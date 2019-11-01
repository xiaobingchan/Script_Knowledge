tar xzvf mongodb-linux-x86_64-rhel70-3.6.3.tgz -C /usr/local/
cd /usr/local
mv mongodb-linux-x86_64-rhel70-3.6.3 mongodb
cd mongodb
mkdir data 
mkdir log
mkdir etc
chown -R 777 data log etc
touch /usr/local/mongodb/log/mongo.log
chown -R 777 /usr/local/mongodb/log/mongo.log
cd etc/
cat > /usr/local/mongodb/etc/mongodb.conf << EOF
dbpath=/usr/local/mongodb/data
logpath=/usr/local/mongodb/log/mongo.log
logappend=true
journal=true
quiet=true
fork=true
port=20000
auth=true
bind_ip = 0.0.0.0
EOF
/usr/local/mongodb/bin/mongod -f /usr/local/mongodb/etc/mongodb.conf
/usr/local/mongodb/bin/mongo --port=20000
use admin
db.createUser({user:"useradmin",pwd:"123456",roles:[{role:"userAdminAnyDatabase",db:"admin"}]})

0，配置后台启动：
logappend=true
journal=true
quiet=true

1，查询当前mongoDB版本：db.version();

2，所有特定行：db.col.find({},{url:1,title:1,_id:0}): # 0代表不显示,1代表显示，默认不显示

3，查询前5行：db.col.find({},{url:1,title:1,_id:0}).limit(5);

4，导出备份： mongodump -h 127.0.0.1:20000 -u zdhyw -d zdhyw -o /home/nfdw/

5，导入备份：/usr/local/mongodb/bin/mongorestore -h 127.0.0.1:27000 -d dxdb --dir /root/mongo20190905/

6，创建管理员用户：
use admin
db.createUser({user:"useradmin",pwd:"123456",roles:[{role:"userAdminAnyDatabase",db:"admin"}]})
db.auth("useradmin","123456")

查询角色权限：db.system.users.find()
授予角色权限：db.grantRolesToUser( "admin" , [ { role: "root", db: "admin" } ])
取校角色权限：db.revokeRolesFromUser( "admin" , [ { role: "root", db: "admin" } ]

7，必定要先用admin库管理员登陆，创建普通用户：
use admin
db.auth("useradmin","123456")
use my1
db.createUser({user:"useradmin2",pwd:"123456",roles:[{role:"read",db:"my1"}]})

8，带用户名密码登陆：
/usr/local/mongodb/bin/mongo --port=20000 -u "useradmin" -p "123456" --authenticationDatabase "admin"

################################
Read：允许用户读取指定数据库
readWrite：允许用户读写指定数据库
dbAdmin：允许用户在指定数据库中执行管理函数，如索引创建、删除，查看统计或访问system.profile
userAdmin：允许用户向system.users集合写入，可以在指定数据库里创建、删除和管理用户
clusterAdmin：只在admin数据库中可用，赋予用户所有分片和复制集相关函数的管理权限。
readAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读权限
readWriteAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读写权限
userAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的userAdmin权限
dbAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的dbAdmin权限。
root：只在admin数据库中可用。超级账号，超级权限
################################

8，插入文档：use my1
db.col.insert({title:'MongoDB',description: 'MongoDB Nosql',url:'http://www.runoob.com',likes: 100})

9，列出已创建用户：show users

use my1
db.col.insert({title: 'MongoDB', 
    description: 'MongoDB Nosql',
    url: 'http://www.runoob.com',
    likes: 100
})

db.col.find()

10，别名查询：
