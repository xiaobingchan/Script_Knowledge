Python官方下载：https://www.python.org/downloads/
Python依赖官方文档：https://pypi.org/
CentOS依赖下载：https://centos.pkgs.org/
Python官方文档：https://docs.python.org/zh-cn/3/
Django官方文档：https://docs.djangoproject.com/zh-hans/2.2/
Tornado官方文档：https://tornado-zh.readthedocs.io/zh/latest/
Flask官方文档：http://docs.jinkan.org/docs/flask/

CentOS/Redhat 部署：

a，先确保OpenSSL版本大于1.0.1
############################################################
rpm -qa | grep openssl
wget http://www.openssl.org/source/openssl-1.0.2j.tar.gz
tar -xzvf openssl-1.0.2j.tar.gz
cd openssl-1.0.2j
./config --prefix=/usr/local/openssl shared zlib
make && make install
echo "export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/openssl/lib" >> /etc/profile
source /etc/profile
mv /usr/bin/openssl  /usr/bin/openssl.old
mv /usr/include/openssl  /usr/include/openssl.old
ln -s /usr/local/openssl/bin/openssl  /usr/bin/openssl
ln -s /usr/local/openssl/include/openssl  /usr/include/openssl
ln -s /usr/local/openssl/lib/libssl.so /usr/local/lib64/libssl.so
strings /usr/local/lib64/libssl.so |grep OpenSSL
echo "/usr/local/openssl/lib" >> /etc/ld.so.conf
ldconfig /etc/ld.so.conf
ldconfig -v
############################################################

b，下载编译安装Python3和PIP3
############################################################
yum install -y zlib-devel bzip2-devel openssl-devel ncurses-devel sqlite-devel libffi-devel expat-devel gdbm-devel readline-devel gcc gcc-c++ python-devel.x86_64 # 安装python3依赖库
rpm -ivh libffi-devel-3.0.13-18.el7.x86_64.rpm
wget https://www.python.org/ftp/python/3.7.0/Python-3.7.0.tgz
tar -zxvf Python-3.7.0.tgz
cd `echo Python-3.7.0.tgz |awk 'BEGIN{FS="/"}''{print $NF}'| awk -F".tgz" '{print $NR}'`
./configure  --prefix=/data/ioszdhyw/soft/python3_7  --with-openssl=/usr/local/openssl # 指定编译安装位置
make && make install  # 编译安装
ln -s /data/ioszdhyw/soft/python3_7/bin/python3 /usr/bin/python3  # 创建python3软链接
ln -s /data/ioszdhyw/soft/python3_7/bin/pip3 /usr/bin/pip3  # 创建pip3软链接
############################################################

1，安装requirements文件：pip install -r requirements.txt
2，安装uWSGI必要安装：yum install python-devel.x86_64
3，pip升级包：pip3 install --upgrade django
4，升级pip：python3 -m pip3 install --upgrade pip3
5，查询pip版本：pip3 -V

