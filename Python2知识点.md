CentOS/Redhat 部署：
################################
yum install -y zlib-devel bzip2-devel openssl-devel ncurses-devel sqlite-devel python-devel.x86_64
tar zxf /data/soft/Python-2.7.16.tgz
cd /data/soft/`echo /data/soft/Python-2.7.16.tgz |awk 'BEGIN{FS="/"}''{print $NF}'| awk -F".tgz" '{print $NR}'`
./configure  --prefix=/usr/local/python2_7_16
make && make install

wget --no-check-certificat  https://pypi.python.org/packages/source/s/setuptools/setuptools-2.0.tar.gz
tar zxf setuptools-2.0.tar.gz
cd setuptools-2.0
python setup.py install
cd  ..
wget https://files.pythonhosted.org/packages/00/9e/4c83a0950d8bdec0b4ca72afd2f9cea92d08eb7c1a768363f2ea458d08b4/pip-19.2.3.tar.gz --no-check-certificate
tar -xzvf pip-19.2.3.tar.gz
cd pip-19.2.3
python setup.py install
python -m pip install --upgrade pip
################################


1，所有python2文件开头加上 ：# -*- coding: utf-8 -*
2，手动输入数字：a=input('请输入：')
3，获取整数商：b=34//3
4，命名不要用python的内置函数，如：sum
5，