[TOC]

# 01 Docker简介

## 什么是 Docker

官网地址：[https://www.docker.com/](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly93d3cuZG9ja2VyLmNvbS8=)

Docker 最初是 dotCloud 公司创始人 Solomon Hykes 在法国期间发起的一个公司内部项目，它是基于 dotCloud 公司多年云服务技术的一次革新，并于 [2013 年 3 月以 Apache 2.0 授权协议开源][docker-soft]，主要项目代码在 [GitHub](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9naXRodWIuY29tL21vYnkvbW9ieQ==) 上进行维护。Docker 项目后来还加入了 Linux 基金会，并成立推动 [开放容器联盟（OCI）](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly93d3cub3BlbmNvbnRhaW5lcnMub3JnLw==)。

Docker 自开源后受到广泛的关注和讨论，至今其 GitHub 项目已经超过 4 万 6 千个星标和一万多个 fork。甚至由于 Docker 项目的火爆，在 2013 年底，[dotCloud 公司决定改名为 Docker](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9ibG9nLmRvY2tlci5jb20vMjAxMy8xMC9kb3RjbG91ZC1pcy1iZWNvbWluZy1kb2NrZXItaW5jLw==)。Docker 最初是在 Ubuntu 12.04 上开发实现的；Red Hat 则从 RHEL 6.5 开始对 Docker 进行支持；Google 也在其 PaaS 产品中广泛应用 Docker。

Docker 使用 Google 公司推出的 [Go 语言](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9nb2xhbmcub3JnLw==) 进行开发实现，基于 Linux 内核的 [cgroup](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly96aC53aWtpcGVkaWEub3JnL3dpa2kvQ2dyb3Vwcw==)，[namespace](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvTGludXhfbmFtZXNwYWNlcw==)，以及 [AUFS](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvQXVmcw==) 类的 [Union FS](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvVW5pb25fbW91bnQ=) 等技术，对进程进行封装隔离，属于 [操作系统层面的虚拟化技术](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvT3BlcmF0aW5nLXN5c3RlbS1sZXZlbF92aXJ0dWFsaXphdGlvbg==)。由于隔离的进程独立于宿主和其它的隔离的进程，因此也称其为 **容器** 。最初实现是基于 [LXC](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9saW51eGNvbnRhaW5lcnMub3JnL2x4Yy9pbnRyb2R1Y3Rpb24v)，从 0.7 版本以后开始去除 LXC，转而使用自行开发的 [libcontainer](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9naXRodWIuY29tL2RvY2tlci9saWJjb250YWluZXI=)，从 1.11 开始，则进一步演进为使用 [runC](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9naXRodWIuY29tL29wZW5jb250YWluZXJzL3J1bmM=) 和 [containerd](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9naXRodWIuY29tL2NvbnRhaW5lcmQvY29udGFpbmVyZA==)。

Docker 在容器的基础上，进行了进一步的封装，从文件系统、网络互联到进程隔离等等，极大的简化了容器的创建和维护。使得 Docker 技术比虚拟机技术更为轻便、快捷。

下面的图片比较了 Docker 和传统虚拟化方式的不同之处。传统虚拟机技术是虚拟出一套硬件后，在其上运行一个完整操作系统，在该系统上再运行所需应用进程；而容器内的应用进程直接运行于宿主的内核，容器内没有自己的内核，而且也没有进行硬件虚拟。因此容器要比传统虚拟机更为轻便。

![img](03%20Docker/c8aa2f2dde50973.png)

![img](03%20Docker/337ebe0ed8d0bb8.png)

## 为什么需要 Docker

### 概述

作为一种新兴的虚拟化方式，Docker 跟传统的虚拟化方式相比具有众多的优势。

### 更高效的利用系统资源

由于容器不需要进行硬件虚拟以及运行完整操作系统等额外开销，Docker 对系统资源的利用率更高。无论是应用执行速度、内存损耗或者文件存储速度，都要比传统虚拟机技术更高效。因此，相比虚拟机技术，一个相同配置的主机，往往可以运行更多数量的应用。

### 更快速的启动时间

传统的虚拟机技术启动应用服务往往需要数分钟，而 Docker 容器应用，由于直接运行于宿主内核，无需启动完整的操作系统，因此可以做到秒级、甚至毫秒级的启动时间。大大的节约了开发、测试、部署的时间。

### 一致的运行环境

开发过程中一个常见的问题是环境一致性问题。由于开发环境、测试环境、生产环境不一致，导致有些 bug 并未在开发过程中被发现。而 Docker 的镜像提供了除内核外完整的运行时环境，确保了应用运行环境一致性，从而不会再出现 **「这段代码在我机器上没问题啊」** 这类问题。

### 持续交付和部署

对开发和运维（[DevOps](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly96aC53aWtpcGVkaWEub3JnL3dpa2kvRGV2T3Bz)）人员来说，最希望的就是一次创建或配置，可以在任意地方正常运行。

使用 Docker 可以通过定制应用镜像来实现持续集成、持续交付、部署。开发人员可以通过 `Dockerfile` 来进行镜像构建，并结合 [持续集成(Continuous Integration)](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvQ29udGludW91c19pbnRlZ3JhdGlvbg==) 系统进行集成测试，而运维人员则可以直接在生产环境中快速部署该镜像，甚至结合 [持续部署(Continuous Delivery/Deployment)](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvQ29udGludW91c19kZWxpdmVyeQ==) 系统进行自动部署。

而且使用 `Dockerfile` 使镜像构建透明化，不仅仅开发团队可以理解应用运行环境，也方便运维团队理解应用运行所需条件，帮助更好的生产环境中部署该镜像。

### 更轻松的迁移

由于 Docker 确保了执行环境的一致性，使得应用的迁移更加容易。Docker 可以在很多平台上运行，无论是物理机、虚拟机、公有云、私有云，甚至是笔记本，其运行结果是一致的。因此用户可以很轻易的将在一个平台上运行的应用，迁移到另一个平台上，而不用担心运行环境的变化导致应用无法正常运行的情况。

### 更轻松的维护和扩展

Docker 使用的分层存储以及镜像的技术，使得应用重复部分的复用更为容易，也使得应用的维护更新更加简单，基于基础镜像进一步扩展镜像也变得非常简单。此外，Docker 团队同各个开源项目团队一起维护了一大批高质量的 [官方镜像](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9odWIuZG9ja2VyLmNvbS9zZWFyY2gvP3E9JmFtcDtzb3VyY2U9dmVyaWZpZWQmYW1wO3R5cGU9aW1hZ2U=)，既可以直接在生产环境使用，又可以作为基础进一步定制，大大的降低了应用服务的镜像制作成本。

### 对比传统虚拟机总结

| 特性       | 容器               | 虚拟机      |
| :--------- | :----------------- | :---------- |
| 启动       | 秒级               | 分钟级      |
| 硬盘使用   | 一般为 `MB`        | 一般为 `GB` |
| 性能       | 接近原生           | 弱于        |
| 系统支持量 | 单机支持上千个容器 | 一般几十个  |





# 02 安装Docker

## 平台支持

Docker CE 支持多种平台，如下表所示

### 桌面

| 平台                                                         | 架构 |
| :----------------------------------------------------------- | :--- |
| [Docker Desktop for Mac (macOS)](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9kb2NzLmRvY2tlci5jb20vZG9ja2VyLWZvci1tYWMvaW5zdGFsbC8=) | X64  |
| [Docker Desktop for Windows (Microsoft Windows 10)](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9kb2NzLmRvY2tlci5jb20vZG9ja2VyLWZvci13aW5kb3dzL2luc3RhbGwv) | X64  |

### 服务器

| 平台                                                         | x86_64 / amd64 | ARM  | ARM64 / AARCH64 | IBM Power (ppc64le) | IBM Z (s390x) |
| :----------------------------------------------------------- | :------------- | :--- | :-------------- | :------------------ | :------------ |
| [CentOS](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9kb2NzLmRvY2tlci5jb20vaW5zdGFsbC9saW51eC9kb2NrZXItY2UvY2VudG9zLw==) | ✔              |      | ✔               |                     |               |
| [Debian](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9kb2NzLmRvY2tlci5jb20vaW5zdGFsbC9saW51eC9kb2NrZXItY2UvZGViaWFuLw==) | ✔              | ✔    | ✔               |                     |               |
| [Fedora](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9kb2NzLmRvY2tlci5jb20vaW5zdGFsbC9saW51eC9kb2NrZXItY2UvZmVkb3JhLw==) | ✔              |      | ✔               |                     |               |
| [Ubuntu](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9kb2NzLmRvY2tlci5jb20vaW5zdGFsbC9saW51eC9kb2NrZXItY2UvdWJ1bnR1Lw==) | ✔              | ✔    | ✔               | ✔                   | ✔             |

## 准备安装

### 卸载旧版本

---

Ubuntu:

```
apt-get remove docker docker-engine docker.io containerd runc
```

### 使用 APT 安装

```
# 更新数据源
apt-get update
# 安装所需依赖
apt-get -y install apt-transport-https ca-certificates curl software-properties-common
# 安装 GPG 证书
curl -fsSL http://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
# 新增数据源
add-apt-repository "deb [arch=amd64] http://mirrors.aliyun.com/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
# 更新并安装 Docker CE
apt-get update && apt-get install -y docker-ce
```

---

CentOS:

卸载旧版Dokcer

```
yum remove docker docker-common container-selinux docker-selinux docker-engine
```



安装docker

1.安装依赖包

```
yum install -y yum-utils device-mapper-persistent-data lvm2
```



 2.添加阿里云yum源

```
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```




3.更新并安装Docker CE

```
yum makecache fast
yum -y install docker-ce
```



4.启动Docker

 ```
service docker start
 ```



### 验证安装是否成功

```
sudo docker version
# 输出如下
Client:
 Version:           18.09.6
 API version:       1.39
 Go version:        go1.10.8
 Git commit:        481bc77
 Built:             Sat May  4 02:35:57 2019
 OS/Arch:           linux/amd64
 Experimental:      false
Server: Docker Engine - Community
 Engine:
  Version:          18.09.6
  API version:      1.39 (minimum version 1.12)
  Go version:       go1.10.8
  Git commit:       481bc77
  Built:            Sat May  4 01:59:36 2019
  OS/Arch:          linux/amd64
  Experimental:     false
```



### 设置Docker服务自启动

```
sudo systemctl status docker
sudo systemctl start docker
sudo systemctl enable docker
```

### 加入Docker组，可以不用sudo使用docker

```
cat /etc/group | grep docker
```

如果没有Docker用户组，新建docker组

```
# 查看当前用户属于哪个用户组
groups
# 新建docker用户组
sudo groupadd docker

# 将当前用户添加到docker组
sudo gpasswd -a ${USER} docker
```

重启后，检查是否可以不用sudo方式来使用docker了

如果不愿意重启，可以这样，直接切换到docker用户组：

```
newgrp docker
```

## CentOS上安装docker-compose

原以为安装docker-compose是个蛮简单的事儿，但墙外的内容访问太慢，好一番折腾才成功。

```
#查看docker compose版本
 docker-compose version
#查看pip版本
 pip -v
#上一条语句没有显示版本信息则运行下面语句安装 python-pip
 yum -y install epel-release
 yum -y install python-pip
#查看pip版本
 pip -v
#pip进行升级
 pip install --upgrade pip
#进行安装compose 第一条语句报错执行第二条，执行成功则跳过第二条
 pip install docker-compose
 pip install docker-compose --ignore-installed requests 
 docker-compose -version
```



## 配置 Docker 镜像加速器

### 阿里云加速器（推荐）



### 官方提供中国区镜像

```
https://registry.docker-cn.com
```

### 配置加速器

以配置阿里云加速器为例，首先 [登录阿里云（没有账号请先注册）](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9wcm9tb3Rpb24uYWxpeXVuLmNvbS9udG1zL2FjdC9xd2JrLmh0bWw/dXNlckNvZGU9aGdxa3U3YzU=)，搜索 **容器镜像服务**

![img](03%20Docker/8e2280fbb4c6c26.png)

找到你的专属加速器

![img](03%20Docker/ee2667294d92be1.png)

通过修改 daemon 配置文件 `/etc/docker/daemon.json` 来使用加速器

```
tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://xxxxxxxx.mirror.aliyuncs.com"]
}
EOF
# 重启 Docker
systemctl daemon-reload
systemctl restart docker
```

### 验证配置是否成功

```
docker info
# 输出如下
Containers: 38
 Running: 18
 Paused: 0
 Stopped: 20
Images: 10
Server Version: 18.09.6
Storage Driver: overlay2
 Backing Filesystem: extfs
 Supports d_type: true
 Native Overlay Diff: true
Logging Driver: json-file
Cgroup Driver: cgroupfs
Plugins:
 Volume: local
 Network: bridge host macvlan null overlay
 Log: awslogs fluentd gcplogs gelf journald json-file local logentries splunk syslog
Swarm: inactive
Runtimes: runc
Default Runtime: runc
Init Binary: docker-init
containerd version: bb71b10fd8f58240ca47fbb579b9d1028eea7c84
runc version: 2b18fe1d885ee5083ef9f0838fee39b62d653e30
init version: fec3683
Security Options:
 apparmor
 seccomp
  Profile: default
Kernel Version: 4.15.0-51-generic
Operating System: Ubuntu 18.04.2 LTS
OSType: linux
Architecture: x86_64
CPUs: 2
Total Memory: 1.924GiB
Name: kubernetes-master
ID: PJ4H:7AF2:P5UT:6FMR:W4DI:SSWR:IQQR:J6QO:ARES:BOAC:ZVMO:SV2Y
Docker Root Dir: /var/lib/docker
Debug Mode (client): false
Debug Mode (server): false
Registry: https://index.docker.io/v1/
Labels:
Experimental: false
Insecure Registries:
 127.0.0.0/8
## 这里是你配置的镜像加速器
Registry Mirrors:
 https://xxxxxxxx.mirror.aliyuncs.com/
Live Restore Enabled: false
Product License: Community Engine
WARNING: No swap limit support
```

## 运行第一个容器

我们以 Nginx 为例，体验 Docker 是如何运行容器的

```shell
# 下载镜像
docker pull nginx
# 运行容器
docker run --name nginx-container -p 80:80 -d nginx
```

浏览器输入虚拟机地址即可访问 Nginx

![img](03%20Docker/1670b1982b988eb.png)





# 03 Docker 概述

## Docker 引擎

Docker 引擎是一个包含以下主要组件的客户端服务器应用程序。

- 一种服务器，它是一种称为守护进程并且长时间运行的程序。
- REST API 用于指定程序可以用来与守护进程通信的接口，并指示它做什么。
- 一个有命令行界面 (CLI) 工具的客户端。

![img](03%20Docker/6bf7ceddc852371.png)

## Docker 架构

- Docker 使用客户端 - 服务器 (C/S) 架构模式，使用远程 API 来管理和创建 Docker 容器。
- Docker 容器通过 Docker 镜像来创建。
- 容器与镜像的关系类似于面向对象编程中的对象与类。

| Docker | 面向对象 |
| :----- | :------- |
| 容器   | 对象     |
| 镜像   | 类       |

![img](03%20Docker/5270ab20cec963d.png)

| 标题            | 说明                                                         |
| :-------------- | :----------------------------------------------------------- |
| 镜像(Images)    | Docker 镜像是用于创建 Docker 容器的模板。                    |
| 容器(Container) | 容器是独立运行的一个或一组应用。                             |
| 客户端(Client)  | Docker 客户端通过命令行或者其他工具使用 Docker API ([https://docs.docker.com/reference/api/docker_remote_api](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9kb2NzLmRvY2tlci5jb20vcmVmZXJlbmNlL2FwaS9kb2NrZXJfcmVtb3RlX2FwaQ==)) 与 Docker 的守护进程通信。 |
| 主机(Host)      | 一个物理或者虚拟的机器用于执行 Docker 守护进程和容器。       |
| 仓库(Registry)  | Docker 仓库用来保存镜像，可以理解为代码控制中的代码仓库。Docker Hub([https://hub.docker.com](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9odWIuZG9ja2VyLmNvbS8=)) 提供了庞大的镜像集合供使用。 |
| Docker Machine  | Docker Machine是一个简化Docker安装的命令行工具，通过一个简单的命令行即可在相应的平台上安装Docker，比如VirtualBox、 Digital Ocean、Microsoft Azure。 |





# 04 Docker 操作镜像

## 获取镜像

之前提到过，[Docker Hub](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9odWIuZG9ja2VyLmNvbS9leHBsb3JlLw==) 上有大量的高质量的镜像可以用，这里我们就说一下怎么获取这些镜像。

从 Docker 镜像仓库获取镜像的命令是 `docker pull`。其命令格式为：

```
docker pull [选项] [Docker Registry 地址[:端口号]/]仓库名[:标签]
```

具体的选项可以通过 `docker pull --help` 命令看到，这里我们说一下镜像名称的格式。

- **镜像仓库地址：** 地址的格式一般是 `<域名/IP>[:端口号]`。默认地址是 Docker Hub。
- **仓库名：** 如之前所说，这里的仓库名是两段式名称，即 `<用户名>/<软件名>`。对于 Docker Hub，如果不给出用户名，则默认为 `library`，也就是官方镜像。

```
docker pull nginx
# 输出如下
Using default tag: latest
latest: Pulling from library/nginx
fc7181108d40: Pull complete 
c4277fc40ec2: Pull complete 
780053e98559: Pull complete 
Digest: sha256:bdbf36b7f1f77ffe7bd2a32e59235dff6ecf131e3b6b5b96061c652f30685f3a
Status: Downloaded newer image for nginx:latest
```

## 列出镜像

要想列出已经下载下来的镜像，可以使用 `docker image ls` 命令。

```
docker image ls
# 输出如下
REPOSITORY           TAG                 IMAGE ID            CREATED             SIZE
redis                latest              5f515359c7f8        5 days ago          183 MB
nginx                latest              05a60462f8ba        5 days ago          181 MB
mongo                3.2                 fe9198c04d62        5 days ago          342 MB
<none>               <none>              00285df0df87        5 days ago          342 MB
ubuntu               16.04               f753707788c5        4 weeks ago         127 MB
ubuntu               latest              f753707788c5        4 weeks ago         127 MB
ubuntu               14.04               1e0c3dd64ccd        4 weeks ago         188 MB
```

列表包含了 `仓库名`、`标签`、`镜像 ID`、`创建时间` 以及 `所占用的空间`。

其中仓库名、标签在之前的基础概念章节已经介绍过了。**镜像 ID** 则是镜像的唯一标识，一个镜像可以对应多个**标签**。因此，在上面的例子中，我们可以看到 `ubuntu:16.04` 和 `ubuntu:latest` 拥有相同的 ID，因为它们对应的是同一个镜像。

## 镜像体积

如果仔细观察，会注意到，这里标识的所占用空间和在 Docker Hub 上看到的镜像大小不同。比如，`ubuntu:16.04` 镜像大小，在这里是 `127 MB`，但是在 Docker Hub 显示的却是 `50 MB`。这是因为 Docker Hub 中显示的体积是压缩后的体积。在镜像下载和上传过程中镜像是保持着压缩状态的，因此 Docker Hub 所显示的大小是网络传输中更关心的流量大小。而 `docker image ls` 显示的是镜像下载到本地后，展开的大小，准确说，是展开后的各层所占空间的总和，因为镜像到本地后，查看空间的时候，更关心的是本地磁盘空间占用的大小。

另外一个需要注意的问题是，`docker image ls` 列表中的镜像体积总和并非是所有镜像实际硬盘消耗。由于 Docker 镜像是多层存储结构，并且可以继承、复用，因此不同镜像可能会因为使用相同的基础镜像，从而拥有共同的层。由于 Docker 使用 Union FS，相同的层只需要保存一份即可，因此实际镜像硬盘占用空间很可能要比这个列表镜像大小的总和要小的多。

你可以通过以下命令来便捷的查看镜像、容器、数据卷所占用的空间。

```
docker system df
# 输出如下
TYPE                TOTAL               ACTIVE              SIZE                RECLAIMABLE
Images              24                  0                   1.992GB             1.992GB (100%)
Containers          1                   0                   62.82MB             62.82MB (100%)
Local Volumes       9                   0                   652.2MB             652.2MB (100%)
Build Cache                                                 0B                  0B
```

## 虚悬镜像

上面的镜像列表中，还可以看到一个特殊的镜像，这个镜像既没有仓库名，也没有标签，均为 `<none>`

```
<none>               <none>              00285df0df87        5 days ago          342 MB
```

这个镜像原本是有镜像名和标签的，原来为 `mongo:3.2`，随着官方镜像维护，发布了新版本后，重新 `docker pull mongo:3.2` 时，`mongo:3.2` 这个镜像名被转移到了新下载的镜像身上，而旧的镜像上的这个名称则被取消，从而成为了 `<none>`。除了 `docker pull` 可能导致这种情况，`docker build` 也同样可以导致这种现象。由于新旧镜像同名，旧镜像名称被取消，从而出现仓库名、标签均为 `<none>` 的镜像。这类无标签镜像也被称为 **虚悬镜像(dangling image)** ，可以用下面的命令专门显示这类镜像：

```
docker image ls -f dangling=true
# 输出如下
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
<none>              <none>              00285df0df87        5 days ago          342 MB
```

一般来说，虚悬镜像已经失去了存在的价值，是可以随意删除的，可以用下面的命令删除。

```
docker image prune
```

## 中间层镜像

为了加速镜像构建、重复利用资源，Docker 会利用 **中间层镜像**。所以在使用一段时间后，可能会看到一些依赖的中间层镜像。默认的 `docker image ls` 列表中只会显示顶层镜像，如果希望显示包括中间层镜像在内的所有镜像的话，需要加 `-a` 参数。

```
docker image ls -a
```

这样会看到很多无标签的镜像，与之前的虚悬镜像不同，这些无标签的镜像很多都是中间层镜像，是其它镜像所依赖的镜像。这些无标签镜像不应该删除，否则会导致上层镜像因为依赖丢失而出错。实际上，这些镜像也没必要删除，因为之前说过，相同的层只会存一遍，而这些镜像是别的镜像的依赖，因此并不会因为它们被列出来而多存了一份，无论如何你也会需要它们。只要删除那些依赖它们的镜像后，这些依赖的中间层镜像也会被连带删除。

## 删除镜像

如果要删除本地的镜像，可以使用 `docker image rm` 命令，其格式为：

```
docker image rm [选项] <镜像1> [<镜像2> ...]
```

### 用 ID、镜像名、摘要删除镜像

其中，`<镜像>` 可以是 `镜像短 ID`、`镜像长 ID`、`镜像名` 或者 `镜像摘要`。

比如我们有这么一些镜像：

```
docker image ls
# 输出如下
REPOSITORY                  TAG                 IMAGE ID            CREATED             SIZE
centos                      latest              0584b3d2cf6d        3 weeks ago         196.5 MB
redis                       alpine              501ad78535f0        3 weeks ago         21.03 MB
docker                      latest              cf693ec9b5c7        3 weeks ago         105.1 MB
nginx                       latest              e43d811ce2f4        5 weeks ago         181.5 MB
```

我们可以用镜像的完整 ID，也称为 **长 ID**，来删除镜像。使用脚本的时候可能会用长 ID，但是人工输入就太累了，所以更多的时候是用 **短 ID** 来删除镜像。`docker image ls` 默认列出的就已经是短 ID 了，一般取前 3 个字符以上，只要足够区分于别的镜像就可以了。

比如这里，如果我们要删除 `redis:alpine` 镜像，可以执行：

```
docker image rm 501
# 输出如下
Untagged: redis:alpine
Untagged: redis@sha256:f1ed3708f538b537eb9c2a7dd50dc90a706f7debd7e1196c9264edeea521a86d
Deleted: sha256:501ad78535f015d88872e13fa87a828425117e3d28075d0c117932b05bf189b7
Deleted: sha256:96167737e29ca8e9d74982ef2a0dda76ed7b430da55e321c071f0dbff8c2899b
Deleted: sha256:32770d1dcf835f192cafd6b9263b7b597a1778a403a109e2cc2ee866f74adf23
Deleted: sha256:127227698ad74a5846ff5153475e03439d96d4b1c7f2a449c7a826ef74a2d2fa
Deleted: sha256:1333ecc582459bac54e1437335c0816bc17634e131ea0cc48daa27d32c75eab3
Deleted: sha256:4fc455b921edf9c4aea207c51ab39b10b06540c8b4825ba57b3feed1668fa7c7
```

我们也可以用`镜像名`，也就是 `<仓库名>:<标签>`，来删除镜像。

```
docker image rm centos
# 输出如下
Untagged: centos:latest
Untagged: centos@sha256:b2f9d1c0ff5f87a4743104d099a3d561002ac500db1b9bfa02a783a46e0d366c
Deleted: sha256:0584b3d2cf6d235ee310cf14b54667d889887b838d3f3d3033acd70fc3c48b8a
Deleted: sha256:97ca462ad9eeae25941546209454496e1d66749d53dfa2ee32bf1faabd239d38
```

当然，更精确的是使用 `镜像摘要` 删除镜像。

```
docker image ls --digests
# 输出如下
REPOSITORY                  TAG                 DIGEST                                                                    IMAGE ID            CREATED             SIZE
node                        slim                sha256:b4f0e0bdeb578043c1ea6862f0d40cc4afe32a4a582f3be235a3b164422be228   6e0c4c8e3913        3 weeks ago         214 MB
docker image rm node@sha256:b4f0e0bdeb578043c1ea6862f0d40cc4afe32a4a582f3be235a3b164422be228
# 输出如下
Untagged: node@sha256:b4f0e0bdeb578043c1ea6862f0d40cc4afe32a4a582f3be235a3b164422be228
```

### 用 docker image ls 命令来配合

像其它可以承接多个实体的命令一样，可以使用 `docker image ls -q` 来配合使用 `docker image rm`，这样可以成批的删除希望删除的镜像。我们在“镜像列表”章节介绍过很多过滤镜像列表的方式都可以拿过来使用。

比如，我们需要删除所有仓库名为 `redis` 的镜像：

```
docker image rm $(docker image ls -q redis)
```

或者删除所有在 `mongo:3.2` 之前的镜像：

```
docker image rm $(docker image ls -q -f before=mongo:3.2)
```

充分利用你的想象力和 Linux 命令行的强大，你可以完成很多非常赞的功能。

## 扩展阅读

### 列出部分镜像

不加任何参数的情况下，`docker image ls` 会列出所有顶级镜像，但是有时候我们只希望列出部分镜像。`docker image ls` 有好几个参数可以帮助做到这个事情。

根据仓库名列出镜像

```
docker image ls ubuntu
# 输出如下
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ubuntu              16.04               f753707788c5        4 weeks ago         127 MB
ubuntu              latest              f753707788c5        4 weeks ago         127 MB
ubuntu              14.04               1e0c3dd64ccd        4 weeks ago         188 MB
```

列出特定的某个镜像，也就是说指定仓库名和标签

```
docker image ls ubuntu:16.04
# 输出如下
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ubuntu              16.04               f753707788c5        4 weeks ago         127 MB
```

除此以外，`docker image ls` 还支持强大的过滤器参数 `--filter`，或者简写 `-f`。之前我们已经看到了使用过滤器来列出虚悬镜像的用法，它还有更多的用法。比如，我们希望看到在 `mongo:3.2` 之后建立的镜像，可以用下面的命令：

```
docker image ls -f since=mongo:3.2
# 输出如下
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
redis               latest              5f515359c7f8        5 days ago          183 MB
nginx               latest              05a60462f8ba        5 days ago          181 MB
```

想查看某个位置之前的镜像也可以，只需要把 `since` 换成 `before` 即可。

此外，如果镜像构建时，定义了 `LABEL`，还可以通过 `LABEL` 来过滤。

```
docker image ls -f label=com.example.version=0.1
```

### 以特定格式显示

默认情况下，`docker image ls` 会输出一个完整的表格，但是我们并非所有时候都会需要这些内容。比如，刚才删除虚悬镜像的时候，我们需要利用 `docker image ls` 把所有的虚悬镜像的 ID 列出来，然后才可以交给 `docker image rm` 命令作为参数来删除指定的这些镜像，这个时候就用到了 `-q` 参数。

```
docker image ls -q
# 输出如下
5f515359c7f8
05a60462f8ba
fe9198c04d62
00285df0df87
f753707788c5
f753707788c5
1e0c3dd64ccd
```

`--filter` 配合 `-q` 产生出指定范围的 ID 列表，然后送给另一个 `docker` 命令作为参数，从而针对这组实体成批的进行某种操作的做法在 Docker 命令行使用过程中非常常见，不仅仅是镜像，将来我们会在各个命令中看到这类搭配以完成很强大的功能。因此每次在文档看到过滤器后，可以多注意一下它们的用法。

另外一些时候，我们可能只是对表格的结构不满意，希望自己组织列；或者不希望有标题，这样方便其它程序解析结果等，这就用到了 [Go 的模板语法](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9nb2h1Z28uaW8vdGVtcGxhdGVzL2dvLXRlbXBsYXRlcy8=)。

比如，下面的命令会直接列出镜像结果，并且只包含镜像 ID 和仓库名：

```
docker image ls --format "{{.ID}}: {{.Repository}}"
# 输出如下
5f515359c7f8: redis
05a60462f8ba: nginx
fe9198c04d62: mongo
00285df0df87: <none>
f753707788c5: ubuntu
f753707788c5: ubuntu
1e0c3dd64ccd: ubuntu
```

或者打算以表格等距显示，并且有标题行，和默认一样，不过自己定义列：

```shell
docker image ls --format "table {{.ID}}\t{{.Repository}}\t{{.Tag}}"
# 输出如下
IMAGE ID            REPOSITORY          TAG
5f515359c7f8        redis               latest
05a60462f8ba        nginx               latest
fe9198c04d62        mongo               3.2
00285df0df87        <none>              <none>
f753707788c5        ubuntu              16.04
f753707788c5        ubuntu              latest
1e0c3dd64ccd        ubuntu              14.04
```





# 05 Docker 操作容器

## 启动容器

所需要的命令主要为 `docker run`。例如，下面的命令输出一个 “Hello World”，之后终止容器。

```
docker run ubuntu:16.04 /bin/echo 'Hello world'
Hello world
```

当利用 `docker run` 来创建容器时，Docker 在后台运行的标准操作包括：

- 检查本地是否存在指定的镜像，不存在就从公有仓库下载
- 利用镜像创建并启动一个容器
- 分配一个文件系统，并在只读的镜像层外面挂载一层可读写层
- 从宿主主机配置的网桥接口中桥接一个虚拟接口到容器中去
- 从地址池配置一个 ip 地址给容器
- 执行用户指定的应用程序
- 执行完毕后容器被终止

## 终止容器

可以使用 `docker container stop` 来终止一个运行中的容器。此外，当 Docker 容器中指定的应用终结时，容器也自动终止。

例如对于只启动了一个终端的容器，用户通过 `exit` 命令或 `ctrl + d` 来退出终端时，所创建的容器立刻终止。终止状态的容器可以用 `docker container ls -a` 命令看到。例如

```
docker container ls -a
# 输出如下
CONTAINER ID        IMAGE                    COMMAND                CREATED             STATUS                          PORTS               NAMES
ba267838cc1b        ubuntu:14.04             "/bin/bash"            30 minutes ago      Exited (0) About a minute ago                       trusting_newton
98e5efa7d997        training/webapp:latest   "python app.py"        About an hour ago   Exited (0) 34 minutes ago                           backstabbing_pike
```

## 启动已终止容器

处于终止状态的容器，可以通过 `docker container start` 命令来重新启动。此外，`docker container restart` 命令会将一个运行态的容器终止，然后再重新启动它。

```
docker container start [container ID or NAMES]
```

## 守护态运行

更多的时候，需要让 Docker 在后台运行而不是直接把执行命令的结果输出在当前宿主机下。此时，可以通过添加 `-d` 参数来实现。如果不使用 `-d` 参数运行容器。

```
docker run ubuntu:16.04 /bin/sh -c "while true; do echo hello world; sleep 1; done"
# 输出如下
hello world
hello world
hello world
hello world
```

容器会把输出的结果 (STDOUT) 打印到宿主机上面，如果使用了 `-d` 参数运行容器。

```
docker run -d ubuntu:17.10 /bin/sh -c "while true; do echo hello world; sleep 1; done"
# 输出如下
77b2dc01fe0f3f1265df143181e7b9af5e05279a884f4776ee75350ea9d8017a
```

此时容器会在后台运行并不会把输出的结果 (STDOUT) 打印到宿主机上面(输出结果可以用 `docker logs` 查看)。

> **注意：** 容器是否会长久运行，是和 `docker run` 指定的命令有关，和 `-d` 参数无关。

## 容器日志

要获取容器的输出信息，可以通过 `docker container logs` 命令。

```
docker container logs [container ID or NAMES]
# 输出如下
hello world
hello world
hello world
```

## 进入容器

在使用 `-d` 参数时，容器启动后会进入后台。某些时候需要进入容器进行操作，`docker exec` 命令能让我们以交互的方式进入容器。

`docker exec` 后边可以跟多个参数，这里主要说明 `-i` `-t` 参数。只用 `-i` 参数时，由于没有分配伪终端，界面没有我们熟悉的 Linux 命令提示符，但命令执行结果仍然可以返回。当 `-i` `-t` 参数一起使用时，则可以看到我们熟悉的 Linux 命令提示符。

```
docker exec -it 69d1 bash
root@69d137adef7a:/#
```

如果从这个 stdin 中 exit，不会导致容器的停止。更多参数说明请使用 `docker exec --help` 查看。

## 删除容器

可以使用 `docker container rm` 来删除一个处于终止状态的容器。例如

```
docker container rm trusting_newton
trusting_newton
```

如果要删除一个运行中的容器，可以添加 `-f` 参数。Docker 会发送 `SIGKILL` 信号给容器。

## 清理所有处于终止状态的容器

用 `docker container ls -a` 命令可以查看所有已经创建的包括终止状态的容器，如果数量太多要一个个删除可能会很麻烦，用下面的命令可以清理掉所有处于终止状态的容器。

```
docker container prune
```





# 06 Dockerfile 定制镜像

## 概述

Dockerfile 是一个文本文件，其内包含了一条条的 **指令(Instruction)**，每一条指令构建一层，因此每一条指令的内容，就是描述该层应当如何构建。

以之前的 Nginx 镜像为例，这次我们使用 Dockerfile 来定制。在一个空白目录中，建立一个文本文件，并命名为 `Dockerfile`

```
mkdir mynginx
cd mynginx
touch Dockerfile
```

其内容为：

```
FROM nginx
RUN echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
```

这个 Dockerfile 很简单，一共就两行。涉及到了两条指令，`FROM` 和 `RUN`。

## FROM 指定基础镜像

所谓定制镜像，那一定是以一个镜像为基础，在其上进行定制。就像我们之前运行了一个 Nginx 镜像的容器，再进行修改一样，基础镜像是必须指定的。而 `FROM` 就是指定 **基础镜像**，因此一个 `Dockerfile` 中 `FROM` 是必备的指令，并且必须是第一条指令。

除了选择现有镜像为基础镜像外，Docker 还存在一个特殊的镜像，名为 `scratch`。这个镜像是虚拟的概念，并不实际存在，它表示一个空白的镜像。

```
FROM scratch
```

如果你以 `scratch` 为基础镜像的话，意味着你不以任何镜像为基础，接下来所写的指令将作为镜像第一层开始存在。

不以任何系统为基础，直接将可执行文件复制进镜像的做法并不罕见，比如 [`swarm`](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9odWIuZG9ja2VyLmNvbS9fL3N3YXJtLw==)、[`coreos/etcd`](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9xdWF5LmlvL3JlcG9zaXRvcnkvY29yZW9zL2V0Y2Q=)。对于 Linux 下静态编译的程序来说，并不需要有操作系统提供运行时支持，所需的一切库都已经在可执行文件里了，因此直接 `FROM scratch` 会让镜像体积更加小巧。使用 [Go 语言](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9nb2xhbmcub3JnLw==) 开发的应用很多会使用这种方式来制作镜像，这也是为什么有人认为 Go 是特别适合容器微服务架构语言的原因之一。

## RUN 执行命令

`RUN` 指令是用来执行命令行命令的。由于命令行的强大能力，`RUN` 指令在定制镜像时是最常用的指令之一。其格式有两种：

- **shell 格式：** `RUN <命令>`，就像直接在命令行中输入的命令一样。刚才写的 Dockerfile 中的 `RUN` 指令就是这种格式。

```
RUN echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
```

- **exec 格式：** `RUN ["可执行文件", "参数1", "参数2"]`，这更像是函数调用中的格式。

既然 `RUN` 就像 Shell 脚本一样可以执行命令，那么我们是否就可以像 Shell 脚本一样把每个命令对应一个 RUN 呢？比如这样：

```
FROM debian:jessie
RUN apt-get update
RUN apt-get install -y gcc libc6-dev make
RUN wget -O redis.tar.gz "http://download.redis.io/releases/redis-3.2.5.tar.gz"
RUN mkdir -p /usr/src/redis
RUN tar -xzf redis.tar.gz -C /usr/src/redis --strip-components=1
RUN make -C /usr/src/redis
RUN make -C /usr/src/redis install
```

之前说过，Dockerfile 中每一个指令都会建立一层，`RUN` 也不例外。每一个 `RUN` 的行为，就和刚才我们手工建立镜像的过程一样：新建立一层，在其上执行这些命令，执行结束后，`commit` 这一层的修改，构成新的镜像。

而上面的这种写法，创建了 7 层镜像。这是完全没有意义的，而且很多运行时不需要的东西，都被装进了镜像里，比如编译环境、更新的软件包等等。结果就是产生非常臃肿、非常多层的镜像，不仅仅增加了构建部署的时间，也很容易出错。这是很多初学 Docker 的人常犯的一个错误。

> **注意：** Union FS 是有最大层数限制的，比如 AUFS，曾经是最大不得超过 42 层，现在是不得超过 127 层。

上面的 `Dockerfile` 正确的写法应该是这样：

```
FROM debian:jessie
RUN buildDeps='gcc libc6-dev make' \
    && apt-get update \
    && apt-get install -y $buildDeps \
    && wget -O redis.tar.gz "http://download.redis.io/releases/redis-3.2.5.tar.gz" \
    && mkdir -p /usr/src/redis \
    && tar -xzf redis.tar.gz -C /usr/src/redis --strip-components=1 \
    && make -C /usr/src/redis \
    && make -C /usr/src/redis install \
    && rm -rf /var/lib/apt/lists/* \
    && rm redis.tar.gz \
    && rm -r /usr/src/redis \
    && apt-get purge -y --auto-remove $buildDeps
```

首先，之前所有的命令只有一个目的，就是编译、安装 Redis 可执行文件。因此没有必要建立很多层，这只是一层的事情。因此，这里没有使用很多个 `RUN` 对一一对应不同的命令，而是仅仅使用一个 `RUN` 指令，并使用 `&&` 将各个所需命令串联起来。将之前的 7 层，简化为了 1 层。在撰写 Dockerfile 的时候，要经常提醒自己，**这并不是在写 Shell 脚本，而是在定义每一层该如何构建。**

并且，这里为了格式化还进行了换行。Dockerfile 支持 Shell 类的行尾添加 `\` 的命令换行方式，以及行首 `#` 进行注释的格式。良好的格式，比如换行、缩进、注释等，会让维护、排障更为容易，这是一个比较好的习惯。

此外，还可以看到这一组命令的最后添加了清理工作的命令，删除了为了编译构建所需要的软件，清理了所有下载、展开的文件，并且还清理了 `apt` 缓存文件。这是很重要的一步，我们之前说过，镜像是多层存储，每一层的东西并不会在下一层被删除，会一直跟随着镜像。因此镜像构建时，一定要确保每一层只添加真正需要添加的东西，任何无关的东西都应该清理掉。

很多人初学 Docker 制作出了很臃肿的镜像的原因之一，就是忘记了每一层构建的最后一定要清理掉无关文件。

## 构建镜像

好了，让我们再回到之前定制的 Nginx 镜像的 Dockerfile 来。现在我们明白了这个 Dockerfile 的内容，那么让我们来构建这个镜像吧。在 `Dockerfile` 文件所在目录执行：

```
docker build -t nginx:v3 .
# 输出如下
Sending build context to Docker daemon 2.048 kB
Step 1 : FROM nginx
 ---> e43d811ce2f4
Step 2 : RUN echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
 ---> Running in 9cdc27646c7b
 ---> 44aa4490ce2c
Removing intermediate container 9cdc27646c7b
Successfully built 44aa4490ce2c
```

从命令的输出结果中，我们可以清晰的看到镜像的构建过程。在 `Step 2` 中，如同我们之前所说的那样，`RUN` 指令启动了一个容器 `9cdc27646c7b`，执行了所要求的命令，并最后提交了这一层 `44aa4490ce2c`，随后删除了所用到的这个容器 `9cdc27646c7b`。

这里我们使用了 `docker build` 命令进行镜像构建。其格式为：

```
docker build [选项] <上下文路径/URL/->
```

在这里我们指定了最终镜像的名称 `-t nginx:v3`，构建成功后，我们可以像之前运行 `nginx:v2` 那样来运行这个镜像，其结果会和 `nginx:v2` 一样。

## 镜像构建上下文

如果注意，会看到 `docker build` 命令最后有一个 `.`。`.` 表示当前目录，而 `Dockerfile` 就在当前目录，因此不少初学者以为这个路径是在指定 `Dockerfile` 所在路径，这么理解其实是不准确的。如果对应上面的命令格式，你可能会发现，这是在指定 **上下文路径**。那么什么是上下文呢？

首先我们要理解 `docker build` 的工作原理。Docker 在运行时分为 Docker 引擎（也就是服务端守护进程）和客户端工具。Docker 的引擎提供了一组 REST API，被称为 [Docker Remote API](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9kb2NzLmRvY2tlci5jb20vZW5naW5lL3JlZmVyZW5jZS9hcGkvZG9ja2VyX3JlbW90ZV9hcGkv)，而如 `docker` 命令这样的客户端工具，则是通过这组 API 与 Docker 引擎交互，从而完成各种功能。因此，虽然表面上我们好像是在本机执行各种 `docker` 功能，但实际上，一切都是使用的远程调用形式在服务端（Docker 引擎）完成。也因为这种 C/S 设计，让我们操作远程服务器的 Docker 引擎变得轻而易举。

当我们进行镜像构建的时候，并非所有定制都会通过 `RUN` 指令完成，经常会需要将一些本地文件复制进镜像，比如通过 `COPY` 指令、`ADD` 指令等。而 `docker build` 命令构建镜像，其实并非在本地构建，而是在服务端，也就是 Docker 引擎中构建的。那么在这种客户端/服务端的架构中，如何才能让服务端获得本地文件呢？

这就引入了上下文的概念。当构建的时候，用户会指定构建镜像上下文的路径，`docker build` 命令得知这个路径后，会将路径下的所有内容打包，然后上传给 Docker 引擎。这样 Docker 引擎收到这个上下文包后，展开就会获得构建镜像所需的一切文件。

如果在 `Dockerfile` 中这么写：

```
COPY ./package.json /app/
```

这并不是要复制执行 `docker build` 命令所在的目录下的 `package.json`，也不是复制 `Dockerfile` 所在目录下的 `package.json`，而是复制 **上下文（context）** 目录下的 `package.json`。

因此，`COPY` 这类指令中的源文件的路径都是*相对路径*。这也是初学者经常会问的为什么 `COPY ../package.json /app` 或者 `COPY /opt/xxxx /app` 无法工作的原因，因为这些路径已经超出了上下文的范围，Docker 引擎无法获得这些位置的文件。如果真的需要那些文件，应该将它们复制到上下文目录中去。

现在就可以理解刚才的命令 `docker build -t nginx:v3 .` 中的这个 `.`，实际上是在指定上下文的目录，`docker build` 命令会将该目录下的内容打包交给 Docker 引擎以帮助构建镜像。

如果观察 `docker build` 输出，我们其实已经看到了这个发送上下文的过程：

```shell
docker build -t nginx:v3 .
# 输出如下
Sending build context to Docker daemon 2.048 kB
```

理解构建上下文对于镜像构建是很重要的，避免犯一些不应该的错误。比如有些初学者在发现 `COPY /opt/xxxx /app` 不工作后，于是干脆将 `Dockerfile` 放到了硬盘根目录去构建，结果发现 `docker build` 执行后，在发送一个几十 GB 的东西，极为缓慢而且很容易构建失败。那是因为这种做法是在让 `docker build` 打包整个硬盘，这显然是使用错误。

一般来说，应该会将 `Dockerfile` 置于一个空目录下，或者项目根目录下。如果该目录下没有所需文件，那么应该把所需文件复制一份过来。如果目录下有些东西确实不希望构建时传给 Docker 引擎，那么可以用 `.gitignore` 一样的语法写一个 `.dockerignore`，该文件是用于剔除不需要作为上下文传递给 Docker 引擎的。

那么为什么会有人误以为 `.` 是指定 `Dockerfile` 所在目录呢？这是因为在默认情况下，如果不额外指定 `Dockerfile` 的话，会将上下文目录下的名为 `Dockerfile` 的文件作为 Dockerfile。

这只是默认行为，实际上 `Dockerfile` 的文件名并不要求必须为 `Dockerfile`，而且并不要求必须位于上下文目录中，比如可以用 `-f ../Dockerfile.php` 参数指定某个文件作为 `Dockerfile`。

当然，一般大家习惯性的会使用默认的文件名 `Dockerfile`，以及会将其置于镜像构建上下文目录中。



# 07 Dockerfile 指令

## 概述

我们已经介绍了 `FROM`，`RUN`，还提及了 `COPY`, `ADD`，其实 Dockerfile 功能很强大，它提供了十多个指令。下面我们继续讲解其他的指令。

## COPY

格式：

- `COPY <源路径>... <目标路径>`
- `COPY ["<源路径1>",... "<目标路径>"]`

和 `RUN` 指令一样，也有两种格式，一种类似于命令行，一种类似于函数调用。

`COPY` 指令将从构建上下文目录中 `<源路径>` 的文件/目录复制到新的一层的镜像内的 `<目标路径>` 位置。比如：

```
COPY package.json /usr/src/app/
```

`<源路径>` 可以是多个，甚至可以是通配符，其通配符规则要满足 Go 的 [`filepath.Match`](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9nb2xhbmcub3JnL3BrZy9wYXRoL2ZpbGVwYXRoLyNNYXRjaA==) 规则，如：

```
COPY hom* /mydir/
COPY hom?.txt /mydir/
```

`<目标路径>` 可以是容器内的绝对路径，也可以是相对于工作目录的相对路径（工作目录可以用 `WORKDIR` 指令来指定）。目标路径不需要事先创建，如果目录不存在会在复制文件前先行创建缺失目录。

此外，还需要注意一点，使用 `COPY` 指令，源文件的各种元数据都会保留。比如读、写、执行权限、文件变更时间等。这个特性对于镜像定制很有用。特别是构建相关文件都在使用 Git 进行管理的时候。

## ADD

`ADD` 指令和 `COPY` 的格式和性质基本一致。但是在 `COPY` 基础上增加了一些功能。

比如 `<源路径>` 可以是一个 `URL`，这种情况下，Docker 引擎会试图去下载这个链接的文件放到 `<目标路径>` 去。下载后的文件权限自动设置为 `600`，如果这并不是想要的权限，那么还需要增加额外的一层 `RUN` 进行权限调整，另外，如果下载的是个压缩包，需要解压缩，也一样还需要额外的一层 `RUN` 指令进行解压缩。所以不如直接使用 `RUN` 指令，然后使用 `wget` 或者 `curl` 工具下载，处理权限、解压缩、然后清理无用文件更合理。因此，这个功能其实并不实用，而且不推荐使用。

如果 `<源路径>` 为一个 `tar` 压缩文件的话，压缩格式为 `gzip`, `bzip2` 以及 `xz` 的情况下，`ADD` 指令将会自动解压缩这个压缩文件到 `<目标路径>` 去。

在某些情况下，这个自动解压缩的功能非常有用，比如官方镜像 `ubuntu` 中：

```
FROM scratch
ADD ubuntu-xenial-core-cloudimg-amd64-root.tar.gz /
```

但在某些情况下，如果我们真的是希望复制个压缩文件进去，而不解压缩，这时就不可以使用 `ADD` 命令了。

在 Docker 官方的 `Dockerfile 最佳实践文档` 中要求，尽可能的使用 `COPY`，因为 `COPY` 的语义很明确，就是复制文件而已，而 `ADD` 则包含了更复杂的功能，其行为也不一定很清晰。最适合使用 `ADD` 的场合，就是所提及的需要自动解压缩的场合。

另外需要注意的是，`ADD` 指令会令镜像构建缓存失效，从而可能会令镜像构建变得比较缓慢。

因此在 `COPY` 和 `ADD` 指令中选择的时候，可以遵循这样的原则，所有的文件复制均使用 `COPY` 指令，仅在需要自动解压缩的场合使用 `ADD`。

## CMD

`CMD` 指令的格式和 `RUN` 相似，也是两种格式：

- `shell` 格式：`CMD <命令>`
- `exec` 格式：`CMD ["可执行文件", "参数1", "参数2"...]`
- 参数列表格式：`CMD ["参数1", "参数2"...]`。在指定了 `ENTRYPOINT` 指令后，用 `CMD` 指定具体的参数。

之前介绍容器的时候曾经说过，Docker 不是虚拟机，容器就是进程。既然是进程，那么在启动容器的时候，需要指定所运行的程序及参数。`CMD` 指令就是用于指定默认的容器主进程的启动命令的。

在运行时可以指定新的命令来替代镜像设置中的这个默认命令，比如，`ubuntu` 镜像默认的 `CMD` 是 `/bin/bash`，如果我们直接 `docker run -it ubuntu` 的话，会直接进入 `bash`。我们也可以在运行时指定运行别的命令，如 `docker run -it ubuntu cat /etc/os-release`。这就是用 `cat /etc/os-release` 命令替换了默认的 `/bin/bash` 命令了，输出了系统版本信息。

在指令格式上，一般推荐使用 `exec` 格式，这类格式在解析时会被解析为 JSON 数组，因此一定要使用双引号 `"`，而不要使用单引号。

如果使用 `shell` 格式的话，实际的命令会被包装为 `sh -c` 的参数的形式进行执行。比如：

```
CMD echo $HOME
```

在实际执行中，会将其变更为：

```
CMD [ "sh", "-c", "echo $HOME" ]
```

这就是为什么我们可以使用环境变量的原因，因为这些环境变量会被 shell 进行解析处理。

提到 `CMD` 就不得不提容器中应用在前台执行和后台执行的问题。这是初学者常出现的一个混淆。

Docker 不是虚拟机，容器中的应用都应该以前台执行，而不是像虚拟机、物理机里面那样，用 upstart/systemd 去启动后台服务，容器内没有后台服务的概念。

一些初学者将 `CMD` 写为：

```
CMD service nginx start
```

然后发现容器执行后就立即退出了。甚至在容器内去使用 `systemctl` 命令结果却发现根本执行不了。这就是因为没有搞明白前台、后台的概念，没有区分容器和虚拟机的差异，依旧在以传统虚拟机的角度去理解容器。

对于容器而言，其启动程序就是容器应用进程，容器就是为了主进程而存在的，主进程退出，容器就失去了存在的意义，从而退出，其它辅助进程不是它需要关心的东西。

而使用 `service nginx start` 命令，则是希望 upstart 来以后台守护进程形式启动 `nginx` 服务。而刚才说了 `CMD service nginx start` 会被理解为 `CMD [ "sh", "-c", "service nginx start"]`，因此主进程实际上是 `sh`。那么当 `service nginx start` 命令结束后，`sh` 也就结束了，`sh` 作为主进程退出了，自然就会令容器退出。

正确的做法是直接执行 `nginx` 可执行文件，并且要求以前台形式运行。比如：

```
CMD ["nginx", "-g", "daemon off;"]
```

## ENTRYPOINT

`ENTRYPOINT` 的格式和 `RUN` 指令格式一样，分为 `exec` 格式和 `shell` 格式。

`ENTRYPOINT` 的目的和 `CMD` 一样，都是在指定容器启动程序及参数。`ENTRYPOINT` 在运行时也可以替代，不过比 `CMD` 要略显繁琐，需要通过 `docker run` 的参数 `--entrypoint` 来指定。

当指定了 `ENTRYPOINT` 后，`CMD` 的含义就发生了改变，不再是直接的运行其命令，而是将 `CMD` 的内容作为参数传给 `ENTRYPOINT` 指令，换句话说实际执行时，将变为：

```
<ENTRYPOINT> "<CMD>"
```

那么有了 `CMD` 后，为什么还要有 `ENTRYPOINT` 呢？这种 `<ENTRYPOINT> "<CMD>"` 有什么好处么？让我们来看几个场景。

### 场景一：让镜像变成像命令一样使用

假设我们需要一个得知自己当前公网 IP 的镜像，那么可以先用 `CMD` 来实现：

```
FROM ubuntu:16.04
RUN apt-get update \
    && apt-get install -y curl \
    && rm -rf /var/lib/apt/lists/*
CMD [ "curl", "-s", "http://ip.cn" ]
```

假如我们使用 `docker build -t myip .` 来构建镜像的话，如果我们需要查询当前公网 IP，只需要执行：

```
docker run myip
当前 IP：61.148.226.66 来自：北京市 联通
```

嗯，这么看起来好像可以直接把镜像当做命令使用了，不过命令总有参数，如果我们希望加参数呢？比如从上面的 `CMD` 中可以看到实质的命令是 `curl`，那么如果我们希望显示 HTTP 头信息，就需要加上 `-i` 参数。那么我们可以直接加 `-i` 参数给 `docker run myip` 么？

```
docker run myip -i
docker: Error response from daemon: invalid header field value "oci runtime error: container_linux.go:247: starting container process caused \"exec: \\\"-i\\\": executable file not found in $PATH\"\n".
```

我们可以看到可执行文件找不到的报错，`executable file not found`。之前我们说过，跟在镜像名后面的是 `command`，运行时会替换 `CMD` 的默认值。因此这里的 `-i` 替换了原来的 `CMD`，而不是添加在原来的 `curl -s http://ip.cn` 后面。而 `-i` 根本不是命令，所以自然找不到。

那么如果我们希望加入 `-i` 这参数，我们就必须重新完整的输入这个命令：

```
docker run myip curl -s http://ip.cn -i
```

这显然不是很好的解决方案，而使用 `ENTRYPOINT` 就可以解决这个问题。现在我们重新用 `ENTRYPOINT` 来实现这个镜像：

```
FROM ubuntu:16.04
RUN apt-get update \
    && apt-get install -y curl \
    && rm -rf /var/lib/apt/lists/*
ENTRYPOINT [ "curl", "-s", "http://ip.cn" ]
```

这次我们再来尝试直接使用 `docker run myip -i`：

```
docker run myip
当前 IP：61.148.226.66 来自：北京市 联通
docker run myip -i
HTTP/1.1 200 OK
Server: nginx/1.8.0
Date: Tue, 22 Nov 2016 05:12:40 GMT
Content-Type: text/html; charset=UTF-8
Vary: Accept-Encoding
X-Powered-By: PHP/5.6.24-1~dotdeb+7.1
X-Cache: MISS from cache-2
X-Cache-Lookup: MISS from cache-2:80
X-Cache: MISS from proxy-2_6
Transfer-Encoding: chunked
Via: 1.1 cache-2:80, 1.1 proxy-2_6:8006
Connection: keep-alive
当前 IP：61.148.226.66 来自：北京市 联通
```

可以看到，这次成功了。这是因为当存在 `ENTRYPOINT` 后，`CMD` 的内容将会作为参数传给 `ENTRYPOINT`，而这里 `-i` 就是新的 `CMD`，因此会作为参数传给 `curl`，从而达到了我们预期的效果。

### 场景二：应用运行前的准备工作

启动容器就是启动主进程，但有些时候，启动主进程前，需要一些准备工作。

比如 `mysql` 类的数据库，可能需要一些数据库配置、初始化的工作，这些工作要在最终的 mysql 服务器运行之前解决。

此外，可能希望避免使用 `root` 用户去启动服务，从而提高安全性，而在启动服务前还需要以 `root` 身份执行一些必要的准备工作，最后切换到服务用户身份启动服务。或者除了服务外，其它命令依旧可以使用 `root` 身份执行，方便调试等。

这些准备工作是和容器 `CMD` 无关的，无论 `CMD` 为什么，都需要事先进行一个预处理的工作。这种情况下，可以写一个脚本，然后放入 `ENTRYPOINT` 中去执行，而这个脚本会将接到的参数（也就是 `<CMD>`）作为命令，在脚本最后执行。比如官方镜像 `redis` 中就是这么做的：

```
FROM alpine:3.4
RUN addgroup -S redis && adduser -S -G redis redis
ENTRYPOINT ["docker-entrypoint.sh"]
EXPOSE 6379
CMD [ "redis-server" ]
```

可以看到其中为了 redis 服务创建了 redis 用户，并在最后指定了 `ENTRYPOINT` 为 `docker-entrypoint.sh` 脚本。

```
#!/bin/sh
# allow the container to be started with `--user`
if [ "$1" = 'redis-server' -a "$(id -u)" = '0' ]; then
    chown -R redis .
    exec su-exec redis "$0" "$@"
fi
exec "$@"
```

该脚本的内容就是根据 `CMD` 的内容来判断，如果是 `redis-server` 的话，则切换到 `redis` 用户身份启动服务器，否则依旧使用 `root` 身份执行。比如：

```
docker run -it redis id
uid=0(root) gid=0(root) groups=0(root)
```

## ENV

格式有两种：

- `ENV <key> <value>`
- `ENV <key1>=<value1> <key2>=<value2>...`

这个指令很简单，就是设置环境变量而已，无论是后面的其它指令，如 `RUN`，还是运行时的应用，都可以直接使用这里定义的环境变量。

```
ENV VERSION=1.0 DEBUG=on \
    NAME="Happy Feet"
```

这个例子中演示了如何换行，以及对含有空格的值用双引号括起来的办法，这和 Shell 下的行为是一致的。

定义了环境变量，那么在后续的指令中，就可以使用这个环境变量。比如在官方 `node` 镜像 `Dockerfile` 中，就有类似这样的代码：

```
ENV NODE_VERSION 7.2.0
RUN curl -SLO "https://nodejs.org/dist/v$NODE_VERSION/node-v$NODE_VERSION-linux-x64.tar.xz" \
  && curl -SLO "https://nodejs.org/dist/v$NODE_VERSION/SHASUMS256.txt.asc" \
  && gpg --batch --decrypt --output SHASUMS256.txt SHASUMS256.txt.asc \
  && grep " node-v$NODE_VERSION-linux-x64.tar.xz\$" SHASUMS256.txt | sha256sum -c - \
  && tar -xJf "node-v$NODE_VERSION-linux-x64.tar.xz" -C /usr/local --strip-components=1 \
  && rm "node-v$NODE_VERSION-linux-x64.tar.xz" SHASUMS256.txt.asc SHASUMS256.txt \
  && ln -s /usr/local/bin/node /usr/local/bin/nodejs
```

在这里先定义了环境变量 `NODE_VERSION`，其后的 `RUN` 这层里，多次使用 `$NODE_VERSION` 来进行操作定制。可以看到，将来升级镜像构建版本的时候，只需要更新 `7.2.0` 即可，`Dockerfile` 构建维护变得更轻松了。

下列指令可以支持环境变量展开： `ADD`、`COPY`、`ENV`、`EXPOSE`、`LABEL`、`USER`、`WORKDIR`、`VOLUME`、`STOPSIGNAL`、`ONBUILD`。

可以从这个指令列表里感觉到，环境变量可以使用的地方很多，很强大。通过环境变量，我们可以让一份 `Dockerfile` 制作更多的镜像，只需使用不同的环境变量即可。

## VOLUME

格式为：

- `VOLUME ["<路径1>", "<路径2>"...]`
- `VOLUME <路径>`

之前我们说过，容器运行时应该尽量保持容器存储层不发生写操作，对于数据库类需要保存动态数据的应用，其数据库文件应该保存于卷(volume)中，后面的章节我们会进一步介绍 Docker 卷的概念。为了防止运行时用户忘记将动态文件所保存目录挂载为卷，在 `Dockerfile` 中，我们可以事先指定某些目录挂载为匿名卷，这样在运行时如果用户不指定挂载，其应用也可以正常运行，不会向容器存储层写入大量数据。

```
VOLUME /data
```

这里的 `/data` 目录就会在运行时自动挂载为匿名卷，任何向 `/data` 中写入的信息都不会记录进容器存储层，从而保证了容器存储层的无状态化。当然，运行时可以覆盖这个挂载设置。比如：

```
docker run -d -v mydata:/data xxxx
```

在这行命令中，就使用了 `mydata` 这个命名卷挂载到了 `/data` 这个位置，替代了 `Dockerfile` 中定义的匿名卷的挂载配置。

## EXPOSE

格式为 `EXPOSE <端口1> [<端口2>...]`。

`EXPOSE` 指令是声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务。在 Dockerfile 中写入这样的声明有两个好处，一个是帮助镜像使用者理解这个镜像服务的守护端口，以方便配置映射；另一个用处则是在运行时使用随机端口映射时，也就是 `docker run -P` 时，会自动随机映射 `EXPOSE` 的端口。

此外，在早期 Docker 版本中还有一个特殊的用处。以前所有容器都运行于默认桥接网络中，因此所有容器互相之间都可以直接访问，这样存在一定的安全性问题。于是有了一个 Docker 引擎参数 `--icc=false`，当指定该参数后，容器间将默认无法互访，除非互相间使用了 `--links` 参数的容器才可以互通，并且只有镜像中 `EXPOSE` 所声明的端口才可以被访问。这个 `--icc=false` 的用法，在引入了 `docker network` 后已经基本不用了，通过自定义网络可以很轻松的实现容器间的互联与隔离。

要将 `EXPOSE` 和在运行时使用 `-p <宿主端口>:<容器端口>` 区分开来。`-p`，是映射宿主端口和容器端口，换句话说，就是将容器的对应端口服务公开给外界访问，而 `EXPOSE` 仅仅是声明容器打算使用什么端口而已，并不会自动在宿主进行端口映射。

## WORKDIR

格式为 `WORKDIR <工作目录路径>`。

使用 `WORKDIR` 指令可以来指定工作目录（或者称为当前目录），以后各层的当前目录就被改为指定的目录，如该目录不存在，`WORKDIR` 会帮你建立目录。

之前提到一些初学者常犯的错误是把 `Dockerfile` 等同于 Shell 脚本来书写，这种错误的理解还可能会导致出现下面这样的错误：

```
RUN cd /app
RUN echo "hello" > world.txt
```

如果将这个 `Dockerfile` 进行构建镜像运行后，会发现找不到 `/app/world.txt` 文件，或者其内容不是 `hello`。原因其实很简单，在 Shell 中，连续两行是同一个进程执行环境，因此前一个命令修改的内存状态，会直接影响后一个命令；而在 `Dockerfile` 中，这两行 `RUN` 命令的执行环境根本不同，是两个完全不同的容器。这就是对 `Dockerfile` 构建分层存储的概念不了解所导致的错误。

之前说过每一个 `RUN` 都是启动一个容器、执行命令、然后提交存储层文件变更。第一层 `RUN cd /app` 的执行仅仅是当前进程的工作目录变更，一个内存上的变化而已，其结果不会造成任何文件变更。而到第二层的时候，启动的是一个全新的容器，跟第一层的容器更完全没关系，自然不可能继承前一层构建过程中的内存变化。

因此如果需要改变以后各层的工作目录的位置，那么应该使用 `WORKDIR` 指令。



# 08 Docker Compose 简介

## 概述

Compose 项目是 Docker 官方的开源项目，负责实现对 Docker 容器集群的快速编排。从功能上看，跟 OpenStack 中的 Heat 十分类似。

其代码目前在 [https://github.com/docker/compose](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9naXRodWIuY29tL2RvY2tlci9jb21wb3Nl) 上开源。

Compose 定位是 「定义和运行多个 Docker 容器的应用（Defining and running multi-container Docker applications）」，其前身是开源项目 Fig。

我们知道使用一个 Dockerfile 模板文件，可以让用户很方便的定义一个单独的应用容器。然而，在日常工作中，经常会碰到需要多个容器相互配合来完成某项任务的情况。例如要实现一个 Web 项目，除了 Web 服务容器本身，往往还需要再加上后端的数据库服务容器，甚至还包括负载均衡容器等。

Compose 恰好满足了这样的需求。它允许用户通过一个单独的 **docker-compose.yml** 模板文件（YAML 格式）来定义一组相关联的应用容器为一个项目（Project）。Compose 中有两个重要的概念：

- 服务 (Service)：一个应用的容器，实际上可以包括若干运行相同镜像的容器实例。
- 项目 (Project)：由一组关联的应用容器组成的一个完整业务单元，在 docker-compose.yml 文件中定义。

Compose 的默认管理对象是项目，通过子命令对项目中的一组容器进行便捷地生命周期管理。Compose 项目由 Python 编写，实现上调用了 Docker 服务提供的 API 来对容器进行管理。因此，只要所操作的平台支持 Docker API，就可以在其上利用 Compose 来进行编排管理。

## 安装 Docker Compose

Compose 支持 Linux、macOS、Windows 10 三大平台。在 Linux 上的也安装十分简单，从 [官方 GitHub Release](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9naXRodWIuY29tL2RvY2tlci9jb21wb3NlL3JlbGVhc2Vz) 处直接下载编译好的二进制文件即可。

```
curl -L https://github.com/docker/compose/releases/download/1.24.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```

## 验证安装是否成功

```
docker-compose version
# 输出如下
docker-compose version 1.24.0, build 0aa59064
docker-py version: 3.7.2
CPython version: 3.6.8
OpenSSL version: OpenSSL 1.1.0j  20 Nov 2018
```





# 09 Docker Compose 使用

## 术语

首先介绍几个术语。

- 服务 (Service)：一个应用容器，实际上可以运行多个相同镜像的实例。
- 项目 (Project)：由一组关联的应用容器组成的一个完整业务单元。

可见，一个项目可以由多个服务（容器）关联而成，Compose 面向项目进行管理。

## 场景

最常见的项目是 Web 网站，该项目应该包含 Web 应用和缓存。下面我们用 Python 来建立一个能够记录页面访问次数的 Web 网站。

### Python 应用

新建文件夹，在该目录中编写 `app.py` 文件

```
from flask import Flask
from redis import Redis
app = Flask(__name__)
redis = Redis(host='redis', port=6379)
@app.route('/')
def hello():
    count = redis.incr('hits')
    return 'Hello World! 该页面已被访问 {} 次。\n'.format(count)
if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True)
```

### Dockerfile

编写 Dockerfile 文件，内容为

```
FROM python:3.6-alpine
ADD . /code
WORKDIR /code
RUN pip install redis flask
CMD ["python", "app.py"]
```

### Docker Compose 模板

编写 `docker-compose.yml` 文件，这个是 Compose 使用的主模板文件。

```
version: '3'
services:
  web:
    build: .
    ports:
     - "5000:5000"
  redis:
    image: "redis:alpine"
```

### 运行 Compose 项目

```
docker-compose up -d
```

此时访问本地 `5000` 端口，每次刷新页面，计数就会加 1。

## 扩展阅读

### YAML 配置文件语言

YAML 是专门用来写配置文件的语言，非常简洁和强大，远比 JSON 格式方便。YAML 语言的设计目标，就是方便人类读写。它实质上是一种通用的数据串行化格式。它的基本语法规则如下：

- **大小写敏感**
- **使用缩进表示层级关系**
- **缩进时不允许使用 TAB 键，只允许使用空格。**
- **缩进的空格数目不重要，只要相同层级的元素左侧对齐即可**

`#` 表示注释，从这个字符一直到行尾，都会被解析器忽略。YAML 支持的数据结构有三种：

- **对象：** 键值对的集合，又称为映射（mapping）/ 哈希（hashes） / 字典（dictionary）
- **数组：** 一组按次序排列的值，又称为序列（sequence） / 列表（list）
- **纯量（scalars）：** 单个的、不可再分的值

#### YAML 对象

对象的一组键值对，使用冒号结构表示

```
animal: pets
```

#### YAML 数组

一组连词线开头的行，构成一个数组

```
- Cat
- Dog
- Goldfish
```

数据结构的子成员是一个数组，则可以在该项下面缩进一个空格

```
- Array
 - Cat
 - Dog
 - Goldfish
```

#### YAML 复合结构

对象和数组可以结合使用，形成复合结构

```
languages:
 - Ruby
 - Perl
 - Python 
websites:
 YAML: yaml.org 
 Ruby: ruby-lang.org 
 Python: python.org 
 Perl: use.perl.org 
```

#### YAML 纯量

纯量是最基本的、不可再分的值。以下数据类型都属于 JavaScript 的纯量

- 字符串
- 布尔值
- 整数
- 浮点数
- Null
- 时间
- 日期

### 修改 IP 和 DNS

课程演示会采用多虚拟机模拟分布式场景，为防止 IP 冲突，无法联网等问题，需要预先设置好主机名、IP、DNS 配置

#### 修改主机名

- 修改 cloud.cfg 防止重启后主机名还原

```
vi /etc/cloud/cloud.cfg
# 该配置默认为 false，修改为 true 即可
preserve_hostname: true
```

- 修改主机名

```
# 修改主机名
hostnamectl set-hostname deployment
# 配置 hosts
cat >> /etc/hosts << EOF
192.168.141.130 deployment
EOF
```

#### 修改 IP

编辑 `vi /etc/netplan/50-cloud-init.yaml` 配置文件，修改内容如下

```
network:
    ethernets:
        ens33:
          addresses: [192.168.141.130/24]
          gateway4: 192.168.141.2
          nameservers:
            addresses: [192.168.141.2]
    version: 2
```

使用 `netplan apply` 命令让配置生效

#### 修改 DNS

```
# 取消 DNS 行注释，并增加 DNS 配置如：114.114.114.114，修改后重启下计算机
vi /etc/systemd/resolved.conf
```





# 10 Docker Compose 部署应用程序

## 部署 Tomcat

```
version: '3.1'
services:
  tomcat:
    restart: always
    image: tomcat
    container_name: tomcat
    ports:
      - 8080:8080
    volumes:
      - ./webapps:/usr/local/tomcat/webapps
    environment:
      TZ: Asia/Shanghai
```

## 部署 MySQL

```
version: '3.1'
services:
  db:
    # 目前 latest 版本为 MySQL8.x
    image: mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: 123456
    command:
      --default-authentication-plugin=mysql_native_password
      --character-set-server=utf8mb4
      --collation-server=utf8mb4_general_ci
      --explicit_defaults_for_timestamp=true
      --lower_case_table_names=1
    ports:
      - 3306:3306
    volumes:
      - ./data:/var/lib/mysql
  # MySQL 的 Web 客户端
  adminer:
    image: adminer
    restart: always
    ports:
      - 8080:8080
```





# 11 Docker Compose 部署Gitlab

## 什么是 GitLab

GitLab 是利用 Ruby on Rails 一个开源的版本管理系统，实现一个自托管的 Git 项目仓库，可通过 Web 界面进行访问公开的或者私人项目。它拥有与 Github 类似的功能，能够浏览源代码，管理缺陷和注释。可以管理团队对仓库的访问，它非常易于浏览提交过的版本并提供一个文件历史库。团队成员可以利用内置的简单聊天程序 (Wall) 进行交流。它还提供一个代码片段收集功能可以轻松实现代码复用，便于日后有需要的时候进行查找。

## 部署 GitLab

我们使用 Docker 来安装和运行 GitLab 中文版，`docker-compose.yml` 配置如下：

```
version: '3'
services:
    web:
      image: 'twang2218/gitlab-ce-zh'
      restart: always
      hostname: '192.168.75.145'
      environment:
        TZ: 'Asia/Shanghai'
        GITLAB_OMNIBUS_CONFIG: |
          external_url 'http://192.168.75.145'
          gitlab_rails['gitlab_shell_ssh_port'] = 2222
          unicorn['port'] = 8888
          nginx['listen_port'] = 80
      ports:
        - '80:80'
        - '443:443'
        - '2222:22'
      volumes:
        - ./config:/etc/gitlab
        - ./data:/var/opt/gitlab
        - ./logs:/var/log/gitlab
```

## 配置 GitLab

- 访问地址：[http://ip:8080](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cDovL2lwOjgwODA=)

![img](03%20Docker/78952f31f90f513.png)

- 设置管理员初始密码，这里的密码最好是 **字母 + 数字组合，并且大于等于 8 位**
- 配置完成后登录，管理员账号是 **root**

![img](03%20Docker/251781cc957b903.png)

> **注意：** 如果服务器配置较低，启动运行可能需要较长时间，请耐心等待

## 设置 GitLab

第一次使用时可以做一些初始化设置，点击 **管理区域** -> **设置**

![img](03%20Docker/c63e0370dabdb19.png)

- 关闭头像功能，由于 Gravatar 头像为网络头像，在网络情况不理想时可能导致访问时卡顿

![img](03%20Docker/30af40d9b01b1a1.png)

- 由于是内部代码托管服务器，可以直接关闭注册功能，由管理员统一创建用户即可

![img](03%20Docker/8db10129bbcfb65.png)

## 账户管理

使用时请不要直接通过 root 用户操作，需要先创建用户，然后通过创建的用户操作，如果你是管理员还需要为其他开发人员分配账户

- 创建账户，点击 **管理区域** -> **新建用户**

![img](03%20Docker/af0b364f94d5f39.png)

- 设置账户信息，同时你可以将自己设置为管理员

![img](03%20Docker/76569aa1df37663.png)

- 修改用户密码，由于我们创建时并没有配置邮箱，所以还需要重新编辑用户信息并手动设置密码

![img](03%20Docker/8916c1e414b4b93.png)

![img](03%20Docker/5a01818fe2672e2.png)

- 退出并使用新账户登录

![img](03%20Docker/5590805b365ea54.png)

> **注意：** 创建完账户，第一次登录时还会提示你修改登录密码

## 项目管理

- 点击 **+** 号 -> **新建项目**

![img](03%20Docker/0cba3c4721d9939.png)

- 输入项目名称及描述信息，设置可见等级为私有，这样别人就看不见你的项目

![img](03%20Docker/a54ab5b1aab1f0d.png)

- 我们选择通过增加一个 README 的方式来初始化项目

![img](03%20Docker/9752e30ab553c26.png)

- 直接提交修改即可

![img](03%20Docker/2a7358ce3c1d8ac.png)

## 使用 SSH 方式拉取和推送

### 生成 SSH KEY

- 使用 ssh-keygen 工具生成，位置在 Git 安装目录下，我的是 `C:\Program Files\Git\usr\bin`，输入命令：

```
ssh-keygen -t rsa -C "your_email@example.com"
```

- 执行成功后的效果：

```
Microsoft Windows [版本 10.0.14393]
(c) 2016 Microsoft Corporation。保留所有权利。
C:\Program Files\Git\usr\bin>ssh-keygen -t rsa -C "topsale@vip.qq.com"
Generating public/private rsa key pair.
Enter file in which to save the key (/c/Users/Lusifer/.ssh/id_rsa):
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
Your identification has been saved in /c/Users/Lusifer/.ssh/id_rsa.
Your public key has been saved in /c/Users/Lusifer/.ssh/id_rsa.pub.
The key fingerprint is:
SHA256:cVesJKa5VnQNihQOTotXUAIyphsqjb7Z9lqOji2704E topsale@vip.qq.com
The key's randomart image is:
+---[RSA 2048]----+
|  + ..=o=.  .+.  |
| o o + B .+.o.o  |
|o   . + +=o+..   |
|.=   .  oo...    |
|= o     So       |
|oE .    o        |
| .. .. .         |
| o*o+            |
| *B*oo           |
+----[SHA256]-----+
C:\Program Files\Git\usr\bin>
```

### 复制 SSH-KEY 信息到 GitLab

- 密钥位置在：`C:\Users\你的用户名\.ssh` 目录下，找到 `id_rsa.pub` 并使用编辑器打开，如：

![img](03%20Docker/7c1bf705b1cdbd5.png)

- 登录 GitLab，点击“用户头像”-->“设置”-->“SSH 密钥”

![img](03%20Docker/70ceb16832d9314.png)

- 成功增加密钥后的效果

![img](03%20Docker/2990bf78f6fb13e.png)

## 使用 TortoiseGit

### 克隆项目

- 新建一个存放代码仓库的本地文件夹
- 在文件夹空白处按右键
- 选择 **Git 克隆...**

![img](03%20Docker/7f6dc076f32e8fa.png)

- 复制项目地址到 URL

![img](03%20Docker/2807b1f8671738f.png)

- 如果弹出连接信息请选择是

![img](03%20Docker/f1af33947230f22.png)

- 成功克隆项目到本地

![img](03%20Docker/6de1febdafa6720.png)

### 推送项目

- 创建或修改文件（这里的文件为所有文件，包括：代码、图片等）
- 我们以创建 `.gitignore` 过滤配置文件为例，该文件的主要作用为过滤不需要上传的文件，比如：IDE 生成的工程文件、编译后的 class 文件等
- 在工程目录下，新建 `.gitignore` 文件，并填入如下配置

```
target/
!.mvn/wrapper/maven-wrapper.jar
## STS ##
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
## IntelliJ IDEA ##
.idea
*.iws
*.iml
*.ipr
## JRebel ##
rebel.xml
## MAC ##
.DS_Store
## Other ##
logs/
temp/
```

- 右键呼出菜单，选择 **提交 Master...**

![img](03%20Docker/fd368c2a34ceaf8.png)

- 点击 **全部** 并填入 **日志信息**

![img](03%20Docker/2f119d987ffbcfc.png)

- 点击 **提交并推送**

![img](03%20Docker/6c527a2b14d7a15.png)

- 成功后的效果图

![img](03%20Docker/c4c8b857c304a86.png)

## 查看 GitLab 确认提交成功

![img](03%20Docker/8f046228b43397a.png)

完成





# 12 Docker Compose 部署 Nexus

## 什么是 Nexus

Nexus 是一个强大的 Maven 仓库管理器，极大地简化了内部仓库的维护和外部仓库的访问。2016 年 4 月 6 日 Nexus 3.0 版本发布，相较 2.x 版本有了很大的改变

- 对低层代码进行了大规模重构，提升性能，增加可扩展性以及改善用户体验。
- 升级界面，极大的简化了用户界面的操作和管理。
- 提供新的安装包，让部署更加简单。
- 增加对 Docker, NeGet, npm, Bower 的支持。
- 提供新的管理接口，以及增强对自动任务的管理。

## 部署 Nexus

我们使用 Docker 来安装和运行 Nexus，`docker-compose.yml` 配置如下：

```
version: '3.1'
services:
  nexus:
    restart: always
    image: sonatype/nexus3
    container_name: nexus
    ports:
      - 80:8081
    volumes:
      - data:/nexus-data
volumes:
  data:
```

## 验证安装是否成功

- **地址：** [http://ip:port/](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cDovL2lwOnBvcnQv)
- **用户名：** admin
- **密码：** admin123

> **注意：** 新版本密码在 `cat /var/lib/docker/volumes/nexus_data/_data/admin.password`

![img](03%20Docker/7d15eefb5020544.png)

## Maven 仓库介绍

### 代理仓库(Proxy Repository)

- 第三方仓库
  - **maven-central**
  - **nuget.org-proxy**
- 版本策略(Version Policy)
  - **Release：** 正式版本
  - **Snapshot：** 快照版本
  - **Mixed：** 混合模式
- 布局策略(Layout Policy)
  - **Strict：** 严格
  - **Permissive：** 宽松

### 宿主仓库(Hosted Repository)

- 存储本地上传的组件和资源的
  - **maven-releases**
  - **maven-snapshots**
  - **nuget-hosted**
- 部署策略(Deployment Policy)
  - **Allow Redeploy：** 允许重新部署
  - **Disable Redeploy：** 禁止重新部署
  - **Read-Only：** 只读

### 仓库组(Repository Group)

通常包含了多个代理仓库和宿主仓库，在项目中只要引入仓库组就可以下载到代理仓库和宿主仓库中的包

- **maven-public**
- **nuget-group**

## 在项目中使用 Nexus

### 配置认证信息

在 Maven `settings.xml` 中添加 Nexus 认证信息 (**servers** 节点下)

```
<server>
  <id>nexus-releases</id>
  <username>admin</username>
  <password>admin123</password>
</server>
<server>
  <id>nexus-snapshots</id>
  <username>admin</username>
  <password>admin123</password>
</server>
```

### Snapshots 与 Releases 的区别

- **nexus-releases：** 用于发布 Release 版本
- **nexus-snapshots：** 用于发布 Snapshot 版本（快照版）

Release 版本与 Snapshot 定义

```
Release: 1.0.0/1.0.0-RELEASE
Snapshot: 1.0.0-SNAPSHOT
```

- 在项目 `pom.xml` 中设置的版本号添加 `SNAPSHOT` 标识的都会发布为 `SNAPSHOT` 版本，没有 `SNAPSHOT` 标识的都会发布为 `RELEASE` 版本。
- `SNAPSHOT` 版本会自动加一个时间作为标识，如：`1.0.0-SNAPSHOT` 发布后为变成 `1.0.0-SNAPSHOT-20180522.123456-1.jar`

### 配置自动化部署

在 `pom.xml` 中添加如下代码

```
<distributionManagement>  
  <repository>  
    <id>nexus-releases</id>  
    <name>Nexus Release Repository</name>  
    <url>http://127.0.0.1:8081/repository/maven-releases/</url>  
  </repository>  
  <snapshotRepository>  
    <id>nexus-snapshots</id>  
    <name>Nexus Snapshot Repository</name>  
    <url>http://127.0.0.1:8081/repository/maven-snapshots/</url>  
  </snapshotRepository>  
</distributionManagement> 

```

**注意事项**

- ID 名称必须要与 `settings.xml` 中 Servers 配置的 ID 名称保持一致
- 项目版本号中有 `SNAPSHOT` 标识的，会发布到 Nexus Snapshots Repository， 否则发布到 Nexus Release Repository，并根据 ID 去匹配授权账号

### 部署到仓库

```
mvn deploy
```

### 配置代理仓库

```
<repositories>
    <repository>
        <id>nexus</id>
        <name>Nexus Repository</name>
        <url>http://127.0.0.1:8081/repository/maven-public/</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <releases>
            <enabled>true</enabled>
        </releases>
    </repository>
</repositories>
<pluginRepositories>
    <pluginRepository>
        <id>nexus</id>
        <name>Nexus Plugin Repository</name>
        <url>http://127.0.0.1:8081/repository/maven-public/</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <releases>
            <enabled>true</enabled>
        </releases>
    </pluginRepository>
</pluginRepositories>
```

## 扩展阅读

### 手动上传第三方依赖

Nexus 3.1.x 开始支持页面上传第三方依赖功能，以下为手动上传命令

```
# 如第三方JAR包：aliyun-sdk-oss-2.2.3.jar
mvn deploy:deploy-file 
  -DgroupId=com.aliyun.oss 
  -DartifactId=aliyun-sdk-oss 
  -Dversion=2.2.3 
  -Dpackaging=jar 
  -Dfile=D:\aliyun-sdk-oss-2.2.3.jar 
  -Durl=http://127.0.0.1:8081/repository/maven-3rd/ 
  -DrepositoryId=nexus-releases
```

**注意事项**

- 建议在上传第三方 JAR 包时，创建单独的第三方 JAR 包管理仓库，便于管理有维护。（maven-3rd）
- `-DrepositoryId=nexus-releases` 对应的是 `settings.xml` 中 **Servers** 配置的 ID 名称。（授权）



# 13 Docker Compose 部署Harbor

## 什么是 Harbor

Harbor 是一个用于存储和分发 Docker 镜像的企业级 Registry 服务器，通过添加一些企业必需的功能特性，例如安全、标识和管理等，扩展了开源 Docker Distribution。作为一个企业级私有 Registry 服务器，Harbor 提供了更好的性能和安全。提升用户使用 Registry 构建和运行环境传输镜像的效率。Harbor 支持安装在多个 Registry 节点的镜像资源复制，镜像全部保存在私有 Registry 中， 确保数据和知识产权在公司内部网络中管控。另外，Harbor 也提供了高级的安全特性，诸如用户管理，访问控制和活动审计等。

## Harbor 特性

- **基于角色的访问控制 ：** 用户与 Docker 镜像仓库通过 “项目” 进行组织管理，一个用户可以对多个镜像仓库在同一命名空间（project）里有不同的权限。
- **镜像复制 ：** 镜像可以在多个 Registry 实例中复制（同步）。尤其适合于负载均衡，高可用，混合云和多云的场景。
- **图形化用户界面 ：** 用户可以通过浏览器来浏览，检索当前 Docker 镜像仓库，管理项目和命名空间。
- **AD/LDAP 支持 ：** Harbor 可以集成企业内部已有的 AD/LDAP，用于鉴权认证管理。
- **审计管理 ：** 所有针对镜像仓库的操作都可以被记录追溯，用于审计管理。
- **国际化 ：** 已拥有英文、中文、德文、日文和俄文的本地化版本。更多的语言将会添加进来。
- **RESTful API ：** RESTful API 提供给管理员对于 Harbor 更多的操控，使得与其它管理软件集成变得更容易。
- **部署简单 ：** 提供在线和离线两种安装工具， 也可以安装到 vSphere 平台 (OVA 方式) 虚拟设备。

## Harbor 组件

- **Proxy：** Harbor 的 registry, UI, token 等服务，通过一个前置的反向代理统一接收浏览器、Docker 客户端的请求，并将请求转发给后端不同的服务。

- **Registry：** 负责储存 Docker 镜像，并处理 docker push/pull 命令。由于我们要对用户进行访问控制，即不同用户对 Docker image 有不同的读写权限，Registry 会指向一个 token 服务，强制用户的每次 docker pull/push 请求都要携带一个合法的 token, Registry 会通过公钥对 token 进行解密验证。

- Core services：

   

  这是 Harbor 的核心功能，主要提供以下服务：

  - **UI：** 提供图形化界面，帮助用户管理 registry 上的镜像（image）, 并对用户进行授权。
  - **WebHook：** 为了及时获取 registry 上 image 状态变化的情况， 在 Registry 上配置 webhook，把状态变化传递给 UI 模块。
  - **Token：** 负责根据用户权限给每个 docker push/pull 命令签发 token. Docker 客户端向 - Registry 服务发起的请求，如果不包含 token，会被重定向到这里，获得 token 后再重新向 Registry 进行请求。

- **Database：** 为 core services 提供数据库服务，负责储存用户权限、审计日志、Docker image 分组信息等数据。

- **Job Services：** 提供镜像远程复制功能，可以把本地镜像同步到其他 Harbor 实例中。

- **Log Collector：** 为了帮助监控 Harbor 运行，负责收集其他组件的 log，供日后进行分析。

![img](03%20Docker/3eb420c96b75b44.png)

## 安装 Harbor

[官方 GitHub](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cHM6Ly9naXRodWIuY29tL2dvaGFyYm9yL2hhcmJvcg==) 上下载最新离线安装版（我已经下载并放置在群分享的 **Linux** 目录下）并上传至服务器

### 解压安装包

```
tar -zxvf harbor-offline-installer-v1.8.0.tgz
# 输出如下
harbor/harbor.v1.8.0.tar.gz
harbor/prepare
harbor/LICENSE
harbor/install.sh
harbor/harbor.yml
```

### 修改配置文件

```
vi harbor.yml
# 修改为域名或你服务器 IP
hostname: 192.168.141.150
```

### 执行安装脚本

```
./install.sh
# 输出如下
[Step 0]: checking installation environment ...
Note: docker version: 18.09.6
Note: docker-compose version: 1.24.0
[Step 1]: loading Harbor images ...
23d9f72a5270: Loading layer [==================================================>]  33.25MB/33.25MB
1d4a1da12c02: Loading layer [==================================================>]  50.51MB/50.51MB
8eb1a006f3b0: Loading layer [==================================================>]  3.584kB/3.584kB
41b6f75847f4: Loading layer [==================================================>]  3.072kB/3.072kB
ec9bd6e4d4e8: Loading layer [==================================================>]   2.56kB/2.56kB
6d852bb664c2: Loading layer [==================================================>]  3.072kB/3.072kB
0e4ed2b5a5b8: Loading layer [==================================================>]  3.584kB/3.584kB
8dfb2b644f30: Loading layer [==================================================>]  12.29kB/12.29kB
Loaded image: goharbor/harbor-log:v1.8.0
d8c53538042b: Loading layer [==================================================>]  63.34MB/63.34MB
1b5fb7ee22e0: Loading layer [==================================================>]  47.96MB/47.96MB
a8bdca5e9d71: Loading layer [==================================================>]  6.656kB/6.656kB
f7cec940b52c: Loading layer [==================================================>]  2.048kB/2.048kB
301a4a2af7db: Loading layer [==================================================>]   7.68kB/7.68kB
e588e1e3a775: Loading layer [==================================================>]   2.56kB/2.56kB
539f28a5d0ea: Loading layer [==================================================>]   2.56kB/2.56kB
8b4a72241226: Loading layer [==================================================>]   2.56kB/2.56kB
Loaded image: goharbor/harbor-db:v1.8.0
c88db349fb2f: Loading layer [==================================================>]  8.972MB/8.972MB
1f2d4d72bba2: Loading layer [==================================================>]  35.77MB/35.77MB
dddbcf598df5: Loading layer [==================================================>]  2.048kB/2.048kB
0ced476c2d9c: Loading layer [==================================================>]  3.072kB/3.072kB
af24eb0bf40b: Loading layer [==================================================>]  35.77MB/35.77MB
Loaded image: goharbor/chartmuseum-photon:v0.8.1-v1.8.0
b185d348bd7d: Loading layer [==================================================>]   2.56kB/2.56kB
f032ded7f92e: Loading layer [==================================================>]  1.536kB/1.536kB
c6c822edbc47: Loading layer [==================================================>]   66.9MB/66.9MB
73ef3c4363bf: Loading layer [==================================================>]  39.75MB/39.75MB
0c490e002448: Loading layer [==================================================>]  144.4kB/144.4kB
31afe2abafb4: Loading layer [==================================================>]  3.004MB/3.004MB
Loaded image: goharbor/prepare:v1.8.0
257ebcc1c9c4: Loading layer [==================================================>]  8.967MB/8.967MB
7579d3c94fca: Loading layer [==================================================>]  38.68MB/38.68MB
323611f7dd17: Loading layer [==================================================>]  38.68MB/38.68MB
Loaded image: goharbor/harbor-jobservice:v1.8.0
587a5757a7f6: Loading layer [==================================================>]  3.548MB/3.548MB
Loaded image: goharbor/nginx-photon:v1.8.0
a61ab2060e6e: Loading layer [==================================================>]  8.967MB/8.967MB
25359ae00f57: Loading layer [==================================================>]  5.143MB/5.143MB
610a1668f8bf: Loading layer [==================================================>]  15.13MB/15.13MB
db2252abd9e0: Loading layer [==================================================>]  26.47MB/26.47MB
4f406312560b: Loading layer [==================================================>]  22.02kB/22.02kB
1cee0947e5a7: Loading layer [==================================================>]  3.072kB/3.072kB
48db2b9b0752: Loading layer [==================================================>]  46.74MB/46.74MB
Loaded image: goharbor/notary-server-photon:v0.6.1-v1.8.0
aaf447150765: Loading layer [==================================================>]    113MB/113MB
6835441e1a1d: Loading layer [==================================================>]  10.94MB/10.94MB
9f4739e3a532: Loading layer [==================================================>]  2.048kB/2.048kB
928f489135f0: Loading layer [==================================================>]  48.13kB/48.13kB
1495a1a09ada: Loading layer [==================================================>]  3.072kB/3.072kB
1a5f5b141717: Loading layer [==================================================>]  10.99MB/10.99MB
Loaded image: goharbor/clair-photon:v2.0.8-v1.8.0
66006ea937c6: Loading layer [==================================================>]  337.8MB/337.8MB
d272ba122880: Loading layer [==================================================>]  106.5kB/106.5kB
Loaded image: goharbor/harbor-migrator:v1.8.0
05bc5efb1724: Loading layer [==================================================>]  8.967MB/8.967MB
af3a6f89469a: Loading layer [==================================================>]  46.85MB/46.85MB
452d238b3e48: Loading layer [==================================================>]  5.632kB/5.632kB
36e1cb2d6ffa: Loading layer [==================================================>]  27.14kB/27.14kB
5385ffb8451e: Loading layer [==================================================>]  46.85MB/46.85MB
Loaded image: goharbor/harbor-core:v1.8.0
268091c30a67: Loading layer [==================================================>]  71.66MB/71.66MB
4433bcd802e7: Loading layer [==================================================>]  3.072kB/3.072kB
420b26399278: Loading layer [==================================================>]   59.9kB/59.9kB
8864c4b9ac3d: Loading layer [==================================================>]  61.95kB/61.95kB
Loaded image: goharbor/redis-photon:v1.8.0
63645c97bf5d: Loading layer [==================================================>]  8.968MB/8.968MB
ccb295818ad9: Loading layer [==================================================>]  3.072kB/3.072kB
1ec2d1eefa8f: Loading layer [==================================================>]   2.56kB/2.56kB
b88acf0f9f5f: Loading layer [==================================================>]   20.1MB/20.1MB
0e7375de12e6: Loading layer [==================================================>]   20.1MB/20.1MB
Loaded image: goharbor/registry-photon:v2.7.1-patch-2819-v1.8.0
444b0c8bfeee: Loading layer [==================================================>]  3.548MB/3.548MB
ed0415346760: Loading layer [==================================================>]  6.568MB/6.568MB
572bd51089e0: Loading layer [==================================================>]  160.8kB/160.8kB
1410c2919a92: Loading layer [==================================================>]    215kB/215kB
8ecdca210598: Loading layer [==================================================>]  3.584kB/3.584kB
Loaded image: goharbor/harbor-portal:v1.8.0
7fb66591fb58: Loading layer [==================================================>]  8.968MB/8.968MB
42ec4a6394bf: Loading layer [==================================================>]  3.072kB/3.072kB
be6c2180cb57: Loading layer [==================================================>]   20.1MB/20.1MB
d956d9e974c5: Loading layer [==================================================>]  3.072kB/3.072kB
e2e0b4f17ad8: Loading layer [==================================================>]  7.465MB/7.465MB
7e29d670afe9: Loading layer [==================================================>]  27.56MB/27.56MB
Loaded image: goharbor/harbor-registryctl:v1.8.0
453732ea69d4: Loading layer [==================================================>]  13.72MB/13.72MB
c985f3824f33: Loading layer [==================================================>]  26.47MB/26.47MB
76eaa2763221: Loading layer [==================================================>]  22.02kB/22.02kB
0ef55a752948: Loading layer [==================================================>]  3.072kB/3.072kB
c5749b90723d: Loading layer [==================================================>]  45.33MB/45.33MB
Loaded image: goharbor/notary-signer-photon:v0.6.1-v1.8.0
[Step 2]: preparing environment ...
prepare base dir is set to /usr/local/docker/harbor/harbor
Generated configuration file: /config/log/logrotate.conf
Generated configuration file: /config/nginx/nginx.conf
Generated configuration file: /config/core/env
Generated configuration file: /config/core/app.conf
Generated configuration file: /config/registry/config.yml
Generated configuration file: /config/registryctl/env
Generated configuration file: /config/db/env
Generated configuration file: /config/jobservice/env
Generated configuration file: /config/jobservice/config.yml
Generated and saved secret to file: /secret/keys/secretkey
Generated certificate, key file: /secret/core/private_key.pem, cert file: /secret/registry/root.crt
Generated configuration file: /compose_location/docker-compose.yml
Clean up the input dir
[Step 3]: starting Harbor ...
Creating network "harbor_harbor" with the default driver
Creating harbor-log ... done
Creating harbor-db   ... done
Creating registryctl ... done
Creating redis       ... done
Creating registry    ... done
Creating harbor-core ... done
Creating harbor-jobservice ... done
Creating harbor-portal     ... done
Creating nginx             ... done
✔ ----Harbor has been installed and started successfully.----
Now you should be able to visit the admin portal at http://192.168.141.150. 
For more details, please visit https://github.com/goharbor/harbor .
```

### 验证安装是否成功

通过浏览器访问 [http://192.168.141.150](http://www.qfdmy.com/wp-content/themes/quanbaike/go.php?url=aHR0cDovLzE5Mi4xNjguMTQxLjE1MA==) ，看到登录页面

![img](03%20Docker/53f967b3135ca78.png)

输入账号 `admin`，密码 `Harbor12345`，登录成功后

![img](03%20Docker/7de03f6aab763e8.png)

## Harbor 启动和停止

Harbor 的日常运维管理是通过 docker-compose 来完成的，Harbor 本身有多个服务进程，都放在 docker 容器之中运行，我们可以通过 `docker ps` 命令查看。

```

docker ps | grep goharbor
# 输出如下
07b401504357        goharbor/nginx-photon:v1.8.0                        "nginx -g 'daemon of…"   23 minutes ago      Up 23 minutes (healthy)   0.0.0.0:80->80/tcp          nginx
050f39a147bc        goharbor/harbor-portal:v1.8.0                       "nginx -g 'daemon of…"   23 minutes ago      Up 23 minutes (healthy)   80/tcp                      harbor-portal
305077bc0a3e        goharbor/harbor-jobservice:v1.8.0                   "/harbor/start.sh"       23 minutes ago      Up 23 minutes                                         harbor-jobservice
4eb33b09b268        goharbor/harbor-core:v1.8.0                         "/harbor/start.sh"       23 minutes ago      Up 23 minutes (healthy)                               harbor-core
e9efb7a6abf9        goharbor/registry-photon:v2.7.1-patch-2819-v1.8.0   "/entrypoint.sh /etc…"   24 minutes ago      Up 23 minutes (healthy)   5000/tcp                    registry
f9bc75d47752        goharbor/harbor-registryctl:v1.8.0                  "/harbor/start.sh"       24 minutes ago      Up 23 minutes (healthy)                               registryctl
76d33d1755f6        goharbor/redis-photon:v1.8.0                        "docker-entrypoint.s…"   24 minutes ago      Up 23 minutes             6379/tcp                    redis
3870b3b93f46        goharbor/harbor-db:v1.8.0                           "/entrypoint.sh post…"   24 minutes ago      Up 23 minutes (healthy)   5432/tcp                    harbor-db
6e848e4d8bc2        goharbor/harbor-log:v1.8.0                          "/bin/sh -c /usr/loc…"   24 minutes ago      Up 24 minutes (healthy)   127.0.0.1:1514->10514/tcp   harbor-log

# 启动
docker-compose start
# 停止
docker-comose stop
# 重启
docker-compose restart
```

**说明：**

- **nginx：** nginx 负责流量转发和安全验证，对外提供的流量都是从 nginx 中转，所以开放 https 的 443 端口，它将流量分发到后端的 ui 和正在 docker 镜像存储的 docker registry。
- **harbor-jobservice：** harbor-jobservice 是 harbor 的 job 管理模块，job 在 harbor 里面主要是为了镜像仓库之前同步使用的；
- **harbor-ui：** harbor-ui 是 web 管理页面，主要是前端的页面和后端 CURD 的接口；
- **registry：** registry 就是 docker 原生的仓库，负责保存镜像。
- **harbor-adminserver：** harbor-adminserver 是 harbor 系统管理接口，可以修改系统配置以及获取系统信息。
- **harbor-db：** harbor-db 是 harbor 的数据库，这里保存了系统的 job 以及项目、人员权限管理。由于本 harbor 的认证也是通过数据，在生产环节大多对接到企业的 ldap 中；
- **harbor-log：** harbor-log 是 harbor 的日志服务，统一管理 harbor 的日志。通过 inspect 可以看出容器统一将日志输出的 syslog。

这几个容器通过 Docker link 的形式连接在一起，这样，在容器之间可以通过容器名字互相访问。对终端用户而言，只需要暴露 proxy （即 Nginx）的服务端口。

## 配置客户端

在 `/etc/docker/daemon.json` 中增加如下内容（如果文件不存在请新建该文件）

```
{
  "registry-mirrors": [
    "https://registry.docker-cn.com"
  ],
  "insecure-registries": [
    "192.168.141.150"
  ]
}
```

> **注意：** 该文件必须符合 JSON 规范，否则 Docker 将不能启动。

重启服务

```
systemctl daemon-reload
systemctl restart docker
```

## 检查客户端配置是否生效

使用 `docker info` 命令手动检查，如果从配置中看到如下内容，说明配置成功

```
Insecure Registries:
 192.168.141.150
 127.0.0.0/8
```

## Harbor 上传镜像

### 新建项目

我们以推送 Nginx 为例，首先需要在 Harbor 上创建一个 **公开/私有** 的项目

![img](03%20Docker/d7e5f534e5cbec2.png)

![img](03%20Docker/2f6da60cb5a1de1.png)

### 推送镜像

![img](03%20Docker/84d801ea113d5a4.png)

```
# 在项目中标记镜像
docker tag nginx 192.168.141.150/myshop/nginx:latest
# 登录 Harbor
docker login 192.168.141.150 -u admin -p Harbor12345
# 推送镜像到项目
docker push 192.168.141.150/myshop/nginx:latest
```

### 查看镜像

![img](03%20Docker/3bf9aee4227c707.png)

![img](03%20Docker/7e33648d6668b4e.png)

## Harbor 下载镜像

在其它机器下载镜像只需要配置好客户端即可

```
docker pull 192.168.141.150/myshop/nginx:latest
```





# 14 Docker Compose 网络设置



## 概述

默认情况下，Compose 会为我们的应用创建一个网络，服务的每个容器都会加入该网络中。这样，容器就可被该网络中的其他容器访问，不仅如此，**该容器还能以服务名称作为 Hostname 被其他容器访问**。

默认情况下，应用程序的网络名称基于 Compose 的工程名称，而项目名称基于 `docker-compose.yml` 所在目录的名称。如需修改工程名称，可使用 `--project-name` 标识或 `COMPOSE_PORJECT_NAME` 环境变量。

假如一个应用程序在名为 myapp 的目录中，并且 `docker-compose.yml` 如下所示：

```
version: '2'
services:
  web:
    build: .
    ports:
      - "8000:8000"
  db:
    image: postgres
```

当我们运行 `docker-compose up` 时，将会执行以下几步：

- 创建一个名为 **myapp_default** 的网络
- 使用 web 服务的配置创建容器，它以 **web** 这个名称加入网络 myapp_default
- 使用 db 服务的配置创建容器，它以 **db** 这个名称加入网络 myapp_default

容器间可使用服务名称（web 或 db）作为 Hostname 相互访问。例如，web 这个服务可使用 `postgres://db:5432` 访问 db 容器。

当服务的配置发生更改时，可使用 `docker-compose up` 命令更新配置。此时，Compose 会删除旧容器并创建新容器。新容器会以不同的 IP 地址加入网络，名称保持不变。任何指向旧容器的连接都会被关闭，容器会重新找到新容器并连接上去。

## 使用 links

默认情况下，服务之间可使用服务名称相互访问。links 允许我们定义一个别名，从而使用该别名访问其他服务。

```
version: '2'
services:
  web:
    build: .
    links:
      - "db:database"
  db:
    image: postgres
```

## 自定义网络

一些场景下，默认的网络配置满足不了我们的需求，此时我们可使用 networks 命令自定义网络。networks 命令允许我们创建更加复杂的网络拓扑并指定自定义网络驱动和选项。不仅如此，我们还可使用 networks 将服务连接到不是由 Compose 管理的、外部创建的网络。

```
version: '2'
services:
  proxy:
    build: ./proxy
    networks:
      - front
  app:
    build: ./app
    networks:
      - front
      - back
  db:
    image: postgres
    networks:
      - back
networks:
  front:
    # Use a custom driver
    driver: custom-driver-1
  back:
    # Use a custom driver which takes special options
    driver: custom-driver-2
    driver_opts:
      foo: "1"
      bar: "2"
```

其中，proxy 服务与 db 服务隔离，两者分别使用自己的网络；app 服务可与两者通信。使用 networks 命令，即可方便实现服务间的网络隔离与连接。

## 配置默认网络

```
version: '2'
services:
  web:
    build: .
    ports:
      - "8000:8000"
  db:
    image: postgres
networks:
  default:
    # Use a custom driver
    driver: custom-driver-1
```

这样，就可为该应用指定自定义的网络驱动

## 已存在的网络

我们可以预先创建一个名为 myapp 的网络，让 Compose 加入这个新创建的网络，使所有 Compose 可以通信，此时使用 external 选项。

```
# 创建网络
docker network create <Network Name>
# 查看已存在的网络
docker network list

networks:
  default:
    external:
      name: myapp

```

## 一次构建，到处运行



