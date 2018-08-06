# My Git UserGuide

## 1 基本知识

1. Git 直接记录快照，而非比较差异。
2. 每次提交更新会纵览一边所有文件的指纹信息并对文件作快照，然后保存指向这一个快照的索引。
3. 为了提高性能，若文件没有变化，不会再次保存，和上次的快照进行链接。
4. 近乎所有操作都是本地执行
5. 时刻保持数据的完整性
   
	- 保存Git之前，所有数据都进行内容的检验和计算
	- 使用SHA-1算法计算数据的校验和，通过对文件的内容或目录的结构计算出一个SHA-1哈希值，作为指纹串。
  
6. 多数操作仅添加数据。
7. 文件的三种状态：已提交（committed）、已修改（modified）、已暂存（staged）
	- Git管理项目时，文件的三个工作区域：git工作目录，暂存区域，以及本次仓库。 

### 1.1 Git 的工作流程

1. 在工作目录中修改某些文件。
2. 对修改的文件进行快照，然后保存到暂存区域。
3. 提交更新，将保存在暂存区域的文件快照永久转储到Git目录中。

### 1.2 初次允许Git的配置

> Git 提供 git config 工具，专门用来配置和读取相应的工作环境变量，正是这些环境变量，决定了Git在各个环节的具体工作方式和行为，这些变量可以存放在一下三个不同的地方。

- /etc/gitconfig文件：系统中所有用户都普遍适用的配置，git config --system 读写这个文件
- ~/.gitconfig文件：用户目录下的配置文件适用于该用户，git config --global 读写这个文件
- 当前项目的git目录的配置文件（也就是工作目录下的配置文件.git/config）:这个配置只对当前项目有效，每一个级别的配置都会覆盖上一层相同的配置。
	
### 1.3 用户信息

	git config --global user.name "Dx"
	git config --global user.email gudongxian@qq.com

### 1.4 文本编辑器
> 设置默认使用的文本编辑器，Git需要输入额外信息的时候，会自动调用一个外部文本编辑器。

	git config --global core.editor 编辑器

### 1.5 差异分析工具
> 在合并冲突使用哪种差异分析工具，Git 可以理解 kdiff3，tkdiff，meld，xxdiff，emerge，vimdiff，gvimdiff，ecmerge，和 opendiff 等合并工具的输出信息。当然，你也可以指定使用自己开发的工具,比如改用vimdiff的话
	
	git config --global merge.tool vimdiff

### 1.6 查看配置信息

	git config --list
	git config user.name #查看指定的配置

### 1.7 获取帮助

	git help <verb>
	git <verb> --help
	man git-<verb>

	git help config

## 2 Git基础

### 2.1 取得项目的Git仓库

**两种方法**

1. 在现存的目录下，通过导入所有的文件夹创建新的git仓库。
2. 从已有的Git仓库克隆出一个新的镜像仓库。

-----

#### 2.1.1 在工作目录中创建新的仓库

对某个项目开始用Git管理，只需到此项目所在的目录执行

    git init

初始化后，会出现。.git目录，所有Git需要的资源和数据都放在这个仓库。

如果当前目录下有几个文件想要纳入版本控制，需要用**git add**命令告诉Git开始对这个文件进行跟踪，然后提交：

	git add *.c
	git add README
	git commit -m 'initial project version'

#### 2.1.2 从现有的仓库克隆

	git clone [url] [new name]

从一个url克隆仓库，可以选择指定新的名字。

### 2.2 记录每次更新到仓库

#### 2.2.1 检查当前文件状态

要确定文件当前处于什么状态，可以用**git status**。
	
	git status

#### 2.2.2 跟踪新文件

	git add file 

运行**git status**会发现当前文件已被跟踪，并处于暂存状态。

**git add**的潜台词就是把目标文件的快照放入暂存区域（add file into staged area）,同时未曾跟踪的文件标记为需要跟踪

#### 2.2.3 暂存已修改文件

将已经**git add**加入到暂存区的文件进行修改，修改后的文件会出现在已修改区域，需要再次加入暂存区才能提交最新的版本，不然提交的是**git add**时的版本。

#### 2.2.4 忽略某些文件

创建.gitignore,列出要忽略的文件模式。

文件 .gitignore 的格式规范如下：

- 所有空行或者以注释符号 ＃ 开头的行都会被 Git 忽略。
- 可以使用标准的 glob 模式匹配。
- 匹配模式最后跟反斜杠（/）说明要忽略的是目录。
- 要忽略指定模式以外的文件或目录，可以在模式前加上惊叹号（!）取反。

> glob模式是指shell所使用的简化的正则表达式，星号（*）匹配零个或多个任意字符；[abc] 匹配任何一个列在方括号中的字符（这个例子要么匹配一个 a，要么匹配一个 b，要么匹配一个 c）；问号（?）只匹配一个任意字符；如果在方括号中使用短划线分隔两个字符，表示所有在这两个字符范围内的都可以匹配（比如 [0-9] 表示匹配所有 0 到 9 的数字）。

demo

	# 此为注释 – 将被 Git 忽略
	# 忽略所有 .a 结尾的文件
	*.a
	# 但 lib.a 除外
	!lib.a
	# 仅仅忽略项目根目录下的 TODO 文件，不包括 subdir/TODO
	/TODO
	# 忽略 build/ 目录下的所有文件
	build/
	# 会忽略 doc/notes.txt 但不包括 doc/server/arch.txt
	doc/*.txt
	# ignore all .txt files in the doc/ directory
	doc/**/*.txt


#### 2.2.5 查看已暂存和未暂存的更新

**git status**的显示比较简单，仅仅是列出了修改过的文件，如果要查看修改了什么地方，可以用**git diff**命令。

要查看尚未暂存的文件修改了那些部分直接执行**git diff**

	git diff

此命令比较工作目录和暂存区域快照的差异，也就是修改之后还未暂存的变化。

要查看已经暂存的文件和上次提交的快照的差异使用**git diff --staged/--cached**

#### 2.2.6 提交更新

	git commit [-m]

提交暂存区的文件到本地仓库，包括提交的信息。返回当前的分支，以及SHA-1值，修改的行数等等。


#### 2.2.7 跳过使用暂存区域

**git commit**加上-a选项 

Git自动把已经跟踪的文件暂存起来一并提交，从而跳过git add

#### 2.2.8 移除文件

要从Git中移除文件，就必须从已跟踪文件清单中移除（确切的说，是从暂存区域移除），然后提交。

可以用**git rm**命令完成此工作，并连带从工作目录中删除指定文件，这样以后就不会出现在未跟踪文件清单中了。


如果只是简单的从工作目录中手工删除文件，运行**git status**就会出现*Changes not staged for commit*部分（也就是未暂存清单）看到。
	
	rm file

然后在运行**git rm file**记录此次移除文件的操作

	git rm file

最后提交的时候，该文件就不会纳入版本管理了，如果删除之前修改过并且以及放入暂存区域的话，必须使用强制删除选项 -f，以防误删文件丢失修改内容。

另一种，我们想把文件从Git仓库删除（也从暂存区域删除），但仍保存在工作目录中。

比如	一些大型日志文件或编译文件，不小心纳入仓库，要移除跟踪但不删除文件，以便稍后在*.gitignore*文件补上。

	git rm --cached readme.txt

后面可以列出文件或者目录的名字，也可以使用 glob 模式。比方说：

	git rm log/\*.log

注意到星号 * 之前的反斜杠 \，因为 Git 有它自己的文件模式扩展匹配方式，所以我们不用 shell 来帮忙展开（译注：实际上不加反斜杠也可以运行，只不过按照 shell 扩展的话，仅仅删除指定目录下的文件而不会递归匹配。上面的例子本来就指定了目录，所以效果等同，但下面的例子就会用递归方式匹配，所以必须加反斜杠。）。此命令删除所有 log/ 目录下扩展名为 .log 的文件。类似的比如：

	git rm \*~

会递归删除当前目录及其子目录中所有 ~ 结尾的文件。


#### 2.2.9 移动文件

	git mv file_from file_to 

运行**git rm**相当于下面三条命令：

	mv file_from file_to
	git rm file_from
	git add file_to

在使用其他工具批处理改名的话，要记得在提交前删除老的文件名，添加新的文件名。

### 2.3 查看提交历史

在提交了若干更新之后，或者克隆了某个项目，想回顾提交历史可以使用**git log**查看

	git log

默认不加任何参数**git log**按照提交时间列出所有更新，最近的更新排在最上面，每次更新都有一个SHA-1值，作者，电子邮件，提交时间和提交说明。


我们常用**-p**参数显示每次提交的内容差异，用**-2**显示最近两次更新。

	git log -p -2

该选项除了显示基本信息外，还显示每次commit的变化。当进行代码审查或者快速浏览某个搭档提交的commit变化的时候就很有用了。

某些时候，单词层面的对比，比行层面的对比，更加容易观察，Git提供**--word-diff**选项	,我们可以加到-p后面。


	git log -U1 --word-diff


在结果中，新增的单词背*｛+ +｝*括起来，被删除的单词背*[- -]*括起来，在进行单词对比的时候，你可能希望上下文的行数从默认的三行减为一行，那么可以使用-U1选项。

另外**git log**还提供需要其他选项，比如*--stat*，仅显示简要的增改行统计。

还有常用的*--pretty*选项，可以指定完全不同于默认格式的方式展示提交历史，比如oneline，short,full,fuller可以用。

	git log --pretty=oneline

一行显示信息。

最有意思的format，可以定制显示的记录格式

	git log --pretty=format:"%h - %an,%ar : %s"


<table>
	<thead>
		<tr>
			<td>选项</td>
			<td>说明</td>		
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>%H</td>	
			<td>提交对象（commit）的完整哈希字串</td>
		</tr>
		<tr>
			<td>%h</td>	
			<td>提交对象的简短哈希字串</td>
		</tr>
		<tr>
			<td>%T</td>	
			<td>树对象（tree）的完整哈希字串</td>
		</tr>
		<tr>
			<td>%t</td>	
			<td>树对象的简短哈希字串</td>
		</tr>
		<tr>
			<td>%P</td>	
			<td>父对象（parent）的完整哈希字串</td>
		</tr>
		<tr>
			<td>%p</td>	
			<td>父对象的简短哈希字串</td>
		</tr>
		<tr>
			<td>%an</td>	
			<td>作者（author）的名字</td>
		</tr>
		<tr>
			<td>%ae</td>	
			<td>作者的电子邮件地址</td>
		</tr>
		<tr>
			<td>%ad</td>	
			<td>作者修订日期（可以用 -date= 选项定制格式）</td>
		</tr>
		<tr>
			<td>%ar</td>	
			<td>作者修订日期，按多久以前的方式显示</td>
		</tr>
		<tr>
			<td>%cn</td>	
			<td>提交者(committer)的名字</td>
		</tr>
		<tr>
			<td>%ce</td>	
			<td>提交者的电子邮件地址</td>
		</tr>
		<tr>
			<td>%cd</td>	
			<td>提交日期</td>
		</tr>
		<tr>
			<td>%cr</td>	
			<td>提交日期，按多久以前的方式显示</td>
		</tr>
		<tr>
			<td>%s</td>	
			<td>提交说明</td>
		</tr>
	</tbody>
</table>


作者（author）和提交者（committer）之间究竟有何差别，其实作者指的是实际作出修改的人，提交者指的是最后将此工作成果提交到仓库的人。

用 oneline 或 format 时结合 --graph 选项，可以看到开头多出一些 ASCII 字符串表示的简单图形，形象地展示了每个提交所在的分支及其分化衍合情况。


其他常用的选项及其释义:

<table>
	<thead>
		<tr>
			<td>选项</td>
			<td>说明</td>		
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>-p</td>	
			<td>按补丁格式显示每个更新之间的差异。</td>
		</tr>
		<tr>
			<td>--word-diff</td>	
			<td>按 word diff 格式显示差异。</td>
		</tr>
		<tr>
			<td>--stat</td>	
			<td>显示每次更新的文件修改统计信息。</td>
		</tr>
		<tr>
			<td>--shortstat</td>	
			<td>只显示 --stat 中最后的行数修改添加移除统计</td>
		</tr>
		<tr>
			<td>--name-only</td>	
			<td>仅在提交信息后显示已修改的文件清单。</td>
		</tr>
		<tr>
			<td>--name-status</td>	
			<td>显示新增、修改、删除的文件清单。</td>
		</tr>
		<tr>
			<td>--abbrev-commit</td>	
			<td>仅显示 SHA-1 的前几个字符，而非所有的 40 个字符。</td>
		</tr>
		<tr>
			<td>--relative-date</td>	
			<td>使用较短的相对时间显示（比如，“2 weeks ago”）。</td>
		</tr>
		<tr>
			<td>--graph</td>	
			<td>显示 ASCII 图形表示的分支合并历史。</td>
		</tr>
		<tr>
			<td>--pretty</td>	
			<td>使用其他格式显示历史提交信息。可用的选项包括 oneline，short，full，fuller 和 format（后跟指定格式）。</td>
		</tr>
		<tr>
			<td>--oneline</td>	
			<td>--pretty=oneline --abbrev-commit 的简化用法。</td>
		</tr>
	</tbody>
</table>
	
#### 2.3.1 限制输出长度

除了定制输出格式，还可以定制输出长度。使用**-<n>**显示最近提交的若干次提交，n为自然数，实践中不太用这个选项，Git在输出所有提交的时候会自动调用分页程序（less）,要看更早只需要翻到下页就行了，另外还有按时间做限制的选项，如**--since和--until**,下面的命令列出最近两周提交：

	git log --since=2.weeks

你可以给出各种时间格式或者多久以前，还可以给出若干搜索条件,如**--author**选项显示指定作者的提交，用**--grep**选项搜索提交中的关键字，（如果要同时满足这两个条件需要使用**--all-match**选项，否则满足任意一个条件都会被匹配出来。）

另一个真正实用的**git log**选项是路径（path）,可以在 git log 选项的最后指定它们的路径。因为是放在最后位置上的选项，所以用两个短划线（--）隔开之前的选项和后面限定的路径名。


<table>
	<thead>
		<tr>
			<td>选项</td>
			<td>说明</td>		
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>-(n)</td>
			<td>仅显示最近的 n 条提交</td>		
		</tr>
		<tr>
			<td>--since, --after</td>
			<td>仅显示指定时间之后的提交。</td>		
		</tr>
		<tr>
			<td>--until, --before</td>
			<td>仅显示指定时间之前的提交。</td>		
		</tr>
		<tr>
			<td>--author</td>
			<td>仅显示指定作者相关的提交。</td>		
		</tr>
		<tr>
			<td>--committerr</td>
			<td>仅显示指定提交者相关的提交。</td>		
		</tr>
	</tbody>
</table>

来看一个实际的例子，如果要查看 Git 仓库中，2008 年 10 月期间，Junio Hamano 提交的但未合并的测试脚本（位于项目的 t/ 目录下的文件），可以用下面的查询命令：

	git log --pretty="%h - %s" --author=gitster --since="2008-10-01" \
   	--before="2008-11-01" --no-merges -- t/

#### 2.3.2 使用图形化工具查阅提交历史

随 Git 一同发布的 gitk 就是这样一种工具

### 2.4 撤消操作

#### 2.4.1 修改最后一次提交

有时候我们提交完了才发现漏掉了几个文件没有加，或者提交信息写错了。想要撤消刚才的提交操作，可以使用 --amend 选项重新提交：

	git commit --amend

此命令将使用当前的暂存区域快照提交。如果刚才提交完没有作任何改动，直接运行此命令的话，相当于有机会重新编辑提交说明，但将要提交的文件快照和之前的一样。

	git commit -m "initial commit"
	git add forget_file
	git commit --amend

上面的命令只产生一个提交，第二次提交命令，修改了第一个的提交内容。

#### 2.4.2 取消已经暂存的文件

使用**git reset HEAD file**取消暂存的文件

#### 2.4.3 取消对文件的修改

如果觉得刚才的修改没有必要，可以取消当前的修改，回到之前修改之前的版本

	git checkout -- file

务必确认需要取消修改。

任何提交到Git的都可以被恢复，即便在已经删除的分支中的提交，或者用 --amend 重新改写的提交，都可以被恢复。所以，你可能失去的数据，仅限于没有提交过的，对 Git 来说它们就像从未存在过一样。

### 2.5 远程仓库的使用 

远程仓库指托管在网络上的项目仓库，可能会有好多个，其中有些你只能读，另外有些可以写。同他人协作开发某个项目时，需要管理这些远程仓库，以便推送或拉取数据，分享各自的工作进展。 管理远程仓库的工作，包括添加远程库，移除废弃的远程库，管理各式远程库分支，定义是否跟踪这些分支，等等。

#### 2.5.1 查看当前的远程库

要查看当前配置有哪些远程仓库，可以使用**git remote**命令，它会列出每个远程仓库简短的名字，在克隆完某个远程仓库，至少看到一个名为*orgin*的远程库。

	git remote

加上 -v(verbose【繁杂的，详细的】),显示对应的克隆地址。
	
	git remote -v

#### 2.5.2 添加远程仓库

要添加一个新的远程仓库，可以指定一个简单的名字，以便将来引用，运行 **git remote add [shortname] [url]**

	git remote add pb git://github.com/gudongxian/pd.git

现在可以用字符串 pb 指代对应的仓库地址了。比如说，要抓取所有 pb 有的，但本地仓库没有的信息，可以运行 	
	git fetch pb

现在，Paul 的主干分支（master）已经完全可以在本地访问了，对应的名字是 pb/master，你可以将它合并到自己的某个分支，或者切换到这个分支，看看有些什么有趣的更新。

#### 2.5.3 从远程仓库抓取数据

正如之前所看到的，可以用下面的命令从远程仓库抓取数据到本地：

	git fetch [remote-url]

如果是克隆了一个仓库，此命令会自动将远程仓库归于 origin 名下。所以，git fetch origin 会抓取从你上次克隆以来别人上传到此远程仓库中的所有更新（或是上次 fetch 以来别人提交的更新）。有一点很重要，需要记住，fetch 命令只是将远端的数据拉到本地仓库，并不自动合并到当前工作分支，只有当你确实准备好了，才能手工合并。

如果设置了某个分支用于跟踪某个远端仓库的分支，可以使用 **git pull** 命令自动抓取数据下来，然后将远端分支自动合并到本地仓库中当前分支。在日常工作中我们经常这么用，既快且好。实际上，默认情况下 git clone 命令本质上就是自动创建了本地的 master 分支用于跟踪远程仓库中的 master 分支（假设远程仓库确实有 master 分支）。所以一般我们运行 **git pull**，目的都是要从原始克隆的远端仓库中抓取数据后，合并到工作目录中的当前分支。


#### 2.5.4 推送数据到远程仓库

项目进行到一个阶段，要同别人分享目前的成果，可以将本地仓库中的数据推送到远程仓库。实现这个任务的命令很简单： git push [remote-name] [branch-name]。如果要把本地的 master 分支推送到 origin 服务器上（再次说明下，克隆操作会自动使用默认的 master 和 origin 名字），可以运行下面的命令

	git push origin master

只有在所克隆的服务器上有写权限，或者同一时刻没有其他人在推数据，这条命令才会如期完成任务。如果在你推数据前，已经有其他人推送了若干更新，那你的推送操作就会被驳回。你必须先把他们的更新抓取到本地，合并到自己的项目中，然后才可以再次推送。


#### 2.5.5 查看远程仓库信息

	git remote show [remote-name]

除了对应的克隆地址外，它还给出了许多额外的信息。它友善地告诉你如果是在 master 分支，就可以用 git pull 命令抓取数据合并到本地。另外还列出了所有处于跟踪状态中的远端分支。


#### 2.5.6 远程仓库的删除和重命名

在新版 Git 中可以用** git remote rename **命令修改某个远程仓库在本地的简称，比如想把 pb 改成 paul，可以这么运行

	git remote rename pb paul

注意，对远程仓库的重命名，也会使对应的分支名称发生变化，原来的 pb/master 分支现在成了 paul/master。

碰到远端仓库服务器迁移，或者原来的克隆镜像不再使用，又或者某个参与者不再贡献代码，那么需要移除对应的远端仓库，可以运行 **git remote rm** 命令

	git remote rm paul

### 2.6 打标签

同大多数 VCS 一样，Git 也可以对某一时间点上的版本打上标签。人们在发布某个软件版本（比如 v1.0 等等）的时候，经常这么做。本节我们一起来学习如何列出所有可用的标签，如何新建标签，以及各种不同类型标签之间的差别。

#### 2.6.1 列显已有的标签

	git tag

显示的标签按字母顺序排列，所以标签的先后并不表示重要程度的轻重。

我们可以用特定的搜索模式列出符合条件的标签。在 Git 自身项目仓库中，有着超过 240 个标签，如果你只对 1.4.2 系列的版本感兴趣，可以运行下面的命令
	
	git tag -l "v1.4.2.*"

#### 2.6.2 新建标签

Git 使用的标签有两种类型：轻量级的（lightweight）和含附注的（annotated）。轻量级标签就像是个不会变化的分支，实际上它就是个指向特定提交对象的引用。而含附注标签，实际上是存储在仓库中的一个独立对象，它有自身的校验和信息，包含着标签的名字，电子邮件地址和日期，以及标签说明，标签本身也允许使用 GNU Privacy Guard (GPG) 来签署或验证。一般我们都建议使用含附注型的标签，以便保留相关信息；当然，如果只是临时性加注标签，或者不需要旁注额外信息，用轻量级标签也没问题。


#### 2.6.3 含附注的标签

创建一个含有	附注类型的标签，用**-a(annotated)**指定标签的名字：

	git tag -a v1.1 -m "my version 1.1"

**-m**选项指定标签的说明，可以使用**git show** 命令查看相应标签的版本信息。

	git show v1.1

#### 2.6.4 签署标签

如果你有自己的私钥，还可以用GPG来签署标签，把**-a**改成**-s**（signed）即可：

	git tag -s v1.1 -m "my signed 1.1 tag"

#### 2.6.5 轻量级标签

不需要加任何选项直接给出标签名就行：

	git tag v1.1

#### 2.6.6 验证标签

使用**git tag -v [tag-name]** *(-v verify)*,这个命令会使用GPG,所以你需要签署者的公钥，存放在keyring中，才能验证：

	git tag -v v1.1

#### 2.6.7 后期加注标签

可以在后期对早期的某次提价添加标签。

	git log --pretty=oneline

只要在打标签的时候跟上对应标签的检验或者前几个字符。

	git tag -a v1.1 abcd23


#### 2.6.8 分享标签

默认情况下，**git push**不会把标签传送到远端服务器上，只能通过显示命令分享标签到远程仓库中，命令格式和推送分支相似，**git push origin [tag-name]**

	git push origin v1.5

如果一次性推送本地新增的标签上去，使用**--tags**选项：

	git push origin --tags

#### 2.7 技巧和窍门

#### 2.7.1 自动补全

如果你用的是 Bash shell，可以试试看 Git 提供的自动补全脚本。下载 Git 的源代码，进入 contrib/completion 目录，会看到一个 git-completion.bash 文件。将此文件复制到你自己的用户主目录中（译注：按照下面的示例，还应改名加上点：cp git-completion.bash ~/.git-completion.bash），并把下面一行内容添加到你的 .bashrc 文件中：

	source ~/.git-completion.bash

也可以为系统上所有用户都设置默认使用此脚本。Mac 上将此脚本复制到 **/opt/local/etc/bash_completion.d** 目录中，Linux 上则复制到 **/etc/bash_completion.d/** 目录中。这两处目录中的脚本，都会在 Bash 启动时自动加载。

如果在 Windows 上安装了 msysGit，默认使用的 Git Bash 就已经配好了这个自动补全脚本，可以直接使用。

在输入 Git 命令的时候可以敲两次跳格键（Tab），就会看到列出所有匹配的可用命令建议：

	$ git co<tab><tab>
	commit config

命令的选项也可以用这种方式自动完成，其实这种情况更实用些。比如运行 git log 的时候忘了相关选项的名字，可以输入开头的几个字母，然后敲 Tab 键看看有哪些匹配的：

	$ git log --s<tab>
	--shortstat  --since=  --src-prefix=  --stat   --summary


#### 2.7.2 git 命令别名

Git 并不会推断你输入的几个字符将会是哪条命令，不过如果想偷懒，少敲几个命令的字符，可以用 git config 为命令设置别名。来看看下面的例子：


    $ git config --global alias.co checkout
    $ git config --global alias.br branch
    $ git config --global alias.ci commit
    $ git config --global alias.st status

现在，如果要输入 git commit 只需键入 git ci 即可。而随着 Git 使用的深入，会有很多经常要用到的命令，遇到这种情况，不妨建个别名提高效率。

使用这种技术还可以创造出新的命令，比方说取消暂存文件时的输入比较繁琐，可以自己设置一下：

	$ git config --global alias.unstage 'reset HEAD --'

这样一来，下面的两条命令完全等同：
    
    $ git unstage fileA
    $ git reset HEAD fileA

显然，使用别名的方式看起来更清楚。另外，我们还经常设置 last 命令：
	
	$ git config --global alias.last 'log -1 HEAD'

然后要看最后一次的提交信息，就变得简单多了：

	$ git last

可以看出，实际上 Git 只是简单地在命令中替换了你设置的别名。不过有时候我们希望运行某个外部命令，而非 Git 的子命令，这个好办，只需要在命令前加上 ! 就行。如果你自己写了些处理 Git 仓库信息的脚本的话，就可以用这种技术包装起来。作为演示，我们可以设置用 git visual 启动 gitk：

	$ git config --global alias.visual '!gitk'

### 小结

到目前为止，你已经学会了最基本的 Git 本地操作：创建和克隆仓库，做出修改，暂存并提交这些修改，以及查看所有历史修改记录。接下来，我们将学习 Git 的必杀技特性：分支模型。

## 3 git 分支