Bat�ű�ʵ����
rem Time:2019.1.15
rem Author:LuYanjie
rem Company:NanFangDianWang
rem File:check_win_host.bat
rem Version:1.0
rem Description:check linux system

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

Bat�ű��鿴�ڴ�ʹ�����

sc start null
rem ����git

wmic logicaldisk F: get FreeSpace 
rem �鿴���̿��ÿռ�

wmic logicaldisk F: get Size
rem �鿴�����ܿռ�

wmic cpu get LoadPercentage 
rem �鿴CPUռ����

wmic cpu get NumberOfCores
rem �鿴CPU����

wmic os get FreePhysicalMemory
rem �鿴�����ڴ� 

wmic os get TotalVisibleMemorySize
rem �鿴���ڴ�

wmic process where name="chrome.exe" get KernelModeTime,UserModeTime
rem �鿴ĳһ������ռ����Դ���


rem wmic����html��ҳ


WMIC����ָ��
������https://www.cnblogs.com/top5/p/3143837.html
��ϸ��https://www.cnblogs.com/archoncap/p/5400769.html

WMIC ��ȡ��Ϣ������ where ��֪������=��ֵ�� get ��Ҫ��ȡ�Ĳ�����1,������2
���̹���process
�������service
CPU��Ϣ��cpu
�����ڴ棺memlogical
Ӳ�̣�logicaldisk
�û�����USERACCOUNT
������ҳ�� wmic���� /format:hform >PCinfo.html

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


@echo off
wmic logicaldisk where "drivetype=3" get name^,size^,freespace>t.txt
for /f %%a in (' find /c /v "" ^<"t.txt" ') do set line_number=%%a
set /a line_number-=2

@echo off&setlocal enabledelayedexpansion
@echo off
setlocal enabledelayedexpansion
set /a myk=0
for /f "skip=1 tokens=1,2,3 delims= " %%i in ('wmic logicaldisk where "drivetype=3" get name^,size^,freespace') do (
		if !myk! leq !line_number! (
		echo !myk!
		for /f %%a in ("%%i") do (
        set /a n+=1   >nul 2<&1
        set  AAA=%%i  >nul 2<&1
        set  BBB=%%k  >nul 2<&1
        rem ѭ��ȡֵ
		set dd=%%i
        call:division !AAA! !BBB! quot 2 2 >nul
        set /a myk+=1
        echo Name %%j  Free %%i  free_percent !quot!
	 )
    )
	)

:division
@echo off
setlocal
set str1=%1
set str2=%2
if "%~4" neq "" set u=%4
for %%i in (str1 str2) do if "!%%i:~,1!" == "-" set /a d+=1
if "%d%" == "1" (set d=-) else set "d="
set l=00000000&for /l %%i in (1 1 7) do set "l=!l!!l!"
set "var=4096 2048 1024 512 256 128 64 32 16 8 4 2 1"
for /l %%i in (1 1 2) do (
    set "str%%i=!str%%i:-=!"
    set /a "n=str%%i_2=0"
    for %%a in (!str%%i:.^= !) do (
        set /a n+=1
        set s=s%%a&set str%%i_!n!=0
        for %%b in (%var%) do if "!S:~%%b!" neq "" set/a str%%i_!n!+=%%b&set "S=!S:~%%b!"
        set /a len%%i+=str%%i_!n!
    )
        set str%%i=!str%%i:.=!
)
if !str1_2! gtr !str2_2! (set /a len2+=str1_2-str2_2) else set /a len1+=str2_2-str1_2
for /l %%i in (1 1 2) do (
    set str%%i=!str%%i!!l!
    for %%j in (!len%%i!) do set " str%%i=!str%%i:~,%%j!"
)
for /f "tokens=* delims=0" %%i in ("!str2!") do set s=%%i&set "str2=0%%i"
set len2=1
for %%j in (%var%) do if "!S:~%%j!" neq "" set/a len2+=%%j&set "S=!S:~%%j!"
set /a len=len2+1
if !len1! lss !len2! set len1=!len2!&set "str1=!l:~-%len2%,-%len1%!!str1!"
set /a len1+=u&set str1=0!str1!!l:~,%u%!
set str=!str1:~,%len2%!
set "i=0000000!str2!"&set /a Len_i=Len2+7
for /l %%i in (1 1 9) do (
    set "T=0"
    for /l %%j in (8 8 !Len_i!) do (
        set /a "T=1!i:~-%%j,8!*%%i+T"
        set Num%%i=!T:~-8!!Num%%i!&set /a "T=!T:~,-8!-%%i"
    )
    set Num%%i=!T!!Num%%i!
    set "Num%%i=0000000!Num%%i:~-%Len%!"
)
for /L %%a in (!len2! 1 !Len1!) do (
    set "str=!L!!str!!str1:~%%a,1!"
    set "str=!str:~-%Len%!"
    if "!str!" geq "!str2!" (
       set M=1&set i=0000000!str!
       for /l %%i in (2 1 9) do if "!i!" geq "!Num%%i!" set "M=%%i"
           set sun=!sun!!M!&set str=&set T=0
           for %%i in (!M!) do (
               for /l %%j in (8 8 !Len_i!) do (
                   set /a "T=3!i:~-%%j,8!-1!Num%%i:~-%%j,8!-!T:~,1!%%2"
                   set "str=!T:~1!!str!"
               )
           )
    ) else set sun=!sun!0
)
if defined u if "%u%" gtr "0" set sun=!sun:~,-%u%!.!sun:~-%u%!
endlocal&set %3=%d%%sun%