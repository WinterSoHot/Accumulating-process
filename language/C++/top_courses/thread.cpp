#include <iostream>
//必须的文件
#include <pthread.h>


using namespace std;

// 线程的运行函数
void *say_hello(void *args)
{
    cout << "Hello Thread!" << endl;
    return 0;
}

#define NUM_THREADS 5

int main(int argc, char const *argv[])
{
    //定义线程的id变量，多个变量使用数组
    pthread_t tids[NUM_THREADS];

    for (int i = 0; i < NUM_THREADS; i++)
    {
        // 参数依次是：创建线程的id，线程参数，调用的函数，传入的函数参数
        int ret = pthread_create(&tids[i], NULL, say_hello, NULL);

        if (ret != 0)
        {
            cout << "pthread create error_code=" << ret << endl;
        }
    }
    //等各个线程退出后，进程才结束，否则进程强制结束了，线程可能还没反应过来；
    pthread_exit(NULL);

    // 使用 lpthread 编译程序。
    // g++ thread.cpp -lpthread -o thread.o 
    return 0;
}
