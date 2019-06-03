# Android 相关命令

## 常用命令汇总

- 显示系统中全部Android平台：

        android list target

- 显示系统中全部AVD（模拟器）：

        android list avd

- 创建AVD（模拟器）：

        android create avd --name 名称 --target 平台编号

- 启动模拟器：

        emulator -avd 名称 -sdcard ~/名称.img (-skin 1280x800)

- 删除AVD（模拟器）：

        android delete avd --name 名称

- 创建SDCard：

        mksdcard 1024M ~/名称.img

- AVD(模拟器)所在位置：
    > Linux(~/.android/avd)
    >Windows(C:\Documents and Settings\Administrator\.android\avd)

- 启动DDMS：

        ddms

- 显示当前运行的全部模拟器：

        adb devices

- 对某一模拟器执行命令：

        abd -s 模拟器编号 命令

- 安装应用程序：

        adb install -r 应用程序.apk

- 获取模拟器中的文件：

        adb pull <remote> <local>

- 向模拟器中写文件：

        adb push <local> <remote>

- 进入模拟器的shell模式：

        adb shell

- 启动SDK，文档，实例下载管理器：

        android

- 缷载apk包：
  
        adb shell
        cd data/app
        rm apk包
        exit
        adb uninstall apk包的主包名
        adb install -r apk包

- 查看adb命令帮助信息：

        adb help

- 在命令行中查看LOG信息：

        adb logcat -s 标签名

- adb shell后面跟的命令主要来自：
    > 源码\system\core\toolbox目录和源码\frameworks\base\cmds目录。

- 删除系统应用：

        adb remount （重新挂载系统分区，使系统分区重新可写）。
        adb shell
        cd system/app
        rm *.apk

- 获取管理员权限：

        adb root

- 启动Activity：

     adb shell am start -n 包名/包名＋类名（-n 类名,-a action,-d date,-m MIME-TYPE,-ccategory,-e 扩展数据,等）。

- 发布端口：
    > 你可以设置任意的端口号，做为主机向模拟器或设备的请求端口。如：

    adb forward tcp:5555 tcp:8000

- 复制文件：
    > 你可向一个设备或从一个设备中复制文件，

        复制一个文件或目录到设备或模拟器上：
        adb push <source> <destination></destination></source>

    如：adb push test.txt /tmp/test.txt

        从设备或模拟器上复制一个文件或目录：
        adb pull <source><destination></destination></source>

    如：adb pull /addroid/lib/libwebcore.so .

- 搜索模拟器/设备的实例：
    > 取得当前运行的模拟器/设备的实例的列表及每个实例的状态：

        adb devices

- 查看bug报告：

        adb bugreport

- 记录无线通讯日志：
    > 一般来说，无线通讯的日志非常多，在运行时没必要去记录，但我们还是可以通过命令，设置记录：

        adb shell
        logcat -b radio

- 获取设备的ID和序列号：

        adb get-product
        adb get-serialno

- 访问数据库SQLite3

        adb shell
        sqlite3

        #cd system/sd/data //进入系统内指定文件夹
        #ls //列表显示当前文件夹内容
        #rm -r xxx //删除名字为xxx的文件夹及其里面的所有文件
        #rm xxx //删除文件xxx
        #rmdir xxx //删除xxx的文件夹

- 点亮屏幕

        adb shellinput keyevent 82

日志相关：

- 命令行显示Log
    复制代码 代码如下:

        adb logcat

- 根据tagname过滤
    复制代码 代码如下:

        adb logcat -s TAG_NAME
        adb logcat -s TAG_NAME_1 TAG_NAME_2

- 优先过滤
    > 显示一个特定的优先级警告及以上的日志。

    复制代码 代码如下:

        adb logcat "*:PRIORITY"

    `adb logcat "*:W"`

    优先级:
    V — 细则 (最低优先级)
    D — 调试
    I — 信息
    W — 警告
    E — 错误
    F — 致命
    S — 静默 (最高优先级，不会打印任何信息)

    使用grep过滤
    这个很像在Linux上使用管道命令一样，需系统支持

    复制代码 代码如下:

        adb logcat | grep "SEARCH_TERM"
        adb logcat | grep "SEARCH_TERM_1\|SEARCH_TERM_2"

    example:

        adb logcat | grep "Exception"
        adb logcat | grep "Exception\|Error"

- 清除日志块

    使用来清除旧的日志

    复制代码 代码如下:

        adb logcat -c

> adb对于Android程序员来说在日常的工作中使用频率很高，现将自己工作中常用的adb命令总结一下备忘，方便查询，也供大家参考。查看应用内存占用，耗电信息，启动时间，wakelock，跑monkey的命令在之前的应用性能优化中起了不小的作用。以下adb命令的测试机器为小米3,其中package_name代表包名。

## 基础脚本

1. 启动adb服务

        adb start-server

2. 终止adb服务

        adb kill-server

3. 进入adb运行环境

        adb shell

4. 获取帮助 里面有adb的各种命令和参数的介绍

        adb help

5. 查看adb版本

        adb version

6. 以root权限重启adb

        adb root

7. 将system分区重新挂在为可读写分区，此命令在操作系统目录时很重要

        adb remount

8. 重启设备,可选参数进入bootloader(刷机模式)或recovery(恢复模式)

        adb reboot [bootloader|recovery]

## apk相关

1. 安装apk

        adb install test.apk -r 覆盖安装，保留数据和缓存文件  -d 解决低版本version问题  -s 安装apk到sd卡

2. 卸载apk

        adb uninstall -k <package_name>

    可选参数-k的作用为卸载软件但是保留配置和缓存文件

3. 查看app相关所有信息，包括action,codepath,version,需要的权限等等信息

        adb shell dumpsys package <package_name>

4. 查看app的路径

        adb shell pm path <package_name>

    查看了一个普通app的路径，如下，位于data/app下面的普通app
    package:/data/app/com.tencent.test-1/base.apk

5. 查看apk的版本信息

    adb shell dumpsys package <package_name> | grepversion

    如果你得到的是下图的两个version版本，则为系统app,下面是系统app本身的版本，上面是升级之后的系统app的版本信息

    versionCode=8 targetSdk=22  versionName=V- 08 versionCode=6targetSdk=22  versionName=V- 0

6. 启动activity

    查看：

        adb shell dumpsys window | findstr mCurrentFocus  命令查看当前运行的包名和Activity

        adb shell am start -n<package_name>/.<activity_class_name>

7. 清楚应用数据

        adb shell pm path <PACKAGE>

    输出安装包的APK路径

        adb shell pm clear <PACKAGE>

    删除与包相关的所有数据：清除数据和缓存

8. 获得应用的启动时间，可以很方便地获取应用的启动时间

        adb shell am start -W<package_name>/.<activity_class_name>

    试验结果如下:

    adb shell am start -Wcom.cc.test/com.painter.test.PainterMainActivity Starting: Intent {act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER]cmp=com.cc.test/com.painter.test.PainterMainActivity } Status: ok Activity:com.cc.test/com.painter.test.PainterMainActivity ThisTime: 355 TotalTime: 355WaitTime: 365 Complete

    返回的几个结果，以WaitTime为准，返回的是从startActivity到应用第一帧完全显示的时间。

9. 启动service,am的-n参数表示组件，-a参数表示命令，-a后面的参数为manifest中定义的service的action

        adb shell am startservice -n<package_name>/.<service_class_name>

    也可:

        adb shell am startservice -a"android.intent.action.CALL"

10. 发送广播

        adb shell am broadcast -a"android.intent.action.AdupsFota.WriteCommandReceiver"

    广播可以带上不同类型的参数,–es为string参数类型,–ei为int参数类型,–ez为boolean参数类型

        adb shell am broadcast -a "android.intent.action.AdupsFota.WriteCommandReceiver"--es test_string "this is test string"

11. 查看某个app的进程相关信息

        adb shell ps <package_name|PID>

    例如

        adb shell ps com.ma.app:push USER  PID PPID VSIZE RSS WCHAN   PC NAME u0_a116 5483 304 1776564 55112sys_epoll_ 00000000 S com.ma.app:push


        adb shell ps | grep <package_name>

12. 杀掉某个进程，一般用于模拟某个bug复现

        adb shell kill pidNumber

13. 查看某一个app的内存占用

        adb shell dumpsys meminfo <package_name|PID>

    结果如下，其中的Heap size包括了Dalvik Heap和Native Heap,平时我们所说的内存限制指的是Dalvik Heap。

    Pss PrivatePrivate Swapped  Heap  Heap Heap     Total Dirty CleanDirty  Size Alloc  Free    ------ ------ ------ ------ ------ ------ ------ Native Heap 1895618940  0 4696 44288 21352 22935 Dalvik Heap 60102 60088  0 26192 104486 88285 16201 App Summary      Pss(KB)       ------  Java Heap: 61640   Native Heap:18940     Code:  3356   Stack:  428    Graphics: 16876  Private Other:  3840   System:  2031 ...

14. 查看单个应用程序的最大内存限制

        adb shell getprop | grep heapgrowthlimit

    得到的结果为128M: [dalvik.vm.heapgrowthlimit]: [128m]

    这就是说Dalvik Heap size的最大值超过了128M，就很可能发生OOM

15. 获取单个应用的电量消耗信息

    Battery Historian是Android - 0开始引入的，下面的命令为获取单个app的电量消耗信息，获取系统耗电信息见下节

        adb shell dumpsys batterystats ><package_name> > xxx.txt

    上面的电量信息为原始数据，可以通过google编写的historian.py脚本把数据信息转换为可读性很好的html文件,类似TraceView生成的列表数据，之前在做app性能优化的时候起了很大的作用

        python historian.py xxx.txt > xxx.html

16. 跑monkey,个人很喜欢这个命令，运行过程中，应用程序会不断切换画面，按照选定的不同级别反馈信息，还可以看到执行过程报告和生成的事件。测试应用的稳定性时很实用。现在studio也有了monkeyrunner的tool

        adb shell monkey -v -p <package_name> 500  -p 对象包  -v 反馈信息级别

    :Monkey: seed=1493061025112 count=500 :AllowPackage:com.tencent.mm.app:push :IncludeCategory: android.intent.category.LAUNCHER:IncludeCategory: android.intent.category.MONKEY ** No activities found to run,monkey aborted.

## 系统相关

1. 查看设备名称，豌豆荚等应用就是通过此来获得设备的名称

        adb shell cat /system/build.prop/

    结果:

    ro.product.model=MI 3W ro.product.brand=Xiaomi

2. 查看手机分辨率有两种方法，第二种方法最为简洁

        adb shell dumpsys window | grep Surface

    grep是一个非常有用的参数,具体含义和用法大家自行google一下,试验结果为1080 * 1920:

    Surface: shown=false layer=21000 alpha=- 0rect=(- 0,- 0) - 0 x - 0

        adb shell wm size

    返回结果为:

    Physical size: 1080x1920

3. 查看手机sdk版本

        adb shell getprop | grep version

    运行上面的命令后，列出来的version中[ro.build.version.release]: [- - 1]即为手机sdk版本

4. 查看手机型号信息

        adb shell getprop | grep product

    运行此命令之后，能看到product,board,brand和cpu等等的型号

5. 获取序列号，获取到的序列号即为adb devices列出来的序列号

        adb get-serialno

6. 查看连接的设备

        adb devices

    如果有多个设备连接，想对其中的某一设备进行操作，就需要在此命令的后面加参数

        adb [-d|-e|-s <serial Number>] <command> 　　 -d:真机(多个设备中只有一个真机时适用) 　　 -e:模拟器(多个设备中只有一个模拟器时适用) 　　 -s:序列号

    假如有两个真机连接了我的电脑，adb devices获取到的数据如下

    List of devices attached 1b71651 device 12sdfsd device

    进入1b71651设备的命令为:

        adb -s 1b71651 shell

7. 查看wifi密码(需要root权限)

        adb shell cat data/misc/wifi/*.conf

8. 查看wifi_mac

        adb shell cat /sys/class/net/wlan0/address

    运行此命令，93:a1:a2:91:d1:c1是小米3的wifi_mac地址

9. 查看后台services信息

        adb shell service list

    运行结果为

    Found 126 services: 0 miui.whetstone.net:[miui.whetstone.INetworkService] 1 miui.whetstone.power: [miui.whetstone.power]...

10. 查看系统当前内存占用，为综述

        adb shell cat /proc/meminfo

    运行结果:

    MemTotal: 1893504 kB MemFree:   81200 kBBuffers:   14828 kB Cached:   244152 kB SwapCached:  15152 kB Active:   541680 kB Inactive:   575280 kB ...

11. 查看各进程详细内存占用和系统的内存占用有几种方法

    - 第一种
  
            adb shell dumpsys meminfo

        Total PSS SWAP by process:  97858 kB: 34944 kB: com.android.systemui (pid2769)  ... Total RAM: 1893504 kB (statusnormal) Free RAM: 344212 kB (117992 cached pss + 136016 cached kernel + 90204free)

        Total PSS 信息就是你的应用真正占据的内存大小，通过这个信息，可以轻松判别手机中哪些程序占内存比较大。

    - 查看各进程内存的另一方法 不是所有设备都支持

            adb shell procrank

        运行结果如下:

        PID  Vss  Rss Pss  Uss cmdline 496 1810184K92744K 57607K 48348K system_server 743 1847492K 84492K 52117K 46796Kcom.android.systemui ....       ------------ ------       328259K 261484K TOTALRAM: 2061012K total, 889152K free, 40940K buffers, 612032K cached, 300K shmem,62556K slab

        其中

        VSS – Virtual Set Size 虚拟耗用内存（包含共享库占用的内存） RSS – Resident Set Size 实际使用物理内存（包含共享库占用的内存） PSS – Proportional Set Size 实际使用的物理内存（比例分配共享库占用的内存） USS – Unique Set Size 进程独自占用的物理内存（不包含共享库占用的内存）

    - 查看设备上进程的cpu和内存占用情况

            adb shell top

12. 查看系统耗电情况

        adb shell dumpsys batterystats > xxx.txt

13. 查看系统设置的闹钟

        adb shell dumpsys alarm

14. 查看系统的wakelock,不合理的使用wakelock会导致系统耗电加剧

        adb shell dumpsys power

    返回结果:

    Wake Locks: size=2 PARTIAL_WAKE_LOCK    'AudioMix' (uid=1013, pid=236,ws=WorkSource{10018}) PARTIAL_WAKE_LOCK   'Android.media.MediaPlayer' ON_AFTER_RELEASE (uid=10018, pid=24023,ws=null)

## 文件操作相关

1. 拷贝文件/目录到设备

        adb push <local>...<remote>

2. 从设备拷贝文件/目录，-a参数保留了文件的时间戳和模式

        adb pull [-a] <remote>...<local>

3. 查看设备log,和studio和eclipse的logcat相同，可通过参数控制输出的日志

        adb logcat  -s 过滤指定参数log  -v time 保留日志时间  >> 追加写  > 覆盖写

    下面的命令含义为:打印出log信息中的时间并且包含关键字“Test” 的所有log以覆盖写的方式保存到test文件

        adb logcat -v time -s Test > test.txt

4. 列出目录下的文件和文件夹，可选参数-al可查看文件和文件夹的详细信息

        adb shell ls [-al]

5. 进入文件夹

        adb shell cd <folder>

6. 查看文件

        adb shell cat <filename>

7. 重命名文件

        adb shell rename path/oldfilename path/newfilename

8. 删除文件/文件夹

        adb shell rm path/filename  -r 可选参数用于删除文件夹及下面的所有文件 eg:adb shell rm -r <folder>

9. 移动文件

        adb shell mv path/filename newpath/filename

10. 拷贝文件

        adb shell cp file newpath/file1

11. 创建目录

        adb shell mkdir path/folder

12. 设置文件最高读写权限

        adb shell chmod 777 filename

13. 手机未root 查看data/data/某一app文件信息

        笔者的小米3没有root，但是又想方便地查看data/data/目录下的一些文件，直接进入data会提示没有权限,查看的方式为进入data/data/后,运行下面的命令，就能直接进入你应用的包下了,可通用cp或者mv拷贝或移动到sdcard目录进行其他操作

        run-as package_name

##　数据库相关

1. 操作db

sqlite3 test.db