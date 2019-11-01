useradd git
passwd git

su - git
mkdir -p repos/app.git/
cd repos/app.git/
git --bare init

git config --global user.email "you@example.com"
git config --global user.name "Your Name"
git clone git@192.168.244.129:/home/git/repos/app.git
git add .
git status 
git commit -m '1'
git push origin master

ssh-keygen

0，git直接下载：https://minhaskamal.github.io/DownGit/#/home

1，ssh免密登陆，https://blog.csdn.net/zrc199021/article/details/51693223
ssh-keygen
cat /root/.ssh/id_rsa.pub >> /root/.ssh/authorized_keys
chmod 600 /root/.ssh/authorized_keys
###### 编辑 /etc/ssh/sshd_config 下面注释 ###########
AuthorizedKeysFile      .ssh/authorized_keys
PubkeyAuthentication yes
RSAAuthentication yes
###### ####################################################
service sshd restart

2，scp /root/.ssh/id_rsa.pub root@192.168.244.148:/root/.ssh/id_rsa.pub2

cat /root/.ssh/id_rsa.pub2 > /root/.ssh/authorized_key


