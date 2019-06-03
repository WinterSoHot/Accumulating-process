#include <iostream>
#include <csignal>
#include <unistd.h>

using namespace std;

void signalHandler(int signalNumber)
{
    cout << "中断信号(" << signalNumber << ") received." << endl;

    //清理并关闭
    //清理程序
    exit(signalNumber);
}

int main()
{
    int i = 0;
    //注册 SIGINT和处理程序
    signal(SIGINT, signalHandler);
    while (++i)
    {
        cout << "Going to sleep ..." << endl;

        if (i == 3)
        {
            raise(SIGINT);
        }

        sleep(1);
    }
    //按Ctrl + C 中断程序
    return 0;
}
