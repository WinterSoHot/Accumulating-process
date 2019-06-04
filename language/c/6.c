#include<stdio.h>
#include<time.h>
#define TIME 1000000000
int m,n = TIME ;/*全局变量*/
/*
*存储类定义 C 程序中变量/函数的范围（可见性）和生命周期
* auto auto 存储类是所有局部变量默认的存储类。 只能用在函数内
* register  用于定义存储在寄存器中而不是 RAM 中的局部变量
* static
static 存储类指示编译器在程序的生命周期内保持局部变量的存在，而不需要在每次它进入和离开作用域时进行创建和销毁。因此，使用 static 修饰局部变量可以在函数调用之间保持局部变量的值。

static 修饰符也可以应用于全局变量。当 static 修饰全局变量时，会使变量的作用域限制在声明它的文件内。

static 是全局变量的默认存储类，以下两个变量 (count 和 road) 都有一个 static 存储类。
* extern
*/
/*
auto 是局部变量的默认存储类, 限定变量只能在函数内部使用；

register 代表了寄存器变量，不在内存中使用；

static是全局变量的默认存储类,表示变量在程序生命周期内可见；

extern 表示全局变量，即对程序内所有文件可见，类似于Java中的public关键字；
 */
int main()
{
    auto int month;
    time_t start,stop;
    register int a,b=TIME; /*寄存器变量*/
    int x,y = TIME;    /*局部变量*/
    
    time(&start);
    for(a=0;a<b;a++);
    time(&stop);
    printf("寄存器变量用时：%ld \n",stop - start);
    
    time(&start);
    for(x=0;x<y;x++);
    time(&stop);
    printf("局部变量用时：%ld \n",stop - start);
    
    time(&start);
    for(m=0;m<n;m++);
    time(&stop);
    printf("全局变量用时：%ld \n",stop - start);
    
    return 0;
}