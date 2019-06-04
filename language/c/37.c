#include<stdio.h>
//命令行参数

int main(int argc,char *argv[])
{
    //argv[0] 存储程序的名称
    //argv[1] 第一个参数
    if (argc == 2)
    {
        printf("%s\n",argv[1]);
    }
    return 0;
}