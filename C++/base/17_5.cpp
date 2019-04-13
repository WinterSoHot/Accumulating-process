#include <iostream>

using namespace std;
//内联函数 比普通函数执行更加有效率
//内联函数必须声明和定义同时写
//如：类中直接在声明和定义一块写默认就是内联函数
inline int Max(int x, int y)
{
    return (x > y) ? x : y;
}

// 程序的主函数
int main()
{

    cout << "Max (20,10): " << Max(20, 10) << endl;
    cout << "Max (0,200): " << Max(0, 200) << endl;
    cout << "Max (100,1010): " << Max(100, 1010) << endl;
    return 0;
}