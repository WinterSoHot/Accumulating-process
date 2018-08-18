#include <iostream>

using namespace std;

// 函数声明
void func(void);

static int count = 10; /* 全局变量 */

int counter;
extern void write_extern();

main(int argc, char const *argv[])
{
    auto f = 3.14;        //double
    auto s("hello");      //const char*
    auto z = new auto(9); // int*
                          // auto x1 = 5, x2 = 5.0, x3 = 'r'; //错误，必须是初始化为同一类型

    register int miles;

    while (count--)
    {
        func();
    }

    counter = 11;
    write_extern();
    return 0;
}

// 函数定义
void func(void)
{
    static int i = 5; // 局部静态变量
    i++;
    std::cout << "变量 i 为 " << i;
    std::cout << " , 变量 count 为 " << count << std::endl;
}

thread_local int x;  // 命名空间下的全局变量
class X
{
    static thread_local std::string s; // 类的static成员变量
};
static thread_local std::string X::s;  // X::s 是需要定义的
 
void foo()
{
    thread_local std::vector<int> v;  // 本地变量
}