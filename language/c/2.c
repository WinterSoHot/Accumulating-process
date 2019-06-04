#include<stdio.h>
/*变量声明 通过使用extern关键字声明变量名而不定义它*/
extern int a,b;
extern int c;
extern float f;
int main()
{
    /*变量定义*/
    int a,b;
    int c;
    float f;
    
    /*初始化*/
    a = 10;
    b = 20;
    
    c = a + b;
    printf("value of c :%d \n",c);
    
    f = 70.0/3.0;
    printf("value of f :%f \n",f);
     
    /*c的两种类型的表达式 左值和右值 变量*/
    return 0;
}