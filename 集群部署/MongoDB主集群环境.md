主节点：192.168.244.176 master
节点1：192.168.244.128 node1
节点2：192.168.244.129 node2

主机地址：192.168.244.176  192.168.244.128  192.168.244.129
176         128        129  
master    node1     node2  
mongos    mongos    config  
shard0主  shard1主  shard2主
shard1副  shard2副  shard0副
shard2仲  shard0仲  shard1仲

端口分配：
mongos:20000
config:21000
shard0:27000
shard1:27001
shard2:27002

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

tar -xzvf mongodb-linux-x86_64-rhel70-4.0.0.tgz -C /usr/local/
mv /usr/local/mongodb-linux-x86_64-rhel70-4.0.0 /usr/local/mongodb

创建目录
# 所有主机
mkdir -p /usr/local/mongodb/conf
mkdir -p /usr/local/mongodb/log
mkdir -p /data/mongodata/shard0
mkdir -p /data/mongodata/shard1
mkdir -p /data/mongodata/shard2

cat >> /etc/profile << EOF
export MONGODB_HOME=/usr/local/mongodb
export PATH=$MONGODB_HOME/bin:$PATH
EOF
source /etc/profile

# 配置config 192.168.244.129
mkdir -p /data/mongodata/config
cat >> /usr/local/mongodb/conf/config.conf << EOF
# where to write logging data.
systemLog:
  destination: file
  logAppend: true
  path: /usr/local/mongodb/log/config.log
# Where and how to store data.
storage:
  dbPath: /data/mongodata/config
  journal:
    enabled: true
# how the process runs
processManagement:
  fork: true
  pidFilePath: /usr/local/mongodb/configsrv.pid
# network interfaces
net:
  port: 21000
  bindIp: 0.0.0.0
#operationProfiling:
replication:
    replSetName: configs       
sharding:
    clusterRole: configsvr
EOF
/usr/local/mongodb/bin/mongod -f /usr/local/mongodb/conf/config.conf
/usr/local/mongodb/bin/mongo --port 21000
config = {
   _id : "configs",
    members : [
        {_id : 0, host : "node2:21000" }
    ]
}
rs.initiate(config)
rs.status();

# 配置shard0  192.168.244.176  192.168.244.128  192.168.244.129
cat > /usr/local/mongodb/conf/shard0.conf << EOF
# where to write logging data.
systemLog:
  destination: file
  logAppend: true
  path: /usr/local/mongodb/log/shard0.logging
# Where and how to store data.
storage:
  dbPath: /data/mongodata/shard0
  journal:
    enabled: true
# how the process runs
processManagement:
  fork: true
  pidFilePath: /usr/local/mongodb/shard0.pid
# network interfaces
net:
  port: 27000
  bindIp: 0.0.0.0
#operationProfiling:
replication:
    replSetName: shard0       
sharding:
    clusterRole: shardsvr
EOF
/usr/local/mongodb/bin/mongod -f /usr/local/mongodb/conf/shard0.conf
/usr/local/mongodb/bin/mongo --port 27000
config = {
   _id : "shard0",
    members : [
        {_id : 0, host : "master:27000", priority : 2 },
        {_id : 1, host : "node1:27000", priority : 1 },
        {_id : 2, host : "node2:27000", arbiterOnly :true }
    ]
}
rs.initiate(config)

# 配置shard1  192.168.244.176  192.168.244.128  192.168.244.129
cat > /usr/local/mongodb/conf/shard1.conf << EOF
# where to write logging data.
systemLog:
  destination: file
  logAppend: true
  path: /usr/local/mongodb/log/shard1.logging
 
# Where and how to store data.
storage:
  dbPath: /data/mongodata/shard1
  journal:
    enabled: true

# how the process runs
processManagement:
  fork: true
  pidFilePath: /usr/local/mongodb/shard1.pid
 
# network interfaces
net:
  port: 27001
  bindIp: 0.0.0.0
 
#operationProfiling:
replication:
    replSetName: shard1       

sharding:
    clusterRole: shardsvr

EOF
/usr/local/mongodb/bin/mongod -f /usr/local/mongodb/conf/shard1.conf
/usr/local/mongodb/bin/mongo --port 27001
config = {
   _id : "shard1",
    members : [
        {_id : 0, host : "master:27001", priority : 2 },
        {_id : 1, host : "node1:27001", priority : 1 },
        {_id : 2, host : "node2:27001", arbiterOnly :true }
    ]
}
rs.initiate(config)

# 配置shard2  192.168.244.176  192.168.244.128  192.168.244.129
cat > /usr/local/mongodb/conf/shard2.conf << EOF
# where to write logging data.
systemLog:
  destination: file
  logAppend: true
  path: /usr/local/mongodb/log/shard2.logging
 
# Where and how to store data.
storage:
  dbPath: /data/mongodata/shard2
  journal:
    enabled: true

# how the process runs
processManagement:
  fork: true
  pidFilePath: /usr/local/mongodb/shard2.pid
 
# network interfaces
net:
  port: 27002
  bindIp: 0.0.0.0
 
#operationProfiling:
replication:
    replSetName: shard2

sharding:
    clusterRole: shardsvr

EOF
/usr/local/mongodb/bin/mongod -f /usr/local/mongodb/conf/shard2.conf
/usr/local/mongodb/bin/mongo --port 27002
config = {
   _id : "shard2",
    members : [
        {_id : 0, host : "master:27002", priority : 2 },
        {_id : 1, host : "node1:27002", priority : 1 },
        {_id : 2, host : "node2:27002", arbiterOnly :true }
    ]
}
rs.initiate(config)

# 配置mongos server 192.168.244.176  192.168.244.128
cat > /usr/local/mongodb/conf/mongos.conf << EOF
systemLog:
  destination: file
  logAppend: true
  path: /usr/local/mongodb/log/mongos.log
processManagement:
  fork: true
#  pidFilePath: /usr/local/mongodb/log/mongos.pid
 
# network interfaces
net:
  port: 20000
  bindIp: 0.0.0.0
#监听的配置服务器,只能有1个或者3个 configs为配置服务器的副本集名字
sharding:
   configDB: configs/node2:21000
EOF
/usr/local/mongodb/bin/mongos -f /usr/local/mongodb/conf/mongos.conf
/usr/local/mongodb/bin/mongo --port 20000

use zdhyw
sh.addShard("shard0/master:27000,node1:27000,node2:27000")
sh.addShard("shard1/master:27001,node1:27001,node2:27001")
sh.addShard("shard2/master:27002,node1:27002,node2:27002")




