1，Ansible安装：

wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo
sed -i '/aliyuncs/d' /etc/yum.repos.d/epel.repo
yum clean all
yum makecache
yum install epel-release

yum install ansible




yum install -y salt-master
yum install -y salt-ssh
yum install -y salt-syndic
yum install -y salt-cloud

yum install -y salt-minion
yum install -y salt-ssh
yum install -y salt-syndic
yum install -y salt-cloud
