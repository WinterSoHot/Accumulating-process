#include<stdio.h>  //引入文件  printf函数

/*
stdin   标准输入
stdout  标准输出
stderr  标准错误

*/

int main()
{
    float f;
    /*
    printf("请输入一个浮点数：");
    scanf("%f",&f);
    printf("Value of f is %f\n",f);
    */
    
    /*int c;
    printf("输入一个字符：");
    c = getchar();
    printf("\n你输入的为：");
    putchar(c);
    */
    
    /*char str[200];
    printf("输入一个字符串：");
    gets(str);
    printf("\n你输入的为：");
    puts(str);
    */
    
    char str[100];
   int i;
 
   printf( "Enter a value :");
   scanf("%s %d", str, &i);
 
   printf( "\nYou entered: %s %d ", str, i);
   printf("\n");
    
    return 0;
}