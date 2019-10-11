yum remove zabbix-release
yum install http://repo.zabbix.com/zabbix/3.2/rhel/6/x86_64/zabbix-release-3.2-1.el6.noarch.rpm
yum clean all

yum install -y zabbix-server-mysql zabbix-web-mysql
yum install -y zabbix-agent


wget http://repo.mysql.com/mysql-community-release-el7-5.noarch.rpm
rpm -ivh mysql-community-release-el7-5.noarch.rpm
yum repolist enabled | grep "mysql.*-community.*"
yum install mysql-community-server -y
systemctl enable mysqld
systemctl start mysqld

mysql -uroot -p
############################################################################################
create database zabbix character set utf8 collate utf8_bin;
grant all privileges on zabbix.* to zabbix@localhost identified by 'zabbix';
flush privileges;
############################################################################################

cd /usr/share/doc/zabbix-server-mysql-3.2.11/
zcat create.sql.gz | mysql -uzabbix -pzabbix -b zabbix

vim /etc/zabbix/zabbix_server.conf
############################################################################################
DBHost=localhost
DBName=zabbix
DBUser=zabbix
DBPassword=zabbix
############################################################################################
systemctl start zabbix-server
systemctl enable zabbix-server
systemctl start zabbix-agent
systemctl enable zabbix-agent

vim /etc/httpd/conf.d/zabbix.conf
############################################################################################
php_value date.timezone Asia/Shanghai
############################################################################################
systemctl enable httpd
systemctl start httpd

# 访问 http://118.89.23.220/zabbix，账号：admin，密码：zabbix


参考笔记：https://blog.imdst.com/zabbix-jian-kong-tian-jia-jian-kong-fu-wu-qi/

1，官方中文文档：https://www.zabbix.com/documentation/3.4/zh/manual/
