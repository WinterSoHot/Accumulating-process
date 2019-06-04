#include<stdio.h>
/*常量*/
int main()
{
    /*整数常量*/
    int a = 212;
    int b = 212u; /*无符号整型 u 无符号 L长整型*/ 
    int c = 0xff; /*十六进制*/ 
    int d = 077;    /*八进制*/ 
    printf("%d,%d,%d,%d\n",a,b,c,d);
    printf("%f,%f,%f,%f",3.14,314159E-5L,210,1e2);
    return 0;
    
}