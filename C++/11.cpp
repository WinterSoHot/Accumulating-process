#include <iostream>
#include <cstring>

#include <string>

using namespace std;

int main()
{
    char greeting[6] = {'H', 'e', 'l', 'l', 'o', '\0'};

    cout << "Greeting message: ";
    cout << greeting << endl;

    char str1[11] = "Hello";
    char str2[11] = "World";
    char str3[11];
    int len;

    // 复制 str1 到 str3
    strcpy(str3, str1);
    cout << "strcpy( str3, str1) : " << str3 << endl;

    // 连接 str1 和 str2
    strcat(str1, str2);
    cout << "strcat( str1, str2): " << str1 << endl;

    // 连接后，str1 的总长度
    len = strlen(str1);
    cout << "strlen(str1) : " << len << endl;

    string str4 = "Hello";
    string str5 = "World";
    string str6;
    int len;

    // 复制 str4 到 str6
    str6 = str4;
    cout << "str6 : " << str6 << endl;

    // 连接 str4 和 str5
    str6 = str4 + str5;
    cout << "str4 + str5 : " << str6 << endl;

    // 连接后，str6 的总长度
    len = str6.size();
    cout << "str6.size() :  " << len << endl;

    return 0;
}