禁用防火墙

```
systemctl stop firewalld
systemctl disable firewalld
```

禁用SELINUX

```
setenforce 0


vi /etc/selinux/config
SELINUX=disabled
```

创建vi /etc/sysctl.d/k8s.conf文件，添加如下内容：

```
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
net.ipv4.ip_forward = 1
```

执行命令使修改生效:

```
modprobe br_netfilter
sysctl -p /etc/sysctl.d/k8s.conf
```

由于ipvs已经加入到了内核的主干，在所有的Kubernetes节点k8s-master和k8s-node上执行以下脚本。

这个脚本会影响到后面kube-proxy开启ipvs

```
cat > /etc/sysconfig/modules/ipvs.modules <<EOF
#!/bin/bash
modprobe -- ip_vs
modprobe -- ip_vs_rr
modprobe -- ip_vs_wrr
modprobe -- ip_vs_sh
modprobe -- nf_conntrack_ipv4
EOF
```

```
chmod 755 /etc/sysconfig/modules/ipvs.modules
bash /etc/sysconfig/modules/ipvs.modules
```

上面脚本创建了的/etc/sysconfig/modules/ipvs.modules文件，保证在节点重启后能自动加载所需模块。 使用以下命令查看是否已经正确加载所需的内核模块。

```
lsmod | grep -e ip_vs -e nf_conntrack_ipv4
```

![1568283830674](05%20%E5%AE%89%E8%A3%85kubernetes%E5%89%8D%E7%9A%84%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C.assets/1568283830674.png)

接下来还需要确保各个节点上已经安装了ipset软件包。

```
yum install ipset
```

为了便于查看ipvs的代理规则，最好安装一下ipvsadm管理工具。

```
yum install ipvsadm
```

如果以上前提条件如果不满足，则即使kube-proxy的配置开启了ipvs模式，也会退回到iptables模式。

确认一下iptables filter表中FOWARD链的默认策略(policy)为ACCEPT。

```
iptables -nvL
```

![1568284091052](05%20%E5%AE%89%E8%A3%85kubernetes%E5%89%8D%E7%9A%84%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C.assets/1568284091052.png)





## 修改docker cgroup driver为systemd

根据文档**CRI installation**中的内容，对于使用`systemd`作为init system的Linux的发行版，使用`systemd`作为docker的cgroup driver可以确保服务器节点在资源紧张的情况更加稳定，因此这里修改各个节点上docker的cgroup driver为`systemd`。

创建或修改 vi /etc/docker/daemon.json：

```
{
  "exec-opts": ["native.cgroupdriver=systemd"]
}
```

![1568284348957](05%20%E5%AE%89%E8%A3%85kubernetes%E5%89%8D%E7%9A%84%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C.assets/1568284348957.png)

重启docker：

```
systemctl restart docker

docker info | grep Cgroup
Cgroup Driver: systemd
```

![1568284374182](05%20%E5%AE%89%E8%A3%85kubernetes%E5%89%8D%E7%9A%84%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C.assets/1568284374182.png)



禁用交换空间swap

```
swapoff -a
```

修改 /etc/fstab 文件，注释掉 SWAP 的自动挂载。 

![img](05%20%E5%AE%89%E8%A3%85kubernetes%E5%89%8D%E7%9A%84%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C.assets/SNAGHTMLc68b8a9.PNG)

重启

使用free命令，确认swap确实被关闭

![1568284638104](05%20%E5%AE%89%E8%A3%85kubernetes%E5%89%8D%E7%9A%84%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C.assets/1568284638104.png)

swappiness参数调整，修改 /etc/sysctl.d/k8s.conf添加下面一行：

```
vm.swappiness=0
```

执行sysctl -p /etc/sysctl.d/k8s.conf使修改生效。

```
sysctl -p /etc/sysctl.d/k8s.conf
```

