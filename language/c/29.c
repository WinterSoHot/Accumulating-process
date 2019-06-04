#include<stdio.h>

#undef FILE_SIZE  //取消已经定义
#define FILE_SIZE 42 //定义

#ifndef MESSAGE
 #define MESSAGE "You Wish!"
#endif

#ifdef DEBUG

#endif

int main()
{
    //使用预定义宏
    printf("FILE :%s\n",__FILE__);
    printf("DATE :%s\n",__DATE__);
    printf("TIME :%s\n",__TIME__);
    printf("LINE :%d\n",__LINE__);
    printf("ANSI :%d\n",__STDC__);
    
    return 0;
}