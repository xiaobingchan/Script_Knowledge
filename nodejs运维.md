Nodejs部署

# Node 官网已经把 linux 下载版本更改为已编译好的版本了，我们可以直接下载解压后使用：
wget https://nodejs.org/dist/v12.11.1/node-v12.11.1-linux-x64.tar.gz    # 下载
tar xf  node-v10.9.0-linux-x64.tar.xz       # 解压
cd node-v10.9.0-linux-x64/                  # 进入解压目录
./bin/node -v                               # 执行node命令 查看版本
# 创建软链接做环境变量
ln -s /usr/software/nodejs/bin/npm   /usr/local/bin/ 
ln -s /usr/software/nodejs/bin/node   /usr/local/bin/

0，编译比较新的node项目，最好将nodejs版本更新至最新，npm编译失败很大可能是因为nodejs版本过低导致的
1，离线是无法 npm install的，可以把npm install的程序包或 nodejs原生组件直接拷贝到对应系统
2，serve -s ./build -p 80 是无法保持后台运行的，需要 cd ./build && npm start 方可保持后台运行
3，