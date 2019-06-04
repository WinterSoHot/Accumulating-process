#include<stdio.h>
#include<string.h>

// 共同体：同一时间只能存储一个变量
union Data
{
    int a;
    float b;
    char s[20];
};

int main()
{
    union Data data;
    data.a = 20;
    data.b = 12.0;
    strcpy(data.s,"Hello");
    
    printf("Data a : %d\n",data.a);
    printf("Data a : %.2f\n",data.b);
    printf("Data a : %s\n",data.s);
    
    return 0;
}