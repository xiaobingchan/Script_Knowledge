首先安装jdk环境
#############################################################
rpm -ivh /data/soft/jdk-8u152-linux-x64.rpm
pid="sed -i '/export JAVA_HOME/d' /etc/profile"
eval $pid
pid="sed -i '/export CLASSPATH/d' /etc/profile"
eval $pid
cat >> /etc/profile <<EOF
export JAVA_HOME=/usr/java/jdk1.8.0_152
export CLASSPATH=%JAVA_HOME%/lib:%JAVA_HOME%/jre/lib
export PATH=\$PATH:\$JAVA_HOME/bin
EOF
source /etc/profile
#############################################################

安装jenkins
#############################################################
wget http://pkg.jenkins-ci.org/redhat-stable/jenkins-2.164.3-1.1.noarch.rpm
rpm -ivh jenkins-2.164.3-1.1.noarch.rpm
service jenkins start
firewall-cmd --permanent --zone=public --add-port=8080/tcp
firewall-cmd --reload
访问网址：http://192.168.244.180:8080/
#############################################################

安装gitlab
#############################################################
yum -y install policycoreutils openssh-server openssh-clients postfix
systemctl enable postfix && systemctl start postfix
firewall-cmd --permanent --zone=public --add-port=9091/tcp
firewall-cmd --reload
wget https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el7/gitlab-ce-11.9.9-ce.0.el7.x86_64.rpm
rpm -i gitlab-ce-11.9.9-ce.0.el7.x86_64.rpm

vi  /etc/gitlab/gitlab.rb
external_url 'http://127.0.0.1:9091'  # 配置访问地址
nginx['listen_port'] = 9091  # 配置端口

gitlab-ctl reconfigure
gitlab-ctl restart
free -m
访问 http://127.0.0.1:9091
#############################################################


安装gitee
#############################################################
先安装mysql数据库

wget -O gitea https://dl.gitea.io/gitea/1.8.2/gitea-1.8.2-linux-amd64
chmod +x gitea-1.8.2-linux-amd64
yum -y install git
nohup ./gitea-1.8.2-linux-amd64 web &
firewall-cmd --permanent --zone=public --add-port=3000/tcp
firewall-cmd --reload
#############################################################

1，jenkis新建token：进入http://Jenkins网址/user/root/configure新建TOKEN
2，新建api触发执行bash：item->设置->触发远程构建 (例如,使用脚本)->执行bash
3，安装插件"Maven Integration"和"Build Authorization Token Root"
4，自动安装maven：http://Jenkins网址/configureTools/
5，查询http构建情况：https://blog.csdn.net/boling_cavalry/article/details/85373901
6，Java git自动构建Spring：https://blog.csdn.net/boling_cavalry/article/details/78942408
7，Github提交代码自动构建：https://blog.csdn.net/boling_cavalry/article/details/78943061
8，Gitlab提交代码自动构建：https://www.centos.bz/2017/08/gitlab-jenkins-build-project/
9，Pipline流水线

ssh-keygen -t rsa -C "luyanjie4@gmail.com"
