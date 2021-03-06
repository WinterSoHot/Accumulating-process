# Grammar
##  ##
## 15 指针

## 19,20 字符串

## 21 结构体

## 22 位域

## 23 共同体

## 24 位域

## 25 typedef

## 26 输入输出

## 27,28 文件读写

## 29 预处理器

## 30 宏定义

## 31 头文件

## 32,33 错误处理

## 34 递归

## 35 可变参数

## 36 内存管理

## 37 可变参数

# 数据结构

1. 数据  数据元素 数据项 数据对象 数据结构

	> 数据：描述客观事务的符号，可以被计算机操作，能够被计算机识别并且处理的符号集合。

	> 数据元素：是组成数据的基本单位，也被称为记录。

	> 数据项：一个数据元素由若干个数据项组成。

	> 数据对象：由性质相同的数据元素组成的集合，是数据的子集。

	> 数据结构：是相互之间存在一个或多个特定关系的数据元素的集合。

2. 逻辑结构和物理结构
	> 逻辑结构：指数据对象和数据元素之间的相互关系。

	- 集合结构、线性结构、树形结构、图形结构

	> 物理结构：指逻辑结构在计算机的存储形式。

	- 顺序存储结构，链式存储结构


3. 算法

	> 算法的基本特性：输入、输出、有穷性、确定性、可行性。
	> 
	> 算法设计的要求：正确性、可读性、健壮性、高效率和低存储量。
	> 
	> 算法的度量方法：事后统计、事前统计。


	1. 算法的时间复杂度 
	
		> 定义：在进行算法分析的时候，语句执行的次数T(n)是关于问题规模n的函数，进而分析T(
		> n)随n变化情况并确定T(n)的数量级。

		> 算法的时间复杂度，就是算法的时间量度。 记作T(n)=O(f(n))。它表示随着问题规模n的
		> 增大，算法执行时间的增长率和f(n)的增长率相同，称为算法的渐进时间复杂度，简称时
		> 间复杂度。其中f(n)是问题规模的某个函数。
		>
		>一般情况下，随着n的增大，T(n)增长最慢的算法为最优算法。
		>
		>O(1) 常数阶 O(n) 线性阶 O(n²) 平方阶
		![](https://i.imgur.com/QJcg2sT.png)

	2. 算法的空间复杂度
	
		> 算法的空间复杂度是通过计算算法所需的存储空间实现，算法空间的时间复杂度的计算公
		> 式记作：S(n)=O(f(n)),其中n是问题的规模，f(n)为语句关于n所占存储空间的函数。


4. 线性表

	1. 顺序存储结构线性表

		> 获取元素
		> 
		> 插入元素： 判断插入的位置是否小于1，判断插入的位置是否超过最大容量，判断插入的
		> 位置是否
		> 大于当前长度加1 
		> 
		> 删除元素： 判断当前删除的位置是否小于1，判断删除的位置是否查过当前长度。

		> 优点：快速读取每个元素，不用考虑每个元素之间的逻辑关系。
		> 
		> 缺点：插入和删除需要大量移动元素，难易确定存储空间的容量，容易造成存储空间的碎片。
	
	2. 链式存储结构线性表
		1. 头指针：我们把链表的第一个结点的存储地址叫做头指针。
		2. 头结点：单链表的第一个结点附设一个结点。
		

	3. 单链表和顺序结构的区别
		![](https://i.imgur.com/aipTt56.png)

	4. 静态链表
		> 使用数组实现，每个元素包括data和cur 两个元素，data为保存的值cur指向下个元素的数组下标。

	5. 循环链表
		> 最后一个元素的next指向头结点
	
	6. 双向链表 
		> 每个节点有两个指针，一个指向前一个节点，一个指向后面的节点。

![](https://i.imgur.com/pwDM1RI.png)


