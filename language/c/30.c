#include<stdio.h>
//预处理器运算符
//C 预处理器提供了下列的运算符来帮助您创建宏：
//宏延续运算符（\）
//字符串常量化运算符（#）
//标记粘贴运算符（##）
//defined() 运算符


#define message_for(a,b) \
    printf(#a " and " #b ": We love you!\n")

    
#define tokenpaster(n) printf("token" #n " = %d",token##n)


#if !defined(MESSAGE)
    #define MESSAGE "Wish!"
#endif

//参数化的宏

#define square(x) ((x)*(x))
#define square2(x) (x*x)

#define MAX(x,y) ((x)>(y) ? (x) : (y))

int main()
{
    message_for(Carole,Debra);
    
    int token34 = 40;
    tokenpaster(34);
    
    printf("\n");
    printf();
    printf("\n");MESSAGE
    printf("square : %d\n",square(10));
    printf("square2 : %d\n",square2(5+5));
    printf("MAX : %d\n",MAX(10,20));
    return 0;
}