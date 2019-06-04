#include<stdio.h>
//杂项运算符 ? sizeof & 三元

int main()
{
    int a = 4;
    short b;
    double c;
    int* ptr;
    
    printf("Line1 - a 的大小 = %lu\n",sizeof(a));
    printf("Line1 - b 的大小 = %lu\n",sizeof(b));
    printf("Line1 - c 的大小 = %lu\n",sizeof(c));
    
    ptr = &a;
    printf("a and *ptr : %d and %d \n",a,*ptr);
    
    b = (a == 1) ? 20 : 10;
    printf(" b : %d",b);
    
    return 0;
}