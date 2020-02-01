# Go语言学习

## 数据类型

1. 布尔型
布尔型的值只可以是常量 true 或者 false。一个简单的例子：var b bool = true。
2. 数字类型
整型 int 和浮点型 float32、float64，Go 语言支持整型和浮点型数字，并且支持复数，其中位的运算采用补码。
3. 字符串类型:
字符串就是一串固定长度的字符连接起来的字符序列。Go 的字符串是由单个字节连接起来的。Go 语言的字符串的字节使用 UTF-8 编码标识 Unicode 文本。
4. 派生类型:
包括：
    - 指针类型（Pointer）
    - 数组类型
    - 结构化类型(struct)
    - Channel 类型
    - 函数类型
    - 切片类型
    - 接口类型（interface）
    - Map 类型

## 格式化输出

[参考链接](https://blog.csdn.net/xiaoyida11/article/details/51554022)

## 变量 variance

```go
var a string = "string"
var vname vtype //自动赋初始值 
vname = value
var v_name = value // 自动判断类型
v_name := value // 声明新的变量，v_name 不是新变量编译出错
```

- 数值类型（包括complex64/128）为 0

- 布尔类型为 false

- 字符串为 ""（空字符串）

- 以下几种类型为 nil：
    
```go
var a *int
var a []int
var a map[string] int
var a chan int
var a func(string) int
var a error // error 是接口
```

```go
//类型相同多个变量, 非全局变量
var vname1, vname2, vname3 type
vname1, vname2, vname3 = v1, v2, v3

var vname1, vname2, vname3 = v1, v2, v3 // 和 python 很像,不需要显示声明类型，自动推断

vname1, vname2, vname3 := v1, v2, v3 // 出现在 := 左侧的变量不应该是已经被声明过的，否则会导致编译错误


// 这种因式分解关键字的写法一般用于声明全局变量
var (
    vname1 v_type1
    vname2 v_type2
)
```

值类型和引用类型

> 所有像 int、float、bool 和 string 这些基本类型都属于值类型，使用这些类型的变量直接指向存在内存中的值：

通过 `&i` 来获取变量 `i` 的内存地址

## 常量

```go
const identifier [type] = value
const c_name1, c_name2 = value1, value2

const (
    Unknown = 0
    Female = 1
    Male = 2
)
```

iota
> iota，特殊常量，可以认为是一个可以被编译器修改的常量。
> iota 在 const关键字出现时将被重置为 0(const 内部的第一行之前)，const 中每新增一行常量声明将使 iota 计数一次(iota 可理解为 const 语句块中的行索引)。

```go
const (
	a = iota   //0
	b          //1
	c          //2
	d = "ha"   //独立值，iota += 1
	e          //"ha"   iota += 1
	f = 100    //iota +=1
	g          //100  iota +=1
	h = iota   //7,恢复计数
	i          //8
    )
```

## 运算符

- 算术运算符
- 关系运算符
- 逻辑运算符
- 位运算符
- 赋值运算符
- 其他运算符

运算符优先级

|优先级|运算符|
|---|---|
|5|	* / % << >> & &^|
|4|	+ - \| \^ |
|3|	== != < <= > >=|
|2|	&&|
|1|	\|\| |

## 条件语句

```go
if 布尔表达式 {
   /* 在布尔表达式为 true 时执行 */
} else {
  /* 在布尔表达式为 false 时执行 */
}

switch var1 {
    case val1:
        ...
    case val2:
        ...
    default:
        ...
}
```


switch 语句还可以被用于 type-switch 来判断某个 interface 变量中实际存储的变量类型。


```go
switch x.(type){
    case type:
       statement(s);      
    case type:
       statement(s); 
    /* 你可以定义任意个数的case */
    default: /* 可选 */
       statement(s);
}
```

使用 fallthrough 会强制执行后面的 case 语句，fallthrough 不会判断下一条 case 的表达式结果是否为 true。

```go
switch {
    case false:
            fmt.Println("1、case 条件语句为 false")
            fallthrough
    case true:
            fmt.Println("2、case 条件语句为 true")
            fallthrough
    case false:
            fmt.Println("3、case 条件语句为 false")
            fallthrough
    case true:
            fmt.Println("4、case 条件语句为 true")
    case false:
            fmt.Println("5、case 条件语句为 false")
            fallthrough
    default:
            fmt.Println("6、默认 case")
    }
```

select 是 Go 中的一个控制结构，类似于用于通信的 switch 语句。每个 case 必须是一个通信操作，要么是发送要么是接收。

select 随机执行一个可运行的 case。如果没有 case 可运行，它将阻塞，直到有 case 可运行。一个默认的子句应该总是可运行的。

```go
select {
    case communication clause  :
       statement(s);      
    case communication clause  :
       statement(s);
    /* 你可以定义任意数量的 case */
    default : /* 可选 */
       statement(s);
}
```
- 每个 case 都必须是一个通信
- 所有 channel 表达式都会被求值
- 所有被发送的表达式都会被求值
- 如果任意某个通信可以进行，它就执行，其他被忽略。
- 如果有多个 case 都可以运行，Select 会随机公平地选出一个执行。其他不会执行。
- 否则：
    - 如果有 default 子句，则执行该语句。
    - 如果没有 default 子句，select 将阻塞，直到某个通信可以运行；Go 不会重新对 channel 或值进行求值。

## 循环语句

```go
for [condition |  ( init; condition; increment ) | Range]
{
   for [condition |  ( init; condition; increment ) | Range]
   {
      statement(s);
   }
   statement(s);
}
```

- break
- continue
- goto

## 函数

```go
func function_name( [parameter list] ) [return_types] {
   函数体
}
```
- func：函数由 func 开始声明
- function_name：函数名称，函数名和参数列表一起构成了函数签名。
- parameter list：参数列表，参数就像一个占位符，当函数被调用时，你可以将值传递给参数，这个值被称为实际参数。参数列表指定的是参数类型、顺序、及参数个数。参数是可选的，也就是说函数也可以不包含参数。
- return_types：返回类型，函数返回一列值。return_types 是该列值的数据类型。有些功能不需要返回值，这种情况下 return_types 不是必须的。
- 函数体：函数定义的代码集合。

函数参数

- 值传递
    > 默认
- 引用传递
    > 引用传递指针参数传递到函数内

```go
/* 定义交换值函数*/
func swap(x *int, y *int) {
   var temp int
   temp = *x    /* 保持 x 地址上的值 */
   *x = *y      /* 将 y 值赋给 x */
   *y = temp    /* 将 temp 值赋给 y */
}
```

函数用法

- 函数作为另外一个函数的实参：函数定义后可作为另外一个函数的实参数传入
- 闭包：闭包是匿名函数，可在动态编程中使用
- 方法：方法就是一个包含了接受者的函数
    > Go 语言中同时有函数和方法。一个方法就是一个包含了接受者的函数，接受者可以是命名类型或者结构体类型的一个值或者是一个指针。所有给定类型的方法属于该类型的方法集。

```go
func (variable_name variable_data_type) function_name() [return_type]{
   /* 函数体*/
}
```            

## 变量作用域

变量声明的位置
- 函数内定义的变量称为局部变量
    > 作用域只在函数体内，参数和返回值变量也是局部变量。
- 函数外定义的变量称为全局变量
    > 全局变量可以在整个包甚至外部包（被导出后）使用。
- 函数定义中的变量称为形式参数
    > 形式参数会作为函数的局部变量来使用。
                   
## 数组

声明数组

```go
var variable_name [SIZE] variable_type
```

初始化数组
```go
var balance = [5]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
var balance = [...]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
balance[4] = 50.0
```

多维数组

```go
var variable_name [SIZE1][SIZE2]...[SIZEN] variable_type
a = [3][4]int{  
 {0, 1, 2, 3} ,   /*  第一行索引为 0 */
 {4, 5, 6, 7} ,   /*  第二行索引为 1 */
 {8, 9, 10, 11},   /* 第三行索引为 2 */
}
```

数组参数

```go
void myFunction(param [10]int)
{
.
.
.
}

void myFunction(param []int)
{
.
.
.
}
```

## 指针

```go
var var_name *var-type
```

```go
func main() {
   var a int= 20   /* 声明实际变量 */
   var ip *int        /* 声明指针变量 */

   ip = &a  /* 指针变量的存储地址 */

   fmt.Printf("a 变量的地址是: %x\n", &a  )

   /* 指针变量的存储地址 */
   fmt.Printf("ip 变量储存的指针地址: %x\n", ip )

   /* 使用指针访问值 */
   fmt.Printf("*ip 变量的值: %d\n", *ip )
}
```

## 结构体

定义

```go
type struct_variable_type struct {
   member definition
   member definition
   ...
   member definition
}
```

声明

```go
variable_name := structure_variable_type {value1, value2...valuen}
或
variable_name := structure_variable_type { key1: value1, key2: value2..., keyn: valuen}
```

访问成员：`结构体.成员名`

结构体指针

```go
var struct_pointer *Books
```

## 切片 Slice

声明一个未指定大小的数组来定义切片
```go
var identifier []type
```

使用make()函数来创建切片:

```go
var slice1 []type = make([]type, len)

or

slice1 := make([]type, len)
```

```go
make([]T, length, capacity)
```
- length 初始长度
- capacity 容量

初始化切片

```go
s :=[] int {1,2,3 } 
s := arr[:] 
s := arr[startIndex:endIndex] 
s := arr[startIndex:] 
s := arr[:endIndex] 
s1 := s[startIndex:endIndex] 
s :=make([]int,len,cap) 
```

len() 和 cap() 函数

- 切片是可索引的，并且可以由 len() 方法获取长度。
- 切片提供了计算容量的方法 cap() 可以测量切片最长可以达到多少。

一个切片在未初始化之前默认为 nil，长度为 0，实例如下：

切片截取
设置下限及上限来设置截取切片 `[lower-bound:upper-bound]`

append() 和 copy() 函数

- 拷贝切片的 copy 方法
- 向切片追加新元素的 append 方法

```go
var numbers []int
/* 允许追加空切片 */
numbers = append(numbers, 0)
/* 向切片添加一个元素 */
numbers = append(numbers, 1)
/* 同时添加多个元素 */
numbers = append(numbers, 2,3,4)
/* 创建切片 numbers1 是之前切片的两倍容量*/
numbers1 := make([]int, len(numbers), (cap(numbers))*2)
/* 拷贝 numbers 的内容到 numbers1 */
copy(numbers1,numbers)
```

## Range 范围

> Go 语言中 range 关键字用于 for 循环中迭代数组(array)、切片(slice)、通道(channel)或集合(map)的元素。在数组和切片中它返回元素的索引和索引对应的值，在集合中返回 key-value 对。
  
## Map(集合)

> Map 是一种无序的键值对的集合。Map 最重要的一点是通过 key 来快速检索数据，key 类似于索引，指向数据的值。
> Map 是一种集合，所以我们可以像迭代数组和切片那样迭代它。不过，Map 是无序的，我们无法决定它的返回顺序，这是因为 Map 是使用 hash 表来实现的。
  
定义 Map

```go
/* 声明变量，默认 map 是 nil */
var map_variable map[key_data_type]value_data_type

/* 使用 make 函数 */
map_variable := make(map[key_data_type]value_data_type)
```

```go
for country := range countryCapitalMap {
   fmt.Println(country, "首都是", countryCapitalMap [country])
}

/*查看元素在集合中是否存在 */
capital, ok := countryCapitalMap [ "American" ] /*如果确定是真实的,则存在,否则不存在 */
if (ok) {
    fmt.Println("American 的首都是", capital)
} else {
    fmt.Println("American 的首都不存在")
}
```


delete() 函数

> delete() 函数用于删除集合的元素, 参数为 map 和其对应的 key。实例如下：
  
```go
delete(countryCapitalMap, "Key")
```
## 递归

## 类型转换

```go
type_name(expression)
```

```go
func main() {
   var sum int = 17
   var count int = 5
   var mean float32
   
   mean = float32(sum)/float32(count)
   fmt.Printf("mean 的值为: %f\n",mean)
}
```

## 接口

```go
/* 定义接口 */
type interface_name interface {
   method_name1 [return_type]
   method_name2 [return_type]
   method_name3 [return_type]
   ...
   method_namen [return_type]
}

/* 定义结构体 */
type struct_name struct {
   /* variables */
}

/* 实现接口方法 */
func (struct_name_variable struct_name) method_name1() [return_type] {
   /* 方法实现 */
}
...
func (struct_name_variable struct_name) method_namen() [return_type] {
   /* 方法实现*/
}
```

## 错误处理

Go 语言通过内置的错误接口提供了非常简单的错误处理机制。

error类型是一个接口类型，这是它的定义：

```go
type error interface {
    Error() string
}
```

```go
func Sqrt(f float64) (float64, error) {
    if f < 0 {
        return 0, errors.New("math: square root of negative number")
    }
    // 实现
}
```

## 并发

Go 语言支持并发，我们只需要通过 go 关键字来开启 goroutine 即可。

goroutine 是轻量级线程，goroutine 的调度是由 Golang 运行时进行管理的。

goroutine 语法格式：

```go
go 函数名( 参数列表 )
```

通道（channel）:是用来传递数据的一个数据结构。
> 通道可用于两个 goroutine 之间通过传递一个指定类型的值来同步运行和通讯。操作符 <- 用于指定通道的方向，发送或接收。如果未指定方向，则为双向通道。
 
```go
ch <- v    // 把 v 发送到通道 ch
v := <-ch  // 从 ch 接收数据
           // 并把值赋给 v
```

声明一个通道很简单，我们使用chan关键字即可，通道在使用前必须先创建：

```go
ch := make(chan int)
```
通道缓冲区
Channel buffer
通道可以设置缓冲区，通过 make 的第二个参数指定缓冲区大小：

```go
ch := make(chan int, 100)
```

带缓冲区的通道允许发送端的数据发送和接收端的数据获取处于异步状态，就是说发送端发送的数据可以放在缓冲区里面，可以等待接收端去获取数据，而不是立刻需要接收端去获取数据。

不过由于缓冲区的大小是有限的，所以还是必须有接收端来接收数据的，否则缓冲区一满，数据发送端就无法再发送数据了。

注意：如果通道不带缓冲，发送方会阻塞直到接收方从通道中接收了值。如果通道带缓冲，发送方则会阻塞直到发送的值被拷贝到缓冲区内；如果缓冲区已满，则意味着需要等待直到某个接收方获取到一个值。接收方在有值可以接收之前会一直阻塞。

Go 遍历通道与关闭通道

通过 `range` 关键字来实现遍历读取到的数据，类似于与数组或切片。格式如下：

```go
v, ok := <-ch
```

如果通道接收不到数据后 ok 就为 false，这时通道就可以使用 close() 函数来关闭。

```go
func fibonacci(n int, c chan int) {
        x, y := 0, 1
        for i := 0; i < n; i++ {
                c <- x
                x, y = y, x+y
        }
        close(c)
}

func main() {
        c := make(chan int, 10)
        go fibonacci(cap(c), c)
        // range 函数遍历每个从通道接收到的数据，因为 c 在发送完 10 个
        // 数据之后就关闭了通道，所以这里我们 range 函数在接收到 10 个数据
        // 之后就结束了。如果上面的 c 通道不关闭，那么 range 函数就不
        // 会结束，从而在接收第 11 个数据的时候就阻塞了。
        for i := range c {
                fmt.Println(i)
        }
}
```
