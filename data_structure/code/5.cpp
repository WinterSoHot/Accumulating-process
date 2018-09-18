#include <iostream>

using namespace std;

class CQueue
{
  private:
    int *base; //存储空间基址
    int front;
    int rear;
    int queueSize;

  public:
    CQueue(int m);
    ~CQueue();
    void EnQueue(int e);
    int DeQueue();
    int GetHead();
    int GetLast();
    void QueueDisplay();
};

CQueue::CQueue(int m)
{
    queueSize = m;
    base = (int *)malloc(queueSize * sizeof(int));
    front = 0;
    rear = 0;
}

CQueue::~CQueue()
{
    delete[] base;
    front = 0;
    rear = 0;
    queueSize = 0;
}

void CQueue::EnQueue(int e)
{
    if ((rear + 1) % (queueSize) == front)
    {
        cout << "上溢，无法入队" << endl;
        return;
    }
    base[rear] = e;
    rear = (rear + 1) % queueSize;
}

int CQueue::DeQueue()
{
    int e;

    if (front == rear)
    {
        cout << "下溢，不能出队" << endl;
        return -1;
    }
    e = base[front];
    front = (front + 1) % queueSize;
    return e;
}

int CQueue::GetHead()
{
    int e;
    if (front == rear)
    {
        cout << "队空，无元素" << endl;
        return -1;
    }
    e = base[front];
    return e;
}

int CQueue::GetLast()
{
    int e;
    if (front == rear)
    {
        cout << "队空，无元素";
        return -1;
    }
    e = base[rear];
    return e;
}

void CQueue::QueueDisplay()
{
    if (front == rear)
    {
        cout << "队空，无元素" << endl;
        return;
    }
    for (int i = front % queueSize; i < rear; i++)
    {   
        
        int index = i;
        if (i > queueSize) {
            index = i % queueSize;
        }
        cout << "index: " << index << " value: " << base[index] << endl;
    }

    // for (int i = 0; i < (rear) % queueSize; i++)
    // {
    //     cout << "index: " << i << " value: " << base[i] << endl;
    // }
}

int main(int argc, char const *argv[])
{
    CQueue cq = CQueue(6);
    cq.EnQueue(1);
    cq.EnQueue(2);
    cq.EnQueue(3);
    cq.EnQueue(4);
    cq.EnQueue(5);

    cout << "入队后：" << endl;
    cq.QueueDisplay();

    cout << "第一个元素出队后：" << endl;
    cq.DeQueue();
    cq.QueueDisplay();
    return 0;
}
