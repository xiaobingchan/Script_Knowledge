1，获取内存
for /f "skip=2 tokens=2 delims=," %%i in ('wmic os get FreePhysicalMemory /format:CSV') do (
set richparm2=%%i&goto e1)
:e1
set /a richparm2=%richparm2%/1024
echo Memory Free Size:%richparm2% MB,Memory Total Size:%richparm3% MB

2，git for windows启动：sc start null

3，WMIC命令管理

WMIC开发指南
概述：https://www.cnblogs.com/top5/p/3143837.html
详细：https://www.cnblogs.com/archoncap/p/5400769.html

wmic logicaldisk F: get FreeSpace                                       rem 查看磁盘可用空间
wmic logicaldisk F: get Size                                            rem 查看磁盘总空间
wmic cpu get LoadPercentage                                             rem 查看CPU占用率
wmic cpu get NumberOfCores                                              rem 查看CPU核数
wmic os get FreePhysicalMemory                                          rem 查看可用内存 
wmic os get TotalVisibleMemorySize                                      rem 查看总内存
wmic process where name="chrome.exe" get KernelModeTime,UserModeTime    rem 查看某一个进程占用资源情况
wmic /output:process.html process list                                  rem 生成html网页


4，WMIC 获取信息类型名 where 已知参数名=‘值’ get 想要获取的参数名1,参数名2
   wmic cpu where name="Intel(R) Core(TM) i5-3320M CPU @ 2.60GHz" get Caption
   wmic logicaldisk where name="C:" get FreeSpace
   
进程管理：process
服务管理：service
CPU信息：cpu
物理内存：memlogical
硬盘：logicaldisk
用户管理：USERACCOUNT


***** 1，让bat自动往其他应用程序内部自动输入命令，交互式输入（Python、MySQL、sqlplus、wlst、sqlcmd、psql、mongo、redis-cli等等）：
@echo off
echo txtName = "S0008.txt">in.txt
echo f = file(txtName, "a+")>>in.txt
echo f.write("hello world!")>>in.txt
echo f.close()>>in.txt
python2 <in.txt>nul 2>nul

******* 2，函数调用赋值
@echo off
call :getTM tm
goto myend

:getTM
set %1="3"

:Next
echo %tm%
::输出方法赋值
goto myend

:myend

********* 3，忽略命令输出错误
python4>nul 2>nul

******** 4，BAT获取13位时间戳代码
@echo off
set "$=%temp%\Spring"
>%$% Echo WScript.Echo((new Date()).getTime())
for /f %%a in ('cscript -nologo -e:jscript %$%') do set timestamp=%%a
del /f /q %$%
echo %timestamp%

******** 5，输出换行
echo.

******** 6，判断命令是否执行成功
@echo off
echo 1+1>in.txt
python4<in.txt>nul 2>nul
if errorlevel 1 (
echo "failed"
) else echo "successful"
del in.txt

******** 7，wmic获取电脑可用内存和总内存大小的bat脚本
@echo off
for /f "skip=2 tokens=2 delims=," %%i in ('wmic os get FreePhysicalMemory /format:CSV') do (
set richparm2=%%i&goto e1)
:e1
for /f "skip=2 tokens=2 delims=," %%i in ('wmic os get TotalVisibleMemorySize /format:CSV') do (
set richparm3=%%i&goto e2)
:e2
set /a richparm2=%richparm2%/1024
set /a richparm3=%richparm3%/1024
echo Memory Free Size:%richparm2% MB,Memory Total Size:%richparm3% MB

******** 8，wmic生成计算机硬盘巡检报表网页命令
wmic /output:a.html logicaldisk where(name='c:') get DeviceID,Size,FreeSpace,Description,FileSystem /format:htable

******** 9，bat命令获取本机ip地址
@echo off
ver|findstr "5.1" >nul && (
    set "m=ipconfig^|findstr /i "ip address""
)|| (
    set "m=ipconfig^|findstr /i "ipv4""
)
for /f "tokens=14* delims=: " %%1 in ('%m%')do  echo %%2

******** 10，bat for循环一定次数实现数值相加(一定要加上，setlocal enabledelayedexpansion)
@echo off
setlocal enabledelayedexpansion
set /a index=0
for /l %%L in (1 1 8) do (
	set /a index=index+1
	echo !index!
)

******** 11，将当前在线用户强制结束登出命令并禁用用户
query user
logoff 4 /VM
net user %username% /active:no

******** 12，随机抽取一个字符串里面的任意数目字符
@echo off
setlocal enabledelayedexpansion
set Str=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789
set /a n = %random% %% 63
set Out=%password_get:%%n,1%
for /l %%L in (1 1 8) do (
    set /a n = !random! %% 63
    for %%n in (!n!) do set Out=!Out!!Str:~%%n,1!
)
echo !Out!

**********  13，判断字符串是否含有指定字符
@echo off
set str="!@#%1*&$"
echo %str%|findstr "[ABCD1234567890EFGHIJKLMabcdefghijklmnopqrstuvwxyzNOPQRSTUVWXYZ]"
if errorlevel 1 (
echo 'not have ABCD1234567890EFGHIJKLMabcdefghijklmnopqrstuvwxyzNOPQRSTUVWXYZ'
) else echo 'it has ABCD1234567890EFGHIJKLMabcdefghijklmnopqrstuvwxyzNOPQRSTUVWXYZ'

*********** 14，判断字符串长度
@echo off
    set mystr=ABCedttd$$
    set str=%mystr%
	set /a num=0
    :strLength
    if not "%str%"=="" (
        set /a num+=1
        set "str=%str:~1%"
        goto strLength
    )
echo %mystr% length: %num%

************ 15，判断字符串是否为空：
if not "%new_password%"==""

************ 16，判断字符串是否相同：
if "!str!" geq "!str2!"
equ - 等于
neq - 不等于
lss - 小于
leq - 小于或等于
gtr - 大于
geq - 大于或等于

************ 17，if、else if 、else判断，数值比较：
@echo off
set cpu_percent=47
if %cpu_percent% gtr 50 ( echo greater than 50
) else if %cpu_percent% gtr 60 ( echo greater than 60
) else echo less than 50

************* 18，条件并列
if %a% gtr 5 if %a% lss 10 (echo ok) else echo no

**********  19，Windows创建软链接：mklink b.lnk mydd.bat
文件夹: mklink /j C:\ioszdhyw\wsd.lnk E:\Weblogic\Middleware\Oracle_Home\wlserver\common\bin\ ; cd /data/ioszdhyw/ luyanjie

**********  20，Windows查看文件是否存在：if not exist 1.txt echo nofile

**********  21, 执行软链接：start "" "C:\ioszdhyw\bin\wlst.lnk"

**********  22，新建文件夹：md 文件夹名

**********  23，Windows查看文件夹是否存在：
@echo off
if not exist C:\Users\Administrator\Desktop\shit\ (
set _result_0ce9=0
) else ( set _result_0ce9=1 )

**********  24，解压zip包
unzip f:\wenjian.zip

**********  25，Windows命令查看文件MD5
certutil -hashfile yourfilename.ext MD5
certutil -hashfile yourfilename.ext SHA1
certutil -hashfile yourfilename.ext SHA256
