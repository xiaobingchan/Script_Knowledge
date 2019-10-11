主节点：192.168.244.176 master
节点1：192.168.244.128 node1
节点2：192.168.244.129 node2

cat  >> /etc/sysconfig/selinux  << EOF
SELINUX=disabled
EOF

systemctl stop firewalld
systemctl disable firewalld

cat  >> /etc/hosts << EOF
192.168.244.176 master
192.168.244.128 node1
192.168.244.129 node2
EOF

yum install -y lsof.x86_64
yum install -y rsync.x86_64
yum install -y boost-devel.x86_64
rpm -ivh galera-3-25.3.28-1.el7.x86_64.rpm

tar -xzvf mariadb-10.1.34-linux-x86_64.tar.gz -C /usr/local
mv /usr/local/mariadb-10.1.34-linux-x86_64 /usr/local/mysql-cluster
useradd mysql

mkdir -p /usr/local/mysql-cluster/binlog
chmod -R 777 /usr/local/mysql-cluster/binlog
mkdir -p /usr/local/mysql-cluster/log/
chmod -R 777 /usr/local/mysql-cluster/log/

cat > /etc/my.cnf << EOF
[server]
[client]
port = 13306
[mysqld]
bind-address=0.0.0.0
port = 13306 
server_id=176
datadir=/usr/local/mysql-cluster/data
user=mysql  
skip-external-locking  
skip-name-resolve  
character-set-server=utf8
[galera]  
wsrep_causal_reads=ON  #节点应用完事务才返回查询请求  
bind-address=0.0.0.0
wsrep_node_address=192.168.244.176
wsrep_provider_options="gcache.size=1G"#同步复制缓冲池  
wsrep_certify_nonPK=ON   #为没有显式申明主键的表生成一个用于certificationtest的主键，默认为ON  
query_cache_size=0           #关闭查询缓存  
wsrep_on=ON   #开启全同步复制模式  
wsrep_provider=/usr/lib64/galera-3/libgalera_smm.so
wsrep_cluster_name=master
wsrep_cluster_address="gcomm://192.168.244.128,192.168.244.129,192.168.244.176"  #本机ip千万不能放第一位
wsrep_node_name=master
binlog_format=row  
default_storage_engine=InnoDB  
innodb_autoinc_lock_mode=2   #主键自增模式修改为交叉模式  
wsrep_slave_threads=8  #开启并行复制线程，根据CPU核数设置  
innodb_flush_log_at_trx_commit=0   #事务提交每隔1秒刷盘  
innodb_buffer_pool_size=1G  
wsrep_sst_method=rsync  
[embedded]  
[mariadb]  
[mariadb-10.1]  
EOF

cd /usr/local/mysql-cluster/scripts/
./mysql_install_db --defaults-file=/etc/my.cnf --basedir=/usr/local/mysql-cluster --datadir=/usr/local/mysql-cluster/data

chown mysql:mysql /usr/local/mysql-cluster/data
chmod -R 777 /usr/local/mysql-cluster/data
/usr/local/mysql-cluster/bin/mysqld_safe --defaults-file=/etc/my.cnf --wsrep-new-cluster &
# 第一次启动第一个节点（以后启动不用加 --wsrep-new-cluster）


cat > /etc/my.cnf << EOF
[server]
[client]
port = 13306
[mysqld]
bind-address=0.0.0.0
port = 13306
server_id=128
datadir=/usr/local/mysql-cluster/data
user=mysql  
skip-external-locking  
skip-name-resolve  
character-set-server=utf8
[galera]  
wsrep_causal_reads=ON  #节点应用完事务才返回查询请求  
wsrep_provider_options="gcache.size=4G"#同步复制缓冲池  
wsrep_certify_nonPK=ON   #为没有显式申明主键的表生成一个用于certificationtest的主键，默认为ON  
query_cache_size=0           #关闭查询缓存  
wsrep_on=ON   #开启全同步复制模式  
wsrep_provider=/usr/lib64/galera-3/libgalera_smm.so
wsrep_cluster_name=master
wsrep_cluster_address="gcomm://192.168.244.176,192.168.244.129,192.168.244.128"  #本机ip千万不能放第一位
wsrep_node_name=node1 # 要和hosts一致
wsrep_node_address=192.168.244.128
binlog_format=row  
default_storage_engine=InnoDB  
innodb_autoinc_lock_mode=2   #主键自增模式修改为交叉模式  
wsrep_slave_threads=8  #开启并行复制线程，根据CPU核数设置  
innodb_flush_log_at_trx_commit=0   #事务提交每隔1秒刷盘  
innodb_buffer_pool_size=2G  
wsrep_sst_method=rsync  
[embedded]  
[mariadb]  
[mariadb-10.1]
EOF
cd /usr/local/mysql-cluster/scripts/
./mysql_install_db --defaults-file=/etc/my.cnf --basedir=/usr/local/mysql-cluster --datadir=/usr/local/mysql-cluster/data

chown mysql:mysql /usr/local/mysql-cluster/data
chmod -R 777 /usr/local/mysql-cluster/data
/usr/local/mysql-cluster/bin/mysqld_safe --defaults-file=/etc/my.cnf &
# 其他节点

/usr/local/mysql-cluster/bin/mysql -uroot -p

show status like 'wsrep%';

# 登录一台机器为集群创建帐号：
SHOW GLOBAL STATUS LIKE 'wsrep_cluster_size';
create database test_db;
create table innodb_tbl(id int,name text) ENGINE InnoDB;
insert into innodb_tbl values(1,'hive');
select * from innodb_tbl;

tar -xzvf haproxy-1.8.19.tar.gz -C /usr/local