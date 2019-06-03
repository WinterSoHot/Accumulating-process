#include <iostream>
#include <cmath>

#include <ctime>
#include <cstdlib>

using namespace std;

int main()
{
    // 数字定义
    short s;
    int i;
    long l;
    float f;
    double d;

    // 数字赋值
    s = 10;
    i = 1000;
    l = 1000000;
    f = 230.47;
    d = 30949.374;

    // 数字输出
    cout << "short  s :" << s << endl;
    cout << "int    i :" << i << endl;
    cout << "long   l :" << l << endl;
    cout << "float  f :" << f << endl;
    cout << "double d :" << d << endl;

    // 数学运算
    cout << "sin(d) :" << sin(d) << endl;
    cout << "abs(i)  :" << abs(i) << endl;
    cout << "floor(d) :" << floor(d) << endl;
    cout << "sqrt(f) :" << sqrt(f) << endl;
    cout << "pow( d, 2) :" << pow(d, 2) << endl;

    int i, j;

    // 设置种子
    srand((unsigned)time(NULL));

    /* 生成 10 个随机数 */
    for (i = 0; i < 10; i++)
    {
        // 生成实际的随机数
        j = rand();
        cout << "随机数： " << j << endl;
    }

    return 0;
}