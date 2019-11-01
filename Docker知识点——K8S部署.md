#Master主节点（ip:192.168.244.129）
#Node1子节点（ip:192.168.244.146）
#Node2子节点（ip:192.168.244.147）

#输入以下命令关闭防火墙：
systemctl stop firewalld
systemctl disable firewalld
sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config
setenforce 0
swapoff -a

yum clean all
rm -rf /etc/yum.repos.d/*.repo
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo
sed -i '/aliyuncs/d' /etc/yum.repos.d/CentOS-Base.repo
sed -i '/aliyuncs/d' /etc/yum.repos.d/epel.repo
sed -i 's/$releasever/7.6.1810/g' /etc/yum.repos.d/CentOS-Base.repo

# yum安装k8s和etcd：
yum -y install etcd kubernetes flannel

sudo vi /etc/etcd/etcd.conf
######################################################################
ETCD_LISTEN_CLIENT_URLS="http://0.0.0.0:2379" 
######################################################################
 
sudo vi /etc/kubernetes/apiserver
######################################################################
KUBE_API_ADDRESS="--address=0.0.0.0" 
######################################################################

for SERVICES in etcd kube-apiserver kube-controller-manager kube-scheduler; do 
systemctl restart $SERVICES 
systemctl enable $SERVICES 
systemctl status $SERVICES 
done 
 
#10.0.2.7换成自己主机节点虚拟机的ip
etcdctl -C http://192.168.244.129:2379 set /atomic.io/network/config '{"Network":"10.1.0.0/16"}'

############################################################################################################################################
#Node子节点配置kubernetes+docker
systemctl stop firewalld
systemctl disable firewalld
sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config
setenforce 0

yum clean all
rm -rf /etc/yum.repos.d/*.repo
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo
sed -i '/aliyuncs/d' /etc/yum.repos.d/CentOS-Base.repo
sed -i '/aliyuncs/d' /etc/yum.repos.d/epel.repo
sed -i 's/$releasever/7.6.1810/g' /etc/yum.repos.d/CentOS-Base.repo



sudo vi /etc/kubernetes/config
#################################################################
KUBE_MASTER="--master=http://192.168.244.129:8080" 
KUBE_ETCD_SERVERS="--etcd_servers=http://192.168.244.129:2379"
################################################################# 

sudo vi /etc/kubernetes/kubelet
#################################################################
KUBELET_ADDRESS="--address=0.0.0.0"
KUBELET_PORT="--port=10250" 
KUBELET_HOSTNAME="--hostname-override=192.168.244.146" 
KUBELET_API_SERVER="--api-servers=http://192.168.244.129:8080" 
################################################################# 

ip link delete docker0  
 
sudo vi /etc/sysconfig/flanneld
#################################################################
FLANNEL_ETCD_ENDPOINTS="http://192.168.244.129:2379" 
#################################################################

for SERVICES in flanneld kube-proxy kubelet docker; do 
systemctl restart $SERVICES 
systemctl enable $SERVICES 
systemctl status $SERVICES 
done
 

#在主机节点（192.168.244.129）输入命令：
kubectl get nodes




1，部署K8S集群：
#首先生成密钥：
openssl genrsa -out /etc/kubernetes/serviceaccount.key 2048
#编辑/etc/kubenetes/apiserver
#KUBE_API_ARGS="--service_account_key_file=/etc/kubernetes/serviceaccount.key"
#编辑/etc/kubernetes/controller-manager
#KUBE_CONTROLLER_MANAGER_ARGS="--service_account_private_key_file=/etc/kubernetes/serviceaccount.key"
#最后无论是哪种解决方式都需要再重启kubernetes服务：
systemctl restart etcd kube-apiserver kube-controller-manager kube-scheduler
wget http://mirror.centos.org/centos/7/os/x86_64/Packages/python-rhsm-certificates-1.19.10-1.el7_4.x86_64.rpm
rpm2cpio python-rhsm-certificates-1.19.10-1.el7_4.x86_64.rpm | cpio -iv --to-stdout ./etc/rhsm/ca/redhat-uep.pem | tee /etc/rhsm/ca/redhat-uep.pem

# 参考：https://www.linuxhub.org/?p=4599
