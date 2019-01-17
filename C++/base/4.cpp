#include <iostream>

using namespace std;
// 变量声明
extern int a, b;
extern int c;
extern float f;

// 全局变量声明
int g;

// 函数声明
int func();

// 常量定义
#define LENGTH 10
#define WIDTH 5
#define NEWLINE '\n'

main(int argc, char const *argv[])
{

    // （局部）变量定义
    //不带初始化的定义：带有静态存储持续时间的变量会被隐式初始化为 NULL（所有字节的值都是 0），其他所有变量的初始值是未定义的。
    int a, b;
    int c;
    float f;

    // 实际初始化
    a = 10;
    b = 20;
    c = a + b;

    cout << c << endl;

    f = 70.0 / 3.0;
    cout << f << endl;
    // 函数调用
    int i = func();

    g = a + c;
    cout << g << endl;

    int area = WIDTH * LENGTH;
    cout << area;
    cout << NEWLINE;

    const int LENGTH2 = 10;
    const int WIDTH2 = 5;
    const char NEWLINE2 = '\n';
    return 0;
}

// 函数定义
int func()
{
    return 0;
}
