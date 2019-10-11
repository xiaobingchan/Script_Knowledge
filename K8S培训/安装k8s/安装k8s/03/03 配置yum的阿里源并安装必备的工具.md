[TOC]

## 1. 配置阿里的yum源

先安装wget

```
yum install -y wget
```



备份一下yum源配置文件 

```
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
```

下载阿里源

```
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
```

清空并重置一下yum源仓库

```
yum clean all
yum makecache
```

更新内核及相关软件，这个要花一些时间

```
yum update
```

ssh远程登录master, 配置阿里云的kubernetes源

```
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF
```

配置阿里云的Docker源

```
wget -O /etc/yum.repos.d/docker-ce.repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo 
```

更新yum源仓库

```
yum makecache
yum update
```

