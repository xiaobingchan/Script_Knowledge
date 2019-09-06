
# 1，非交互式命令创建dog用户：mysql -h127.0.0.1 -P3306 -uroot -p123456 -e "SHOW VARIABLES LIKE 'validate_password%';" 2>/dev/null
# 2，无警告非交互式命令执行：mysql -u root -p123456 -h 127.0.0.1 -P 3306 -Bse "show plugins;" 2>/dev/null
# 3，创建可外部链接用户dog：CREATE USER 'dog'@'%' IDENTIFIED BY '12345678990';
# 4，删除用户dog：DROP USER 'dog'@'%';
# 5，设置dog用户密码：SET PASSWORD FOR 'dog'@'%' = PASSWORD('1234564e6456456456');
# 6，列出已安装插件：SHOW VARIABLES;
# 7，安装密码插件：INSTALL PLUGIN validate_password SONAME 'validate_password.so';


# set global validate_password_policy=LOW;
# SHOW VARIABLES LIKE 'validate_password%';
# 