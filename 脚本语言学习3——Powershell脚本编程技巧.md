1，变量赋值：$isCheck = 0
2，输出一个变量值：[Console]::WriteLine($output)
3，获取Windows系统的全局配置：secedit /export /cfg config.cfg /quiet
4，执行CMD命令：cmd /c (secedit /export /cfg config.cfg /quiet)
   特殊符号：`$  `" `'
   往文件直接写入一行："connect" | Out-File   -Append   -Encoding "ascii"  in.py
   往文件追加插入文字："connect" | Out-File   -Append   -Encoding "ascii"  in.py
   交互式自动传指令：wlst in.py | ConvertFrom-Csv | Out-File a.txt
5，判断命令是否执行成功：
if (!$?)
{
$output=""
[Console]::WriteLine($output)
}
elseif($n -eq 0){ }
else
{ }

6，逐行遍历txt文件
$config = Get-Content -path config.txt
for ($i=0; $i -lt $config.Length; $i++)
{
	[Console]::WriteLine($config[$i])
}
7，分割字符串（=分隔符）：
$config_line = $config -split "="
8，移除变量中的空格
$config_line = $config_line.Trim(' ')
$have_openssl = $have_openssl.Split("have_openssl")[-1]
9，运算符
-eq ：等于；-ne ：不等于；-gt ：大于；-ge ：大于等于
-lt ：小于；-le ：小于等于；-contains ：包含；-notcontains :不包含
10，布尔运算
-and ：和；-or ：或；-xor ：异或；-not ：逆
11，将命令输出放入至文件处理：
命令1 | ConvertFrom-Csv | Out-File a.txt
12，输出文件指定行数的内容：
$have_openssl = (Get-Content b.txt -TotalCount 87)[-1].Split("have_openssl")[-1]
13，删除文件：del a.txt

判断.Net Framework 3.5是否被安装：Get-ChildItem 'HKLM:\SOFTWARE\Microsoft\NET Framework Setup\NDP\v3.5' |  Get-ItemPropertyValu
e -Name Version | Foreach-Object { echo $_ }

压缩命令：.\7z a $filename $upload_path # 需要7z.exe和7z.dll

判断程序运行秒数：
$s=Get-Date
$e=Get-Date
$scrpit_Seconds=($e - $s).TotalSeconds

输出当前时间：Get-Date -Format 'yyyyMdHms'

14，封装输出：
function WriteLog([string]  $content)
{
    #Write-Host $content
    $script:OutMessage += $content + "`r`n"
}
WriteLog('log_bin')
$OutMessage
15，Powershell驱动SQL Server例子：
function sql($sqlText)
{
    $connection = new-object System.Data.SqlClient.SQLConnection("Data Source=(local)\SQLEXPRESSLYJ;Initial Catalog=master;User ID=sa;pwd=a12345678");
    $cmd = new-object System.Data.SqlClient.SqlCommand($sqlText, $connection);
    $connection.Open();
    $reader = $cmd.ExecuteReader()
    $results = @()
    while ( $reader.Read())
    {
        $row = @{ }
        for ($i = 0; $i -lt $reader.FieldCount; $i++)
        {
            $row[$reader.GetName($i)] = $reader.GetValue($i)
        }
        $results += new-object psobject -property $row
    }
    $connection.Close();
    $results
}

$sqlText = "select @@servername as Server_Name"
$Server_Name = sql($sqlText)
$Server_Name = $Server_Name.Server_Name

16，Powershell驱动IIS 例子：
Import-Module WebAdministration
Get-ChildItem IIS:\apppools | ForEach-Object{
    $appPoolName =  $_.Name
    $appPool = $_
    #检查回收设置
    $RecyclingTime = $appPool.recycling.periodicRestart.time.TotalMinutes
    #检查账号设置
    $identityType = $appPool.processModel.identityType
    $userName = $appPool.processModel.userName
    #生成回收事件日志设置
    $LogEventOnRecycle = $appPool.recycling.logEventOnRecycle
    #把Idle Timeout设为0
    $IdleTimeout = $appPool.processModel.idleTimeout
    #最大工作进程数设置为0,支持NUMA
    $maxProcesses = $appPool.processModel.maxProcesses
}
