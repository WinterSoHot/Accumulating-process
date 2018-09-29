#include <iostream>

using namespace std;

struct Node
{
    int data;
    Node *next;
};

class LinkQueue
{
  private:
    Node *front;
    Node *rear;

  public:
    LinkQueue();
    ~LinkQueue();
    void EnQueue(int e);
    int DeQueue();
    int GetHead();
    int GetLast();
    void QueueDisplay();
};

LinkQueue::LinkQueue()
{
    front = new Node;
    front->next = NULL;
    rear = front;
}

LinkQueue::~LinkQueue()
{
    Node *p;

    while (front != NULL)
    {
        p = front;
        front = front->next;
        delete p;
    }
}

void LinkQueue::EnQueue(int e)
{
    cout << e << "进入队列！！" << endl;
    Node *s = new Node;
    s->data = e;
    s->next = rear->next;
    rear->next = s;
    rear = s;

    if (front->next == NULL)
    {
        front->next = s;
    }
}

int LinkQueue::DeQueue()
{
    int e;
    Node *p;

    if (rear == front)
    {
        cout << "下溢";
        return -1;
    }
    p = front->next;
    e = p->data;
    front->next = p->next;
    if (p->next == NULL)
    {
        rear = front;
    }
    delete p;
    return e;
}

int LinkQueue::GetHead()
{
    int e;
    Node *p;
    if (front == rear)
    {
        cout << "下溢";
        return -1;
    }
    p = front->next;

    if (p == NULL)
    {
        cout << "当前队列为空" << endl;
    }
    e = p->data;
    return e;
}

int LinkQueue::GetLast()
{
    int e;
    if (front == rear)
    {
        cout << "下溢";
        return -1;
    }
    e = rear->data;
    return e;
}

void LinkQueue::QueueDisplay()
{
    if (front == rear)
    {
        cout << "队列为空" << endl;
        return;
    }
    Node *p = NULL;
    do
    {

        if (p == NULL)
        {
            p = front->next;
        }
        else
        {
            p = p->next;
        }
        cout << p->data << " ";
    } while ((p != rear));
}

int main(int argc, char const *argv[])
{
    LinkQueue lq = LinkQueue();
    lq.EnQueue(1);
    lq.EnQueue(2);
    lq.EnQueue(3);
    lq.EnQueue(4);
    lq.EnQueue(5);
    lq.EnQueue(7);
    lq.EnQueue(8);

    int e = lq.DeQueue();
    cout << e << endl;
    e = lq.DeQueue();
    cout << e << endl;
    e = lq.GetHead();
    cout << e << endl;
    e = lq.GetLast();
    cout << e << endl;
    lq.QueueDisplay();
    return 0;
}
