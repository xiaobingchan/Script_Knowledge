安装三个主程序

```
yum install -y kubelet kubeadm kubectl
（当前的版本是1.15.3 -- 20190912）
```

前面准备工作做得充分的话，这里就会一切顺利：

```
[root@master ~]# yum install -y kubelet kubeadm kubectl
Loaded plugins: fastestmirror
Loading mirror speeds from cached hostfile
 * base: mirrors.aliyun.com
 * extras: mirrors.aliyun.com
 * updates: mirrors.aliyun.com
Resolving Dependencies
--> Running transaction check
---> Package kubeadm.x86_64 0:1.15.3-0 will be installed
--> Processing Dependency: kubernetes-cni >= 0.7.5 for package: kubeadm-1.15.3-0.x86_64
--> Processing Dependency: cri-tools >= 1.13.0 for package: kubeadm-1.15.3-0.x86_64
---> Package kubectl.x86_64 0:1.15.3-0 will be installed
---> Package kubelet.x86_64 0:1.15.3-0 will be installed
--> Processing Dependency: socat for package: kubelet-1.15.3-0.x86_64
--> Processing Dependency: conntrack for package: kubelet-1.15.3-0.x86_64
--> Running transaction check
---> Package conntrack-tools.x86_64 0:1.4.4-4.el7 will be installed
--> Processing Dependency: libnetfilter_cttimeout.so.1(LIBNETFILTER_CTTIMEOUT_1.1)(64bit) for package: conntrack-tools-1.4.4-4.el7.x86_64
--> Processing Dependency: libnetfilter_cttimeout.so.1(LIBNETFILTER_CTTIMEOUT_1.0)(64bit) for package: conntrack-tools-1.4.4-4.el7.x86_64
--> Processing Dependency: libnetfilter_cthelper.so.0(LIBNETFILTER_CTHELPER_1.0)(64bit) for package: conntrack-tools-1.4.4-4.el7.x86_64
--> Processing Dependency: libnetfilter_queue.so.1()(64bit) for package: conntrack-tools-1.4.4-4.el7.x86_64
--> Processing Dependency: libnetfilter_cttimeout.so.1()(64bit) for package: conntrack-tools-1.4.4-4.el7.x86_64
--> Processing Dependency: libnetfilter_cthelper.so.0()(64bit) for package: conntrack-tools-1.4.4-4.el7.x86_64
---> Package cri-tools.x86_64 0:1.13.0-0 will be installed
---> Package kubernetes-cni.x86_64 0:0.7.5-0 will be installed
---> Package socat.x86_64 0:1.7.3.2-2.el7 will be installed
--> Running transaction check
---> Package libnetfilter_cthelper.x86_64 0:1.0.0-9.el7 will be installed
---> Package libnetfilter_cttimeout.x86_64 0:1.0.0-6.el7 will be installed
---> Package libnetfilter_queue.x86_64 0:1.0.2-2.el7_2 will be installed
--> Finished Dependency Resolution

Dependencies Resolved

=================================================================================================================================================================================================
 Package                                                Arch                                   Version                                          Repository                                  Size
=================================================================================================================================================================================================
Installing:
 kubeadm                                                x86_64                                 1.15.3-0                                         kubernetes                                 8.9 M
 kubectl                                                x86_64                                 1.15.3-0                                         kubernetes                                 9.5 M
 kubelet                                                x86_64                                 1.15.3-0                                         kubernetes                                  22 M
Installing for dependencies:
 conntrack-tools                                        x86_64                                 1.4.4-4.el7                                      base                                       186 k
 cri-tools                                              x86_64                                 1.13.0-0                                         kubernetes                                 5.1 M
 kubernetes-cni                                         x86_64                                 0.7.5-0                                          kubernetes                                  10 M
 libnetfilter_cthelper                                  x86_64                                 1.0.0-9.el7                                      base                                        18 k
 libnetfilter_cttimeout                                 x86_64                                 1.0.0-6.el7                                      base                                        18 k
 libnetfilter_queue                                     x86_64                                 1.0.2-2.el7_2                                    base                                        23 k
 socat                                                  x86_64                                 1.7.3.2-2.el7                                    base                                       290 k

Transaction Summary
=================================================================================================================================================================================================
Install  3 Packages (+7 Dependent packages)

Total download size: 56 M
Installed size: 251 M
Downloading packages:
(1/10): conntrack-tools-1.4.4-4.el7.x86_64.rpm                                                                                                                            | 186 kB  00:00:00     
warning: /var/cache/yum/x86_64/7/kubernetes/packages/14bfe6e75a9efc8eca3f638eb22c7e2ce759c67f95b43b16fae4ebabde1549f3-cri-tools-1.13.0-0.x86_64.rpm: Header V4 RSA/SHA512 Signature, key ID 3e1ba8d5: NOKEY
Public key for 14bfe6e75a9efc8eca3f638eb22c7e2ce759c67f95b43b16fae4ebabde1549f3-cri-tools-1.13.0-0.x86_64.rpm is not installed
(2/10): 14bfe6e75a9efc8eca3f638eb22c7e2ce759c67f95b43b16fae4ebabde1549f3-cri-tools-1.13.0-0.x86_64.rpm                                                                    | 5.1 MB  00:00:02     
(3/10): bfef0ebaf6721dd74cafd3303de20286d6fd78cf4fdc53cd2f348681e434a493-kubectl-1.15.3-0.x86_64.rpm                                                                      | 9.5 MB  00:00:03     
(4/10): d87e7e3e61cc561f18376d2caa53207ba96b932ca2011be9e8e370bdc281d859-kubeadm-1.15.3-0.x86_64.rpm                                                                      | 8.9 MB  00:00:12     
(5/10): libnetfilter_cttimeout-1.0.0-6.el7.x86_64.rpm                                                                                                                     |  18 kB  00:00:00     
(6/10): libnetfilter_queue-1.0.2-2.el7_2.x86_64.rpm                                                                                                                       |  23 kB  00:00:00     
(7/10): a869eccfd0558795d1e001250e1128eb3507905ca5cbf98e5dbfa58088666a74-kubelet-1.15.3-0.x86_64.rpm                                                                      |  22 MB  00:00:07     
(8/10): socat-1.7.3.2-2.el7.x86_64.rpm                                                                                                                                    | 290 kB  00:00:00     
(9/10): libnetfilter_cthelper-1.0.0-9.el7.x86_64.rpm                                                                                                                      |  18 kB  00:00:00     
(10/10): 548a0dcd865c16a50980420ddfa5fbccb8b59621179798e6dc905c9bf8af3b34-kubernetes-cni-0.7.5-0.x86_64.rpm                                                               |  10 MB  00:00:03     
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Total                                                                                                                                                            3.5 MB/s |  56 MB  00:00:15     
Retrieving key from https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg
Importing GPG key 0xA7317B0F:
 Userid     : "Google Cloud Packages Automatic Signing Key <gc-team@google.com>"
 Fingerprint: d0bc 747f d8ca f711 7500 d6fa 3746 c208 a731 7b0f
 From       : https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg
Retrieving key from https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
Importing GPG key 0x3E1BA8D5:
 Userid     : "Google Cloud Packages RPM Signing Key <gc-team@google.com>"
 Fingerprint: 3749 e1ba 95a8 6ce0 5454 6ed2 f09c 394c 3e1b a8d5
 From       : https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
Running transaction check
Running transaction test
Transaction test succeeded
Running transaction
  Installing : kubectl-1.15.3-0.x86_64                                                                                                                                                      1/10 
  Installing : socat-1.7.3.2-2.el7.x86_64                                                                                                                                                   2/10 
  Installing : libnetfilter_cthelper-1.0.0-9.el7.x86_64                                                                                                                                     3/10 
  Installing : cri-tools-1.13.0-0.x86_64                                                                                                                                                    4/10 
  Installing : libnetfilter_queue-1.0.2-2.el7_2.x86_64                                                                                                                                      5/10 
  Installing : libnetfilter_cttimeout-1.0.0-6.el7.x86_64                                                                                                                                    6/10 
  Installing : conntrack-tools-1.4.4-4.el7.x86_64                                                                                                                                           7/10 
  Installing : kubernetes-cni-0.7.5-0.x86_64                                                                                                                                                8/10 
  Installing : kubelet-1.15.3-0.x86_64                                                                                                                                                      9/10 
  Installing : kubeadm-1.15.3-0.x86_64                                                                                                                                                     10/10 
  Verifying  : kubeadm-1.15.3-0.x86_64                                                                                                                                                      1/10 
  Verifying  : libnetfilter_cttimeout-1.0.0-6.el7.x86_64                                                                                                                                    2/10 
  Verifying  : libnetfilter_queue-1.0.2-2.el7_2.x86_64                                                                                                                                      3/10 
  Verifying  : kubelet-1.15.3-0.x86_64                                                                                                                                                      4/10 
  Verifying  : cri-tools-1.13.0-0.x86_64                                                                                                                                                    5/10 
  Verifying  : conntrack-tools-1.4.4-4.el7.x86_64                                                                                                                                           6/10 
  Verifying  : libnetfilter_cthelper-1.0.0-9.el7.x86_64                                                                                                                                     7/10 
  Verifying  : kubernetes-cni-0.7.5-0.x86_64                                                                                                                                                8/10 
  Verifying  : socat-1.7.3.2-2.el7.x86_64                                                                                                                                                   9/10 
  Verifying  : kubectl-1.15.3-0.x86_64                                                                                                                                                     10/10 

Installed:
  kubeadm.x86_64 0:1.15.3-0                                       kubectl.x86_64 0:1.15.3-0                                       kubelet.x86_64 0:1.15.3-0                                      

Dependency Installed:
  conntrack-tools.x86_64 0:1.4.4-4.el7      cri-tools.x86_64 0:1.13.0-0  kubernetes-cni.x86_64 0:0.7.5-0 libnetfilter_cthelper.x86_64 0:1.0.0-9.el7 libnetfilter_cttimeout.x86_64 0:1.0.0-6.el7
  libnetfilter_queue.x86_64 0:1.0.2-2.el7_2 socat.x86_64 0:1.7.3.2-2.el7

Complete!
[root@master ~]# 

```

从安装结果可以看出还安装了cri-tools, kubernetes-cni, socat三个依赖：

官方从Kubernetes 1.14开始将cni依赖升级到了0.7.5版本
socat是kubelet的依赖
cri-tools是CRI(Container Runtime Interface)容器运行时接口的命令行工具
运行kubelet –help可以看到原来kubelet的绝大多数命令行flag参数都被DEPRECATED了，

而官方推荐我们使用–config指定配置文件，并在配置文件中指定原来这些flag所配置的内容。具体内容可以查看这里Set Kubelet parameters via a config file。这也是Kubernetes为了支持动态Kubelet配置（Dynamic Kubelet Configuration）才这么做的，参考Reconfigure a Node’s Kubelet in a Live Cluster。

kubelet的配置文件必须是json或yaml格式，具体可查看这里。

在各节点上，让`kubelet`开机自启动：

```
systemctl enable kubelet.service
```

![1568285254092](06%20%E5%AE%89%E8%A3%85kubernets%E9%9B%86%E7%BE%A4.assets/1568285254092.png)



至此，可以克隆虚拟机了

我们要克隆出两个节点的虚拟机来，规划是这样的



| 主机名 | IP            |
| ------ | ------------- |
| master | 192.168.13.81 |
| node1  | 192.168.13.91 |
| node2  | 192.168.13.92 |

检查确认/etc/hosts文件，应该是这样的

![1568285411899](06%20%E5%AE%89%E8%A3%85kubernets%E9%9B%86%E7%BE%A4.assets/1568285411899.png)

