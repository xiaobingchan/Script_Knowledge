yum -y install epel-release
yum install –y clamav clamav-update
freshclam
clamscan –ri /root -l clamscan.log


yum install –y clamav clamav-update
yum install --downloadonly --downloaddir=/root/clamav clamav
yum install --downloadonly --downloaddir=/root/clamav-update clamav-update