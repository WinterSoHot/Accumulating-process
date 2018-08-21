#include <iostream>

#define PI 3.1415926

#define DEBUG

#define MAX(a, b) ((a) > (b) ? a : b)

#define MKSTR(x) #x
#define concat(x, y) x##y

using namespace std;
int main()
{
    cout << "Value of PI : " << PI << endl; // 使用 gcc -E *.cpp > test.p 查看编译后的文件
    int i, j;
    i = 100;
    j = 30;
#ifdef DEBUG
    cerr << "Trace: Inside main function" << endl;
#endif

#if 0
   /* 这是注释部分 */
   cout << MKSTR(HELLO C++) << endl;
#endif

    cout << "较大的值为：" << MAX(i, j) << endl;

#ifdef DEBUG
    cerr << "Trace: Coming out of main function" << endl;
#endif

    cout << MKSTR(HELLO C++) << endl;
       int xy = 100;
    cout << concat(x, y); //cout << xy;

    cout << "Value of __LINE__ : " << __LINE__ << endl;
    cout << "Value of __FILE__ : " << __FILE__ << endl;
    cout << "Value of __DATE__ : " << __DATE__ << endl;
    cout << "Value of __TIME__ : " << __TIME__ << endl;
    
    return 0;
}