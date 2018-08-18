#include <iostream>
using namespace std;

//typedef 声明，为已有的类型声明一个新的名字
typedef int feet;

//枚举类型
enum color {red,orange=5,blue} c;

main(int argc, char const *argv[])
{
    feet distance = 1;
    cout << distance << "\n";
    c = orange;
    cout << orange << endl;
    return 0;
}
