1，查询当前mongoDB版本：db.version();
2，所有特定行：db.col.find({},{url:1,title:1,_id:0}): # 0代表不显示,1代表显示，默认不显示
3，查询前5行：db.col.find({},{url:1,title:1,_id:0}).limit(5);
4，导出备份： mongodump -h 127.0.0.1:20000 -u zdhyw -d zdhyw -o /home/nfdw/
5，导入备份：/usr/local/mongodb/bin/mongorestore -h 127.0.0.1:27000 -d dxdb --dir /root/mongo20190905/


2，安装带有密码的mongoDB：
tar xzvf mongodb-linux-x86_64-rhel70-3.6.3.tgz -C /usr/local/
cd /usr/local
mv mongodb-linux-x86_64-rhel70-3.6.3 mongodb_363
cd mongodb_363
mkdir data 
mkdir log
mkdir etc
chown -R 777 data log etc
touch /usr/local/mongodb_363/log/mongo.log
chown -R 777 /usr/local/mongodb_363/log/mongo.log
cd etc/

cat > mongodb.conf << EOF
dbpath=/usr/local/mongodb_363/data
logpath=/usr/local/mongodb_363/log/mongo.log
logappend=true
journal=true
quiet=true
fork=true
port=20000
auth=true
bind_ip = 0.0.0.0
EOF

/usr/local/mongodb_363/bin/mongod -f /usr/local/mongodb_363/etc/mongodb.conf

firewall-cmd --zone=public --add-port=20000/tcp --permanent
firewall-cmd --reload

/usr/local/mongodb_363/bin/mongo  –-port=20000

use admin
db.createUser({user:"useradmin",pwd:"123456",roles:[{role:"userAdminAnyDatabase",db:"admin"}]})
db.auth("useradmin","123456")

use my1
db.createUser({user:"admin",pwd:"123456",roles:[{role:"dbOwner",db:"my1"}]})
db.auth("admin","123456")

use my1
db.col.insert({title: 'MongoDB', 
    description: 'MongoDB Nosql',
    url: 'http://www.runoob.com',
    likes: 100
})

db.col.find()
