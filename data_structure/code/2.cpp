#include <iostream>

using namespace std;

typedef int ElemType;
struct Node{
    ElemType data;
    Node* next;
};

class LinkList
{
    private:
        Node *Head;
    public:
        LinkList();
        ~LinkList();
        void CreateList1(int n);
        void CreateList2(int n);
        void ListInsert(int i,int e);
        int ListDelete(int i);
        int GetElem(int i);
        int LocateElem(int e);
        int ListLength();
};

void LinkList::CreateList1(int n)
{
    //头插法创建线性表：不停的从头部插入
    Node *p,*s;
    p = Head;
    cout << "请依次输入" << n << "个数据元素值：" << endl;
    
    for(int i = 1; i <= n; i++)
    {
        s = new Node;
        cin >> s->data;
        s->next = p->next;
        p->next = s;
    }
    
}
int main(int argc, char const *argv[])
{
    return 0;
}
