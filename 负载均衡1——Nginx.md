1，普通安装，开监控模块

yum install -y patch openssl pcre pcre-devel make cmake gcc gcc-c++ gcc-g77 flex bison file libtool libtool-libs autoconf kernel-devel libjpeg libjpeg-devel libpng libpng-devel libpng10 libpng10-devel gd gd-devel freetype freetype-devel libxml2 libxml2-devel zlib zlib-devel glib2 glib2-devel bzip2 bzip2-devel libevent libevent-devel ncurses ncurses-devel curl curl-devel e2fsprogs e2fsprogs-devel krb5 krb5-devel libidn libidn-devel openssl openssl-devel vim-minimal nano fonts-chinese gettext gettext-devel ncurses-devel gmp-devel pspell-devel unzip libcap diffutils

userdel www
groupdel www
groupadd -f www
useradd -g www www
tar zxvf /data/soft/pcre-8.38.tar.gz -C /data/ioszdhyw/soft
tar zxvf /data/soft/nginx-1.12.2.tar.gz -C /data/ioszdhyw/soft
cd  /data/ioszdhyw/soft/`echo /data/soft/nginx-1.12.2.tar.gz |awk 'BEGIN{FS="/"}''{print $NF}'| awk -F".tar" '{print $NR}'`
./configure --user=www --group=www --prefix=/data/ioszdhyw/soft/nginx --with-http_stub_status_module --with-http_ssl_module --with-http_gzip_static_module --with-ipv6 --with-http_sub_module --with-pcre=/data/ioszdhyw/soft/pcre-8.38/ --with-pcre-jit
make && make install

vi /usr/local/nginx/conf/nginx.conf 
##################################################
location /nginx_status {
    stub_status on;
	access_log off;
}

2，带文件服务模块（mongoDB版本必须是2.6.9）

yum -y install pcre-devel openssl-devel zlib-devel git gcc gcc-c++
tar -xzvf nginx-1.14.2.tar.gz
tar -xvf nginx-gridfs.tar.gz
cd nginx-1.14.2
./configure --prefix=/usr/local/nginx   --with-openssl=/usr/include/openssl --add-module=/data/soft/nginx_mongodb/nginx-gridfs
vi objs/Makefile  # 去掉-Werror
make -j8 && make install -j8

vi /usr/local/nginx/conf/nginx.conf 
##################################################
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

3，端口转发
 location / {  
         proxy_pass  http://localhost:8080/;
        }
4，负载均衡
upstream task_api{
        ip_hash;  
        server 127.0.0.1:1001;
        server 127.0.0.1:1002;
    }

curl http://127.0.0.1/api



