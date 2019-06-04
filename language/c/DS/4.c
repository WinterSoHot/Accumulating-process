#include<stdio.h> 
#include<stdlib.h>
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 1
#define MAX_SIZE 1000

typedef int Status;
typedef int ElemType;

//静态链表


typedef struct
{
        ElemType data;
        int curl; // 0 表示没有下标
} Component,StaticLinkList[MAX_SIZE];

Status InitList(StaticLinkList space)
{
    int i;
    for(i=0;i<MAX_SIZE-1;i++)
    {
        space[i].curl = i+1;
    }
    space[MAX_SIZE-1].curl = 0;
    return OK;
}

//获得空闲分量的下标
int Malloc_SLL(StaticLinkList space)
{
    int i = space[0].curl;
    if(i)
    {
        space[0].curl = space[i].curl;
    }
    return i;
}

int ListLength(StaticLinkList L)
{
    int j = 0;
    int i = L[MAX_SIZE-1].curl;
    while(i)
    {
        i = L[i].curl;
        j++;
    }
    return j;
}

Status ListInsert(StaticLinkList L,int i,ElemType e)
{
    int j,k,l;
    k = MAX_SIZE-1; //k是最后一个元素的下标
    if(i<1 || i > ListLength(L)+1)
    {
         return ERROR;
    }
    j = Malloc_SLL(L); //获得空闲分量的下标
    if(j)
    {
        L[j].data = e;/* 将数值赋给此分量的data */
        for(l = 1;l<=i-1;l++)
        {
            k = L[k].curl;
        }
        L[j].curl = L[k].curl;
        L[k].curl = j;
        return OK;
    }
    return ERROR;
}



int main()
{
    StaticLinkList space;
    InitList(space);
    printf("%d",sizeof(space));
    return 0;
}




    

