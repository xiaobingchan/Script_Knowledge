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

查询Docker状态：docker info

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

7，创建Dockerfile建造镜像（docker.io/luyanjie/test）
cat >> Dockerfile << EOF
FROM ubuntu:latest
MAINTAINER Docker Newbee <luyanjie@docker.com>
RUN apt-get update -y
EOF
docker build -t="luyanjie/test:v2" .

8，精简docker容器：
cat > Dockerfile << EOF
FROM ubuntu:latest
ENV VER     3.0.0  
ENV TARBALL http://download.redis.io/releases/redis-$VER.tar.gz
RUN echo "==> Install curl and helper tools..."  && \  
    apt-get update                      && \
    apt-get install -y  curl make gcc   && \
    \
    echo "==> Download, compile, and install..."  && \
    curl -L $TARBALL | tar zxv  && \
    cd redis-$VER               && \
    make                        && \
    make install                && \
    echo "==> Clean up..."  && \
    apt-get remove -y --auto-remove curl make gcc  && \
    apt-get clean                                  && \
    rm -rf /var/lib/apt/lists/*  /redis-$VER
CMD ["redis-server"]
EOF
docker build -t="luyanjie/test:v3" .

9，限制docker容器资源：


10，上传Docker镜像：
docker login  # 登陆Docker Hub
docekr logout # 登出
docker tag luyanjie/test docker.io/luyanjie/testmmyytetr22 # 打上自己的标签
docker push docker.io/luyanjie/testmmyytetr # 提交到自己的docker hub

11，升级Docker
rpm -qa | grep docker
yum remove -y docker-client-1.13.1-63.git94f4240.el7.centos.x86_64
yum remove -y docker-common-1.13.1-63.git94f4240.el7.centos.x86_64
yum remove -y docker-1.13.1-63.git94f4240.el7.centos.x86_64
curl -fsSL https://get.docker.com/ | sh
systemctl restart docker
systemctl enable docker
docker -v

12，镜像（原本）导出
docker save -o 镜像名.tar 镜像名ID
13，镜像（原本）导入
docker load -i 镜像名.tar
14，镜像（有备份变更）导出
docker export -o 容器名.tar 容器名ID
15，镜像（有备份变更）导入
docker import 容器名.tar 镜像名称:tag名称

16，简化alpine常用镜像：
docker pull smebberson/alpine-nginx
docker pull sickp/alpine-redis

17，初次进入容器：
一般Linux：docker run -it 镜像名 /bin/bash
登陆alpine：docker run -it 镜像名 sh

18，重新进入容器：
登陆alpine：docker exec -it CONTAINER_ID sh
一般Linux：docker exec -it CONTAINER_ID /bin/bash

19，指定容器名称：
docker run --name bob_the_container -i -t ubuntu /bin/bash  

20，指定容器开放端口：
docker pull nginx
docker run -p 8888:80  --name=nginx -i -t 540a289bab6c

21，目录映射：
docker run -p 8888:80  --name=nginx -i -t -v /home/test/nginx/html:/usr/share/nginx/html 540a289bab6c 

22，

https://boxueio.com/series/docker-basics/ebook/418
https://blog.csdn.net/qq_25406669/article/details/88339513