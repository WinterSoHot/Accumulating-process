#include<stdio.h>

int max(int num1,int num2);
int main()
{
    int num1,num2;//局部变量系统不会对它初始化,全局变量系统会自动初始化
    printf("输入两个数字:");
    scanf("%d,%d",&num1,&num2);
    printf("较大的数为：%d",max(num1,num2));
    return 0;
}

int max(int num1,int num2)
{
    int max = num1;
    if (num2 > max)
    {
        max = num2;
    }
    return max;
}