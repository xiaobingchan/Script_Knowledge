yum -y install wget gcc gcc-c++ ncurses ncurses-devel cmake numactl.x86_64

tar -zxvf mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz -C /usr/local/
mv /usr/local/mysql-5.7.24-linux-glibc2.12-x86_64/ /usr/local/mysql
cd /usr/local/mysql/
cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql
cat >/etc/my.cnf <<EOF
[client]
port=3306
socket=/tmp/mysql.sock
[mysqld]
port=3306
socket=/tmp/mysql.sock
skip-external-locking
key_buffer_size = 16M
max_allowed_packet = 1M
table_open_cache = 64
sort_buffer_size = 512K
net_buffer_length = 8K
read_buffer_size = 256K
read_rnd_buffer_size = 512K
myisam_sort_buffer_size = 8M
basedir=/usr/local/mysql
datadir=/var/lib/mysql
bind-address=0.0.0.0
EOF

useradd mysql
mkdir -p /usr/local/mysql/data
chown mysql:mysql /usr/local/mysql/data
mkdir -p /var/lib/mysql
chown mysql:mysql /var/lib/mysql
mkdir -p /var/log/mariadb
chown mysql:mysql /var/log/mariadb/
touch /var/log/mariadb/mariadb.log
mkdir -p /var/run/mariadb
chown mysql:mysql /var/run/mariadb/
touch /var/run/mariadb/mariadb.pid
mv /var/lib/mysql/ /var/lib/mysql_bak/
cat  >> /etc/profile << EOF
export PATH=\$PATH:/usr/local/mysql/bin:/usr/local/mysql/lib
EOF
source /etc/profile


/usr/local/mysql/bin/mysqld --defaults-file=/etc/my.cnf --user=mysql --initialize-insecure
/etc/init.d/mysql start
mysql_password="123456"
echo "set password=password('${mysql_password}');"| mysql -S /tmp/mysql.sock

# 1，非交互式命令创建dog用户：mysql -h127.0.0.1 -P3306 -uroot -p123456 -e "SHOW VARIABLES LIKE 'validate_password%';" 2>/dev/null
# 2，无警告非交互式命令执行：mysql -u root -p123456 -h 127.0.0.1 -P 3306 -Bse "show plugins;" 2>/dev/null
# 3，创建可外部链接用户dog：CREATE USER 'dog'@'%' IDENTIFIED BY '12345678990';
# 4，删除用户dog：DROP USER 'dog'@'%';
# 5，设置dog用户密码：SET PASSWORD FOR 'dog'@'%' = PASSWORD('1234564e6456456456');
# 6，列出已安装插件：SHOW VARIABLES;
# 7，安装密码插件：INSTALL PLUGIN validate_password SONAME 'validate_password.so';
# 8，MySQL加速连接：vi /etc/my.cnf
[mysqld]
skip-name-resolve
# 9，
# 10，python的mysql高并发方案Tormysql+Tornado
# 10，自动事务化分库分表spanner：https://cloud.google.com/forrester-dbaas/?hl=zh-cn
# 11，查询对应地区的任务数：  select count(*) from t_operation_task where task_area = '08';
# 12，查询相同数据出现的次数：select country as 国家,count(*) as 次数 from table3 group by country;
      查询时间段：select fullName,addedTime FROM t_user where addedTime between  '2017-1-1 00:00:00'  and '2018-1-1 00:00:00'; 
# 13，MySQL重置密码：vi /etc/my.cnf
# 14，创建数据库并授权： 
     CREATE DATABASE `gitea` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
     GRANT ALL PRIVILEGES ON gitea.* TO 'abc'@'localhost' IDENTIFIED BY '12345678' with grant option;
	 flush privileges;
# 15，创建用户并授权：
     CREATE USER 'gitea'@'%' IDENTIFIED BY '123456';
     GRANT ALL PRIVILEGES ON gitea.* TO 'gitea'@'%' IDENTIFIED BY '123456' with grant option;
     flush privileges;
# 16，指定mysql.sock位置：/home/mysql/mariadb/bin/mysql -uioszdhyw -p -S /home/mysql/mariadb/mysql.sock
[mysqld]
skip-grant-tables
# 17，更新root用户密码
update mysql.user set authentication_string=password('123456') where user='root' and Host = 'localhost';
flush privileges;
# 18，清空数据表
truncate table tablename;
delete from tablename where id=0;
# 19，删除有外键约束的数据
SET FOREIGN_KEY_CHECKS=0;  #  禁用外键约束
SET FOREIGN_KEY_CHECKS=1; #  启动外键约束
