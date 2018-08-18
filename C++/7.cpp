#include <iostream>

using namespace std;

// 循环
main(int argc, char const *argv[])
{

    // 局部变量声明
    int a = 10;

// do 循环执行
LOOP:
    do
    {
        if (a == 15)
        {
            // 跳过迭代
            a = a + 1;
            goto LOOP;
        }
        cout << "a 的值：" << a << endl;
        a = a + 1;
    } while (a < 20);
    for (;;)
    {
        printf("This loop will run forever.\n");
    }

    return 0;
}
