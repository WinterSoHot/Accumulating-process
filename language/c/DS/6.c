#include<stdio.h>
#include<stdlib.h>

//栈
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 1
typedef int Status;
#define MAXSIZE  100
typedef int SElemType;
typedef struct
{
    SElemType data[MAXSIZE];
    int top;
} SqStack;

Status Push(SqStack *S, SElemType e)
{
    if(S->top > MAXSIZE-1) //栈满
    {
        return ERROR;
    }
    S->top++; //栈顶加一
    S->data[S->top] = e;
    return OK;
}

Status Pop(SqStack *S,SElemType *e)
{
    if(S->top == -1)
    {
        return ERROR;
    }
    *e = S->data[S->top];
    S->top--;
    return OK;
}

int main()
{
    SqStack S;
    S.top = -1;
    printf("%d",Push(&S,1));
    
    return 0;
}