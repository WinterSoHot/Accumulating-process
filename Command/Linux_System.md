# Linux

> Linux 系统下的常见命令

## 系统命令

- fdisk -l  查看当前分区挂载信息
- mkfs  mke2fs 格式化一个未格式化的分区
- mount /dev/**  data/   挂载这个设备到 data目录上
- umount /dev/** 卸载设备
- ls -s -S -a -l -t -F filename  查看文件和文件夹信息
- cd
- pwd 显示当前所在目录
- mkdir 创建一个文件夹
- rmdir 删除一个文件
- touch 创建一个文件
- cat -a -n -b -s 显示文件内容
- cat file.txt | ls 分页显示
- cat > 文件名 在命令行里输入文件内容 Cirl+D 退出
- cat file1 file2 > file3 把file1和file2 输入到file3
- cat file2 >> file1 给文件file1 追加内容
- cp  f1 f2 文件复制 -i 安全访问的复制
- rm 文件/目录  删除文件 -i -r 递归处理 -f 强制删除 -v 显示执行过程 -d 直接吧目录的硬链接数据删成0
- mv 文件移动
- chmod +/-/= 权限类型 文件1  文件2 。。。

        1. + 增加权限 -去除权限 =给予指定权限 删除其他权限
        2. r读w写x可执行
        3. u/g/o/a 所有者/所在组/其他用户/所有用户  a+rw 所有用户读写权限  ug+r 所有者和组增加读权限

- grep 查找指定文件字符串 -i 不区分大小写
- head -2 file 显示文件前两行内容
- tail -1 file 显示文件最后一行内容
- wc -l 显示行数 -w显示单词书 -m显示字符数 统计文件的行数单词数和字符数
- sort 对文件内容进行排序
- sort -t":" -k2 file 指定每列的分隔符为：，根据文件的第二列排序输出
- find file/dir  查看当前目录下的文件和目录
- which 命令 查看命令所在的目录
- whereis 文件名 查找可执行的命令和包含该文件名的字符串
- locate 文件名 查找文件
- bzip2 file1 file2 ..压缩命令
- bunzip2 文件名 解压命令
- gzip 文件压缩解压命令 -d 解压
- unzip 解压.zip文件 -d 解压到指定目录 -n 不覆盖原来存在的文件 -v查看文件目录不解压 -o以默认方式覆盖已经存在的文件
- zcat 和 bzcat 查看压缩文件内容 不解压
- zcat 文件名  查看.gz 文件 
- bzcat 文件名 查看.bz2 文件
- tar 命令 对文件和目录进行打包
- tar [-选项] [备份的文件名] [要打包的目录或文件]

||含义|
|---|---|
|-c| 创建新的打包文件|
|-x| 抽取.tar 文件的内容|
|-z| 打包后用gzip压缩或者解压|
|-j|就打包后用bzip2进行压缩或者解压操作|
|-t| 查看一个打包文件的目录|
|-f| 使用文件或设备|
|-v| 在压缩或解压后显示详细的文件清单|

##　磁盘内容

### mount

    mount -t 文件类型 -o 挂接方式 device path 

> -t iso9660/ntfs/msdos/smbfs/vfat/nfs -o loop(将文件当作硬盘分区挂接到系统)/iocharset(指定访问的文件系统的字符集)/ro 采用只读的方式挂接/device 要挂接的设备 /rw 采用读写挂接 /dir 设备在系统的挂接点

光盘文件的挂接  

    mount -o loop -t iso9660 /ss.iso /mnt/ss

- fdisk -l 查看系统的硬盘和硬盘分区情况
- umount 挂载点或设备 卸载一个设备
- df 查看当前硬盘的分区信息
- df -a 显示所有文件分区和硬盘
- df -i 列出i-nodes的使用量
- df -k 大小以KB为单位
- df -h MB
- df -t 列出某个文件系统的分区磁盘使用情况
- df -x 列出不是某个文件系统分区磁盘的使用情况
- df -t 列出每个分区所属文件系统的名称

- du 命令 查看当前目录下所有文件和目录的信息
> -a -h -b -c最后加上总计 -s列出各文件大小的总和 -x值计算属于同一个文件系统的文件

- fsck 分区名 硬盘检测 只能由root使用

- shutdown [选项] [时间] [警告信息]

|||
|---|---|
|-h| 关闭系统服务安全关机 |
|-k| 不关机只发出警告信息 |
|-r| 通知服务后重启 |
|-t| 在规定的时间关机|

    shutdown -h +2 两分钟后关机

- half 关机 ==shutdown -h
- half -f 强制关机

- poweroff 关机

- reboot 重启

- init 命令 切换ubuntu的运行级别
- init [0-7] 0代表关机 6代表重新启动

## 其他常用命令

- echo [选项] [字符串] 显示命令行中的字符串 用于输出提示信息

> -n 输出字符串 光标不换行

### more命令和less命令

> 对文件内容或查询结果进行分屏显示

- more [选项] file
- less [选项] file

|||
|---|---|
|-p| 不滚动，清屏 |
|+n| 从第n行开始 |
|-s| 将连续的空行压缩成一个空格|

### help命令和man命令

>显示某个命令的格式用法

- help/man 命令

- cal 显示日历
- cal [选项]  -m 以星期一为每周的第一天显示 -y 显示今年年历 -就以凯撒历显示
- cal 当前月
- cal 2000 2000年年历
- cal 5 2000 2000年5月

- date命令 显示和设定系统的时间日期

- ps [选项]查看进程信息

|||
|---|---|
|-e|显示所有进程 |
|a |显示所有终端下的进程|
|-f|以全格式显示|
|u |面向用户的格式显示|
|-r| 显示正在运行的进程|
|x |显示所有不控制终端的进程|
|-o| 以用户定义的格式显示|
|-l| 查看当前用户进程的优先级|

nice 命令 

> 改变进程优先级

- nice [选项] -n为-20-19的值 值越小优先级越高

    nice -10

- renice [+/-n] [-g 命令名] [-p 进程标识码] [-u 进程所有者]

- fg 命令 使挂起的进程返回前台执行 fg n 代表进程序号
- bg 命令 激活被挂起的进程，使之在后台运行

- kill [-信号] PID 终止进程

- top 命令 监视系统进程
- top [选项]

|||
|---|---|
|c| 显示整个命令行|
|d| 指定屏幕刷新秒数，默认3s刷新一次|
|i| 不显示任何闲置或僵死的进程|
|n| 指定每秒内监控信息的更新次数|
|p| 进程标识码列表|
|s| 是top在安全模式下运行|
|S| 使用累计模式|

last 命令 列出目前与过去登陆系统用户的信息

- last [-adRx] [-f] [-n] [账号名称] [终端机标号]

|||
|---|---|
|-a| 把从何处登入系统的主机名称或IP地址显示在最后一行|
|-d| 把地址转换成主机名称|
|-f| 指定记录文件|
|-n| 设置列出名单的显示列数|
|-R| 不显示登入系统的主机名称或IP|
|-x| 显示系统关机，重新开机以及执行等级改变等信息|

- free 查看系统内存情况 -m 以MB显示

增加用户

- useradd [-u uid] [-g group] [-d home_dir] [-s shell] [-c comment] [-m [-k skeldir]] [-N] [-f incavtive] [-e expire] login

> 例子：增加一个jack用户，指定宿主目录为/home/jack shell为/bin/bash

    sudo useradd -u 1001 -d /home/jack -m -s /bin/bash jack

增加成功，但是还不能登入系统，因为没有密码

    sudo passwd jack 设置jack密码

- userdel 删除用户信息
- userdel [-r] login

`userdel -r jack 删除jack用户`

groupadd 组增加 groupdel 组删除

- sudo passwd root 给root重设密码 激活root
- su root 切换到root
- sudo passwd -l root 锁定root

- sudo 命令既super do 指以超级管理员的身份执行
- passwd  用户  修改用户密码  
- su 用户 切换用户 su root 当前root激活可以切换

### APT

- apt-get 命令
- aptitude 命令 

`sudo apt-get dist-upgrade 更新所有软件`

### 网络配置

- ifconfig 不带参数的显示当前网卡信息
> 修改网卡地址: ifconfig 设备名称 IP地址  netmask 子网掩码

    sudo ifconfig eth12 192.168.157.141 netmask 255.255.255.0

- DNS 配置 cat /etc/resolv.conf
- HOST cat /etc/hosts

- hostname 命令修改和显示主机名 -i 显示IP

### 网路命令

- ifconfig -interface [options] address

>-interface 指定网络接口名如 eth0、eth1

|||
|---|---|
|up|激活指定的网络接口|
|down|关闭指定网络接口|
|broadcast address|设置接口广播地址|
|pointopoint|启用点对点方式|
|address|设置指定接口设备IP地址|
|netmask address|设置接口子网掩码|

- ping命令 测试主机网络是否畅通

`ping [选项] 主机名或IP`

|||
|---|---|
|-c|设置响应次数|
|-d|使用Socket的SO_DEBUG功能|
|-f|极限检查|
|-i|指定收发时间间隔单位为s|
|-s|byte 设置数据包大小|
|-R|记录路由过程|
|-r|忽略普通的路由表直接发送到远程主机|
|-p|设置填满数据包的范本样式|
|-q|不显示命令的执行过程只显示结果|
|-t|设置存活数值TTL|
|-v|详细显示命令的执行过程|

- netstat 命令 检查网络端口的连接情况

netstat [选项]

|||
|---|---|
|-a|显示所有有效的连接信息|
|-r|显示路由的信息|
|-i|显示interface网络界面信息的内容|
|-n|使用IP代替名称，显示网络情况|
|-o|显示计时器|
|-h|在线帮助|
|-c|持续列出网络状态|
|-t|显示TCP连接情况|
|-u|显示UDP连接情况|
|-v|显示命令执行过程|
|-w|显示RAW传输协议连接情况|

- ftp命令和bye命令

> ftp 登录FTP服务器，该命令允许用户使用FTP协议进行文件的传输，文件的上传和下载

    ftp 主机名/IP

> bye 退出ftp服务器

ftp 内部命令
|||
|---|---|
|ls|列出远程机的当前目录|
|cd|改变远程机的工作目录|
|lcd|改变本地的工作目录|
|close|终止当前的ftp命令|
|hash|每次传输完数据缓冲区的数据就显示一个#号|
|get|从远程机传送指定文件到本机|
|put|从本地传送指定文件到远程机|
|quit|断开与远程机的连接，退出ftp|

- telnet 命令logout命令

`telnet [选项] 主机名/IP地址`

|||
|---|---|
|-a|尝试自动登录远端系统|
|-c|不读取用户专属目录里的.telentrc文件|
|-8|允许使用8位字符资料，包括输入输出|
|-l|指定要登录远端计算机的用户名称|
|-b|使用别名指定远端主机的名称|
|-n|指定文件记录相关信息|

logout 下线命令

- rlogin 命令 远程登录

`rlogin 主机名/IP地址`

- route命令 表示手工修改产生和查看路由表

`route [选项] targetaddress [选项]`

|||
|---|---|
|-add|增加路由|
|-delete|删除路由|
|-net|路由到达的是一个网络，而不是一台主机|
|-host|路由到达的是一台主机 |
|-netmask Nm|指定路由的子网掩码|
|gw|指定路由的网关|
|[dev]If|强迫路由连接指定接口|

例子：为了访问网络，必须把计算机的IP地址设置成Linux的默认路由，增加一个路由
route add 0.0.0.0 1 Array 192.168.1.1

- finger 命令 用来查询一台主机上的登录帐号的信息，通常会显示用户名名，主目录，停滞时间，登录时间，登录Shell等

`finger [选项] [使用者] [用户名@主机名]`
-s -l -p

- mail 命令 发送邮件

Mail [-s subject] [-c address] [-b address] mail -f [mailbox]mail [-u user]

### Curl

    curl -I 网址   //返回响应头
    curl -A testagent 网址 //代理访问
    curl -e 网址1 网址2 //伪装网址的来源
    curl -i 网址 //在返回的数据中显示响应头
    curl --cacert CA证书 网址
    curl -v 网址 //显示一次请求的过程
    curl -X POST 网址 //指定访问网址的方式

### Tool Command

清除卸载后的配置文件

    dpkg -l |grep ^rc|awk '{print $2}' |sudo xargs dpkg -P
