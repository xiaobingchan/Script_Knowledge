1����ȡ�ڴ�
for /f "skip=2 tokens=2 delims=," %%i in ('wmic os get FreePhysicalMemory /format:CSV') do (
set richparm2=%%i&goto e1)
:e1
set /a richparm2=%richparm2%/1024
echo Memory Free Size:%richparm2% MB,Memory Total Size:%richparm3% MB

2��git for windows������sc start null

3��WMIC�������

WMIC����ָ��
������https://www.cnblogs.com/top5/p/3143837.html
��ϸ��https://www.cnblogs.com/archoncap/p/5400769.html

wmic logicaldisk F: get FreeSpace                                       rem �鿴���̿��ÿռ�
wmic logicaldisk F: get Size                                            rem �鿴�����ܿռ�
wmic cpu get LoadPercentage                                             rem �鿴CPUռ����
wmic cpu get NumberOfCores                                              rem �鿴CPU����
wmic os get FreePhysicalMemory                                          rem �鿴�����ڴ� 
wmic os get TotalVisibleMemorySize                                      rem �鿴���ڴ�
wmic process where name="chrome.exe" get KernelModeTime,UserModeTime    rem �鿴ĳһ������ռ����Դ���
wmic /output:process.html process list                                  rem ����html��ҳ


4��WMIC ��ȡ��Ϣ������ where ��֪������=��ֵ�� get ��Ҫ��ȡ�Ĳ�����1,������2
   wmic cpu where name="Intel(R) Core(TM) i5-3320M CPU @ 2.60GHz" get Caption
   wmic logicaldisk where name="C:" get FreeSpace
   
���̹���process
�������service
CPU��Ϣ��cpu
�����ڴ棺memlogical
Ӳ�̣�logicaldisk
�û�����USERACCOUNT


***** 1����bat�Զ�������Ӧ�ó����ڲ��Զ������������ʽ���루Python��MySQL��sqlplus��wlst��sqlcmd��psql��mongo��redis-cli�ȵȣ���
@echo off
echo txtName = "S0008.txt">in.txt
echo f = file(txtName, "a+")>>in.txt
echo f.write("hello world!")>>in.txt
echo f.close()>>in.txt
python2 <in.txt>nul 2>nul

******* 2���������ø�ֵ
@echo off
call :getTM tm
goto myend

:getTM
set %1="3"

:Next
echo %tm%
::���������ֵ
goto myend

:myend

********* 3�����������������
python4>nul 2>nul

******** 4��BAT��ȡ13λʱ�������
@echo off
set "$=%temp%\Spring"
>%$% Echo WScript.Echo((new Date()).getTime())
for /f %%a in ('cscript -nologo -e:jscript %$%') do set timestamp=%%a
del /f /q %$%
echo %timestamp%

******** 5���������
echo.

******** 6���ж������Ƿ�ִ�гɹ�
@echo off
echo 1+1>in.txt
python4<in.txt>nul 2>nul
if errorlevel 1 (
echo "failed"
) else echo "successful"
del in.txt

******** 7��wmic��ȡ���Կ����ڴ�����ڴ��С��bat�ű�
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

******** 8��wmic���ɼ����Ӳ��Ѳ�챨����ҳ����
wmic /output:a.html logicaldisk where(name='c:') get DeviceID,Size,FreeSpace,Description,FileSystem /format:htable

******** 9��bat�����ȡ����ip��ַ
@echo off
ver|findstr "5.1" >nul && (
    set "m=ipconfig^|findstr /i "ip address""
)|| (
    set "m=ipconfig^|findstr /i "ipv4""
)
for /f "tokens=14* delims=: " %%1 in ('%m%')do  echo %%2

******** 10��bat forѭ��һ������ʵ����ֵ���(һ��Ҫ���ϣ�setlocal enabledelayedexpansion)
@echo off
setlocal enabledelayedexpansion
set /a index=0
for /l %%L in (1 1 8) do (
	set /a index=index+1
	echo !index!
)

******** 11������ǰ�����û�ǿ�ƽ����ǳ���������û�
query user
logoff 4 /VM
net user %username% /active:no

******** 12�������ȡһ���ַ��������������Ŀ�ַ�
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

**********  13���ж��ַ����Ƿ���ָ���ַ�
@echo off
set str="!@#%1*&$"
echo %str%|findstr "[ABCD1234567890EFGHIJKLMabcdefghijklmnopqrstuvwxyzNOPQRSTUVWXYZ]"
if errorlevel 1 (
echo 'not have ABCD1234567890EFGHIJKLMabcdefghijklmnopqrstuvwxyzNOPQRSTUVWXYZ'
) else echo 'it has ABCD1234567890EFGHIJKLMabcdefghijklmnopqrstuvwxyzNOPQRSTUVWXYZ'

*********** 14���ж��ַ�������
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

************ 15���ж��ַ����Ƿ�Ϊ�գ�
if not "%new_password%"==""

************ 16���ж��ַ����Ƿ���ͬ��
if "!str!" geq "!str2!"
equ - ����
neq - ������
lss - С��
leq - С�ڻ����
gtr - ����
geq - ���ڻ����

************ 17��if��else if ��else�жϣ���ֵ�Ƚϣ�
@echo off
set cpu_percent=47
if %cpu_percent% gtr 50 ( echo greater than 50
) else if %cpu_percent% gtr 60 ( echo greater than 60
) else echo less than 50

************* 18����������
if %a% gtr 5 if %a% lss 10 (echo ok) else echo no

**********  19��Windows���������ӣ�mklink b.lnk mydd.bat
�ļ���: mklink /j C:\ioszdhyw\wsd.lnk E:\Weblogic\Middleware\Oracle_Home\wlserver\common\bin\ ; cd /data/ioszdhyw/ luyanjie

**********  20��Windows�鿴�ļ��Ƿ���ڣ�if not exist 1.txt echo nofile

**********  21, ִ�������ӣ�start "" "C:\ioszdhyw\bin\wlst.lnk"

**********  22���½��ļ��У�md �ļ�����

**********  23��Windows�鿴�ļ����Ƿ���ڣ�
@echo off
if not exist C:\Users\Administrator\Desktop\shit\ (
set _result_0ce9=0
) else ( set _result_0ce9=1 )

**********  24����ѹzip��
unzip f:\wenjian.zip

**********  25��Windows����鿴�ļ�MD5
certutil -hashfile yourfilename.ext MD5
certutil -hashfile yourfilename.ext SHA1
certutil -hashfile yourfilename.ext SHA256
