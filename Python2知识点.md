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
5，python传入json的双引号，要用\放前面
6，python获取第一个传入参数：import sys  a = sys.argv[1]
7，往命令行传入json：python python_test.py {\"language\":\"Chinese\"}
8，解析json：import json  js = json.loads(a)  print js['langage']
9，构造json：import json  python2json = {}  listData = "test python obj 1 json"  python2json["listData"] = listData  python2json["strData"] = "test python obj 2 json"  python3json=[]  python3json.append(python2json)  python3json.append(python2json)  json_str = json.dumps(python3json)  json_str = json_str.replace(" ", "")  print json_str
10，JSON中，标准语法中，不支持单引号，属性或者属性值，都必须是双引号括起来的

