Redis安装：
############################################################
wget http://download.redis.io/releases/redis-5.0.5.tar.gz
tar -xzvf redis-5.0.5.tar.gz -C /usr/local/
yum install -y gcc
cd /usr/local/redis-5.0.5/deps
make hiredis jemalloc linenoise lua
cd /usr/local/redis-5.0.5
make MALLOC=libc
/usr/local/redis/src/redis-server /usr/local/redis/redis.conf
############################################################

1，redis查看连接信息:
redis-cli -h 127.0.0.1 -a 密码 info
2，redis后台运行配置：
