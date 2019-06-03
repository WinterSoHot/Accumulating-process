#include <iostream>
#include <pthread.h>
#include <cstdlib>

using namespace std;

#define NUM_THREADS 5

typedef struct thread_data
{
    int threadId;
    char *message;
} thread_data;

void *PrintHello(void *threadId)
{
    thread_data *tData = (thread_data *)threadId;
    cout << "线程 ID ," << tData->threadId;
    cout << " Message ," << tData->message << endl;
    pthread_exit(NULL);
}

int main()
{
    pthread_t threads[NUM_THREADS];

    thread_data tds[NUM_THREADS];
    int rc;
    int i;
    for (i = 0; i < NUM_THREADS; i++)
    {
        cout << "main() : 创建线程, " << i << endl;
        tds[i].threadId = i; //先保存i的值
        tds[i].message = (char *)"This is message";
        // 传入的时候必须强制转换为void* 类型，即无类型指针
        rc = pthread_create(&threads[i], NULL,
                            PrintHello, (void *)&(tds[i]));
        if (rc)
        {
            cout << "Error:无法创建线程," << rc << endl;
            exit(-1);
        }
    }
    pthread_exit(NULL);
}