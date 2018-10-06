#include <iostream>

using namespace std;

#define RADIX 10
typedef int ArrType[RADIX];
ArrType f, e;

struct SLCell
{
    int *keys; //关键字
    int next;
};

struct SLList
{
    SLCell *SList;
    int keynum; //记录当前关键字个数
    int recnum; //记录当前静态链表的长度
};

//分配

void Distrbute(SLCell *r, int i, ArrType &f, ArrType &e)
{
    int j;
    for (j = 0; j < RADIX; j++)
        f[j] = 0;

    for (int a = r[0].next; a; a = r[a].next)
    {
        j = r[a].keys[i];

        if (!f[j])
            f[j] = a;

        else
            r[e[j]].next = a;
        e[j] = a;
    }
}
//收集

void Collect(SLCell *r, int i, ArrType &f, ArrType &e)
{
    int j;

    for (j = 0; !f[j]; j++)
        ;             //找到第一个非空子集
    r[0].next = f[j]; //r[0].next 指向第一个非空子表中第一个结点
    int t = e[j];

    while (j < RADIX)
    {
        for (j++; j < RADIX - 1 && !f[j]; j++)
            ; //找下一个非空子集
        if (f[j])
        {
            r[t].next = f[j];
            t = e[j]; //链接两个非空子表
        }
    }
    r[t].next = 0; //t指向最后一个非空子表的左后一个结点
}

//主程序
void RadixSort(SLList &SL)
{

    for (int i = SL.keynum; i >= 1; i--) //按最高位优先依次对各关键字进行分配收集
    {
        Distrbute(SL.SList, i, f, e); //第i趟分散
        Collect(SL.SList, i, f, e);   //第i趟收集
    }
}
