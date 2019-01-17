#include <iostream>

using namespace std;

int main()
{
    char str[] = "Hello C++";

    cout << "Value of str is : " << str << endl;

    char name[50];

    cout << "请输入您的名称： ";
    cin >> name;
    cout << "您的名称是： " << name << endl;

    char str2[] = "Unable to read....";
    //标准错误流（cerr）
    cerr << "Error message : " << str2 << endl;
    char str3[] = "Unable to read....";
    //标准日志流（clog）
    clog << "Error message : " << str3 << endl;
}