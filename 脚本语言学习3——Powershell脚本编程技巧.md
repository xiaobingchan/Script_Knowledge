1��������ֵ��$isCheck = 0
2�����һ������ֵ��[Console]::WriteLine($output)
3����ȡWindowsϵͳ��ȫ�����ã�secedit /export /cfg config.cfg /quiet
4��ִ��CMD���cmd /c (secedit /export /cfg config.cfg /quiet)
   ������ţ�`$  `" `'
   ���ļ�ֱ��д��һ�У�"connect" | Out-File   -Append   -Encoding "ascii"  in.py
   ���ļ�׷�Ӳ������֣�"connect" | Out-File   -Append   -Encoding "ascii"  in.py
   ����ʽ�Զ���ָ�wlst in.py | ConvertFrom-Csv | Out-File a.txt
5���ж������Ƿ�ִ�гɹ���
if (!$?)
{
$output=""
[Console]::WriteLine($output)
}
elseif($n -eq 0){ }
else
{ }

6�����б���txt�ļ�
$config = Get-Content -path config.txt
for ($i=0; $i -lt $config.Length; $i++)
{
	[Console]::WriteLine($config[$i])
}
7���ָ��ַ�����=�ָ�������
$config_line = $config -split "="
8���Ƴ������еĿո�
$config_line = $config_line.Trim(' ')
$have_openssl = $have_openssl.Split("have_openssl")[-1]
9�������
-eq �����ڣ�-ne �������ڣ�-gt �����ڣ�-ge �����ڵ���
-lt ��С�ڣ�-le ��С�ڵ��ڣ�-contains ��������-notcontains :������
10����������
-and ���ͣ�-or ����-xor �����-not ����
11������������������ļ�����
����1 | ConvertFrom-Csv | Out-File a.txt
12������ļ�ָ�����������ݣ�
$have_openssl = (Get-Content b.txt -TotalCount 87)[-1].Split("have_openssl")[-1]
13��ɾ���ļ���del a.txt

�ж�.Net Framework 3.5�Ƿ񱻰�װ��Get-ChildItem 'HKLM:\SOFTWARE\Microsoft\NET Framework Setup\NDP\v3.5' |  Get-ItemPropertyValu
e -Name Version | Foreach-Object { echo $_ }

ѹ�����.\7z a $filename $upload_path # ��Ҫ7z.exe��7z.dll

�жϳ�������������
$s=Get-Date
$e=Get-Date
$scrpit_Seconds=($e - $s).TotalSeconds

�����ǰʱ�䣺Get-Date -Format 'yyyyMdHms'

14����װ�����
function WriteLog([string]  $content)
{
    #Write-Host $content
    $script:OutMessage += $content + "`r`n"
}
WriteLog('log_bin')
$OutMessage
15��Powershell����SQL Server���ӣ�
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

16��Powershell����IIS ���ӣ�
Import-Module WebAdministration
Get-ChildItem IIS:\apppools | ForEach-Object{
    $appPoolName =  $_.Name
    $appPool = $_
    #����������
    $RecyclingTime = $appPool.recycling.periodicRestart.time.TotalMinutes
    #����˺�����
    $identityType = $appPool.processModel.identityType
    $userName = $appPool.processModel.userName
    #���ɻ����¼���־����
    $LogEventOnRecycle = $appPool.recycling.logEventOnRecycle
    #��Idle Timeout��Ϊ0
    $IdleTimeout = $appPool.processModel.idleTimeout
    #���������������Ϊ0,֧��NUMA
    $maxProcesses = $appPool.processModel.maxProcesses
}
