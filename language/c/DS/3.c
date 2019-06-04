#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 1

typedef int Status;
typedef int ElemType;
typedef struct Node
{
    ElemType data;
    struct Node *next;
} Node;
typedef struct Node *LinkList;

Status GetElem(LinkList L,int i,ElemType *e)
{
    int j;
    LinkList p;
    p = L->next;
    j=1;
    while(p && j<i)
    {
        p=p->next;
        ++j;
    }
    
    if(!p || j>i)
    {
        fprintf(stderr,"ERROR\n");
        return ERROR;
    }
    
    *e = p->data;
    return OK;
}

Status ListInsert(LinkList *L,int i,ElemType e)
{
    int j;
    LinkList p,s;
    p = *L;
    j = 1;
    
    while(p && j<i)
    {
        p = p->next;
        ++j;
    }
    
    if(p || j >i)
    {
        return ERROR;
    }
    
    s = (LinkList)malloc(sizeof(Node));
    s->data = e;
    s->next = p->next;
    p->next =s;
    
    return OK;
}

Status ListDelete(LinkList *L,int i,ElemType *e)
{
    int j;
    LinkList p,q;
    p = *L;
    j=1;
    
    while(p || j<i)
    {
        p = p->next;
        ++j;
    }
    if(!(p->next) || j > i)
    {
        return ERROR;
    }
    q = p->next;
    p->next = q->next;
    *e = q->data;
    free(q);
    return OK;
}


//头插法 每次新结点都放到最前面
void CreateListHead(LinkList *L,int n)
{
    LinkList p;
    int i;
    srand(time(0));
    (*L)->next = NULL;
    for(i=0;i<n;i++)
    {
        p = (LinkList)malloc(sizeof(Node));
        p->data = rand() % 100 + 1;
        p->next = (*L)->next;
        (*L)->next = p;
    }
}

//尾插法
void CreateListTailTail(LinkList *L,int n)
{
    LinkList p,r;
    int i;
    srand(time(0));
    
    *L = (LinkList)malloc(sizeof(Node));
    r = *L;
    for(i=0;i<n;i++)
    {
        p = (LinkList)malloc(sizeof(Node));
        p->data = rand() % 100 + 1;
        r->next = p; 
        r = p;
    }
    r->next = NULL;
}

Status ClearList(LinkList *L)
{
    LinkList p,q;
    p = (*L)->next;
    
    while(p)
    {
        q = p->next;
        free(p);
        p = q;
    }
    (*L)->next = NULL;
    return OK;
}
int main()
{
    LinkList L = (LinkList)malloc(sizeof(Node));
    CreateListHead(&L,10);
    ElemType e;
    GetElem(L,5,&e);
    printf("%d\n",e);
    
    LinkList h = (LinkList)malloc(sizeof(Node));
    LinkList l1 = (LinkList)malloc(sizeof(Node));
    LinkList l2 = (LinkList)malloc(sizeof(Node));
    h->next = l1;
    l1->next = l2;
    l1->data = 1;
    l2->data = 2;
    GetElem(h,1,&e);
    printf("%d\n",e);
    return 0;
}
