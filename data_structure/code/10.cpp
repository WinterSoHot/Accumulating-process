#include <iostream>
#include "ds"

using namespace std;
using namespace dx;

typedef int ElemType;

struct BiTNode
{
    ElemType data;
    BiTNode *lchild, *rchild;
};

class BinaryTree
{
  private:
    BiTNode *bt;
    int RCreate(BiTNode *p, int k, int end);
    int PreTraverse(BiTNode *p);
    int InTraverse(BiTNode *p);
    int PostTraverse(BiTNode *p);

  public:
    BinaryTree();
    ~BinaryTree();
    void CreateBiTree(int end);
    void PreOrderTraverse();
    void InOrderTraverse();
    void PostOrderTraverse();
    BiTNode *GetRoot();
    void BiTreeDisplay(BiTNode *bt, int level = 1);
};

BinaryTree::BinaryTree()
{
    bt = NULL;
}

BinaryTree::~BinaryTree()
{
    delete[] bt;
}

int BinaryTree::PreTraverse(BiTNode *p)
{

    if (p != NULL)
    {
        cout << p->data << ' ';
        PreTraverse(p->lchild);
        PreTraverse(p->rchild);
    }
    return 0;
}
int BinaryTree::InTraverse(BiTNode *p)
{

    if (p != NULL)
    {
        InTraverse(p->lchild);
        cout << p->data << ' ';
        InTraverse(p->rchild);
    }
    return 0;
}
int BinaryTree::PostTraverse(BiTNode *p)
{

    if (p != NULL)
    {
        PostTraverse(p->lchild);
        PostTraverse(p->rchild);
        cout << p->data << ' ';
    }
    return 0;
}

void BinaryTree::PreOrderTraverse()
{
    cout << "先序非递归遍历二叉树：";
    BiTNode *p = bt;
    SqStack s(20);

    while (p || !s.StackEmpty())
    {

        if (p)
        {
            cout << p->data << ' ';
            s.Push(p->data);
            p = p->lchild;
        }

        else
        {
            s.Pop();
            p = p->rchild;
        }
    }
    cout << endl;
};

typedef struct BiThrNode
{
    ElemType data;
    BiThrNode *lchild, *rchild;
    int LTag, RTag;
} BiThrNode, *BiThrTree;

class ThreadBiTree
{
  private:
    BiThrNode *bt;
    BiThrNode *pre;
    void RCreate(BiThrNode *p, int flag, int end);

  public:
    BiThrNode *Thrt;
    ThreadBiTree();
    ~ThreadBiTree();
    void CreateBiTree(int end);
    BiThrNode *GetRoot();
    int InOrderThreading(BiThrTree &Thrt, BiThrTree T);
    void InThreading(BiThrTree p);
    int InOrderTraverse_Thr(BiThrTree T);
    void BiTreeDisplay(BiThrNode *bt, int level = 1);
};

ThreadBiTree::ThreadBiTree()
{
    bt = NULL;
    Thrt = new BiThrNode();
}

ThreadBiTree::~ThreadBiTree()
{
}
void ThreadBiTree::InThreading(BiThrTree p)
{

    if (p)
    {
        InThreading(p->lchild);

        if (!p->lchild)
        {
            p->LTag = 1;
            p->lchild = pre;
        }

        if (!pre->rchild)
        {
            pre->RTag = 1;
            pre->rchild = p;
        }
        pre = p;
        InThreading(p->rchild);
    }
}

main(int argc, char const *argv[])
{
    /* code */
    return 0;
}
