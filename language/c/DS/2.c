#include<stdio.h>
#include<stdlib.h>
#define MAX_SIZE 20
typedef int ElemType;

#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 1

typedef int Status;

/* 线性表 顺序结构 */
typedef struct
{
    ElemType data[MAX_SIZE];
    int length;
} SqList;


//不需要修改原数组 直接传入
Status GetElem(SqList L,int i,ElemType *e)
{
    if(L.length == 0 || i<1 || i > L.length)
    {
        return ERROR;
    }
    
    *e = L.data[i-1];
    return OK;
}

/* 插入 需要修改 传入数组指针*/
Status ListInsert(SqList *L,int i,ElemType e)
{
    
    int k;
    // 长度达到最大 不能插入
    if(L->length == MAX_SIZE)
    {
        fprintf(stderr,"长度超过最大\n");
        return ERROR;
    }
    
    //插入的位置小于1或者大于当前长度加1
    if(i < 1 || i > L->length+1)
    {
        fprintf(stderr,"插入的位置小于1或者大于长度\n");
        return ERROR;
    }
    
    if(i <= L->length)
    {
        for(k = L->length-1;k>=i-1;k--)
        {   
            //从后往前遍历到要插入的位置 将值往后移动一位
            L->data[k+1] = L->data[k];
        }
    }
    
    L->data[i-1] = e;
    L->length++;
    return OK;
}

Status ListDelet(SqList *L,int i,ElemType *e)
{
    if(L->length == 0)
    {
        return ERROR;
    }
    
    if(i < 1 || i > L->length)
    {
        return 0;
    }
    
    *e = L->data[i-1];
    int k;
    if(i<L->length)
    {
        for(k=i;i<L->length;k++)
        {
            L->data[k-1] = L->data[k];
        }
    }
    L->length--;
    return OK;
}

int main()
{
    SqList L;
    L.length = 0;
    int status = ListInsert(&L,1,1);
}

