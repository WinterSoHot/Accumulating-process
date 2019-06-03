#include <iostream>

using namespace std;

// 第一个命名空间
namespace firstNS
{
void func()
{
    cout << "Inside first_space" << endl;
}
} // namespace firstNS

// 第二个命名空间
namespace secondNS
{
void func()
{
    cout << "Inside second_space" << endl;
}
} // namespace secondNS
using namespace firstNS;

int main()
{

    // 调用第一个命名空间中的函数
    func();

    // 调用第二个命名空间中的函数
    secondNS::func();

    return 0;
}