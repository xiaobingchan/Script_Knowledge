0，安装docker
rm -rf /etc/yum.repos.d/*.repo
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo
sed -i '/aliyuncs/d' /etc/yum.repos.d/CentOS-Base.repo
sed -i '/aliyuncs/d' /etc/yum.repos.d/epel.repo
cat /etc/redhat-release
sed -i 's/$releasever/7.6.1810/g' /etc/yum.repos.d/CentOS-Base.repo
yum install docker      # yum安装docker
systemctl start docker  # 启动docker服务
systemctl enable docker # 添加开机启动

1，查看所有docker镜像
docker images

2，拉取docker镜像
docker pull centos

3，查询docker镜像
docekr search centos

4，查看"正在运行的"docker容器
docker ps

5，创建镜像容器，启动并进入
docker run -t -i centos:latest /bin/bash

6，提交现成镜像
docker commit -m "Added json gem" -a "Docker Newbee" f1efdad2a1aa luyanjie/test:v1

7，创建Dockerfile
cat >> Dockerfile << EOF
FROM centos:latest
MAINTAINER Docker Newbee <luyanjie@docker.com>
RUN echo "==>"      && \ 
yum -y install wget && \
curl http://www.baidu.com && \
EOF
docker build -t="luyanjie/test:v2" .

8，精简docker容器：


9，限制docker容器资源：


10，上传Docker镜像：
docker login  # 登陆Docker Hub
docker tag docker.io/ubuntu docker.io/luyanjie/testmmyytetr
docker push docker.io/luyanjie/testmmyytetr

11，