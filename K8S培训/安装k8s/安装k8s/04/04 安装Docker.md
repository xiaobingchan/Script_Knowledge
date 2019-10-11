[TOC]

安装几个必要的软件

```
yum install -y yum-utils device-mapper-persistent-data lvm2
```

查看所有仓库中所有docker版本，并选择特定版本安装

```
yum list docker-ce --showduplicates | sort -r
```

安装docker-ce

```
yum install -y docker-ce-17.12.1.ce
```

启动Docker

```
systemctl start docker
```

设置为开机自动启动

```
systemctl enable docker
```

docker info及docker —version验证安装是否成功

```
docker info
docker --version
```

配置阿里镜像加速

可以通过修改daemon配置文件/etc/docker/daemon.json来使用加速器

```
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://1x7jbfu7.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

拉一个测试docker看看效果

```
docker run hello-world
```

![1568275382121](04%20%E5%AE%89%E8%A3%85Docker.assets/1568275382121.png)