#include <iostream>

using namespace std;

//异常处理

double division(int a, int b)
{

    if (b == 0)
    {
        throw "Division by zero condition!";
    }
    return a / b;
}

struct MyException : public exception
{
    /*
     * const throw() 不是函数，这个东西叫异常规格说明，
     * 表示 what 函数可以抛出异常的类型，
     * 类型说明放到 () 里，这里面没有类型，
     * 就是声明这个函数不抛出异常，
     * 通常函数不写后面的就表示函数可以抛出任何类型的异常。
     */
    const char *what() const throw()
    {
        return "C++ Exception";
    }
};

int main()
{
    int x = 50;
    int y = 0;
    double z = 0;

    try
    {
        z = division(x, y);
    }
    catch (const char *msg)
    {
        cerr << msg << endl;
    }

    try
    {
        throw MyException();
    }
    catch (MyException &e)
    {
        cout << "MyException caught" << endl;
        cout << e.what() << endl;
    }
    catch (exception &e)
    {
        //其他的错误
    }

    return 0;
}