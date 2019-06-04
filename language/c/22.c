#include<stdio.h>
//位域 一种特殊的数据结构
/*所谓"位域"是把一个字节中的二进位划分为几个不同的区域，并说明每个区域的位数。每个域有一个域名，允许在程序中按域名进行操作。这样就可以把几个不同的对象用一个字节的二进制位域来表示。*/


/*
注意点：
1、 一个位域必须存储在一个字节，不能跨越两个字节
2、 位域的长度不能大于一个字节
3、 无名位域不可以使用的
*/
int main()
{
    struct bs
    {
        unsigned a:1;
        unsigned b:3;
        unsigned c:4;
    } bit,*pbit;
    
    bit.a = 1; /*不能超过位域允许的范围*/
    bit.b = 7; /*不能超过位域允许的范围*/
    bit.c = 15; /*不能超过位域允许的范围*/
    
    printf("%d %d %d \n",bit.a,bit.b,bit.c);
    
    pbit = &bit;
    
    pbit->a = 0;
    pbit->b&= 3;
    pbit->c|= 1;
    
    printf("%d,%d,%d\n",pbit->a,pbit->b,pbit->c); 
}
