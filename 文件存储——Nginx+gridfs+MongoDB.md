1.	安装nginx，下载好安装包nginx和nginx-gridfs，此次安装采用nginx1.14.2.
wget http://nginx.org/download/nginx-1.14.2.tar.gz 
# 下载nginx1.14.2.

# 制作 nginx-gridfs
git clone https://github.com/mdirolf/nginx-gridfs.git
cd nginx-gridfs
git checkout v0.8
git submodule init
git submodule update

2，安装依赖包
mkdir -p /data/soft/nginx-mongodb
cd /data/soft/nginx-mongodb   # 将安装包放在这个目录
yum -y install pcre-devel openssl-devel zlib-devel git gcc gcc-c++
tar -xzvf nginx-1.14.2.tar.gz
tar -xvf nginx-gridfs.tar.gz
cd nginx-1.14.2
./configure --prefix=/usr/local/nginx   --with-openssl=/usr/include/openssl --add-module=/data/soft/nginx_mongodb/nginx-gridfs
vi objs/Makefile  # 去掉-Werror
make -j8 && make install -j8
注：如遇报错去掉objs/Makefile里的-Werror，再次编译即可
3.配置nginx.conf
cat /proc/cpuinfo     # 查看CPU核数，根据核数配置Nginx的worker_processes数量
rm -rf /usr/local/nginx/conf/nginx.conf
vi /usr/local/nginx/conf/nginx.conf 
/usr/local/nginx/conf/nginx.conf添加下面内容，注意！将IP换成实际的访问IP

user  root;
worker_processes  4;

error_log  logs/error.log;

pid        logs/nginx.pid;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;
    server {
        listen       80;
        server_name  localhost;
        location / {
            root   html;
            index  index.html index.htm;
        }
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
      }
    server {
        listen       800;
        server_name  192.168.240.113;
        location /file/ {
            gridfs FILEDB
            root_collection=fs
            field=filename
            type=string
            user=foo
            pass=bar;
                mongo 192.168.240.113:20000;
                access_log  logs/gridfs.access.log;
                error_log   logs/gridfs.error.log;
       }
}
}


4，	安装mongodb（一定要用MongoDB 2.6.9版本）
cat /etc/redhat-release  # 查看系统版本信息，下载相对应的安装包
wget http://downloads.mongodb.org/linux/mongodb-linux-x86_64-rhel70-2.6.9.tgz
下载好安装包，此次安装版本mongodb-linux-x86_64-rhel70-2.6.9.tgz
解压：
cd /data/soft/nginx-mongodb
tar xzvf mongodb-linux-x86_64-rhel70-2.6.9.tgz -C /usr/local/
创建所需目录并授权：
cd /usr/local
mv mongodb-linux-x86_64-rhel70-2.6.9 mongodb_file
cd mongodb_file
mkdir data 
mkdir log
mkdir etc
chown -R 777 data log etc
touch /usr/local/mongodb_file/log/mongo.log
chown -R 777 /usr/local/mongodb_file/log/mongo.log
添加配置文件：
cd etc 
vi mongodb.conf
dbpath=/usr/local/mongodb_file/data
logpath=/usr/local/mongodb_file/log/mongo.log
logappend=true
journal=true
quiet=true
fork=true
port=20000
auth=true
bind_ip = 0.0.0.0

2.	启动mongodb
/usr/local/mongodb_file/bin/mongod -f /usr/local/mongodb_file/etc/mongodb.conf

3，设置密码：
/usr/local/mongodb_file/bin/mongo  –-port=20000
use admin
db.createUser({user:"useradmin",pwd:"123456",roles:[{role:"userAdminAnyDatabase",db:"admin"}]})
db.auth("useradmin","123456")
use FILEDB
db.createUser({user:"dx",pwd:"dx",roles:[{role:"dbOwner",db:"dx"}]})
db.auth("dx","dx")


至此，环境搭建完成。
测试nginx请求mongodb,注意！将IP换成实际的访问IP
mongodb上传图片：
cd /data/soft/nginx-mongodb/
/usr/local/mongodb_file/bin/mongofiles -h 10.92.215.235 -u dx -p dx -d FILEDB --port 20000 put 1.jpg
# 注意文件名称千万不能有路径后缀和减号！！！
/usr/local/mongodb_file/bin/mongofiles -h 10.92.215.235 -u dx -p dx -d FILEDB --port 20000 get 2.jpg
# 检查是否成功存储2.jpg
/usr/local/mongodb_file/bin/mongofiles -h 10.92.215.235 -u dx -p dx -d FILEDB --port 20000 list
# 检查是否文件列表

3.	启动nginx
开防火墙：
firewall-cmd --zone=public --add-port=80/tcp --permanent
firewall-cmd --reload
firewall-cmd --zone=public --add-port=20000/tcp --permanent
firewall-cmd --reload
检查配置：
/usr/local/nginx/sbin/nginx -t
启动：
/usr/local/nginx/sbin/nginx
检查进程跟端口，一定要确保worker_processes的子进程要启动成功！：
ps -ef|grep nginx 
netstat -lntp|grep nginx  
 

nginx访问,注意！将IP换成实际的访问IP：
http://10.92.215.236/file/1.jpg (file为nginx配置)
