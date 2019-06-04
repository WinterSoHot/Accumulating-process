#include<stdio.h>
/*运算符*/

int main()
{
    /*算术运算符*/
    int a = 21;
    int b = 10;
    int c;
    
    c = a + b;
    printf("Line1 - c : %d\n",c);
    c = a - b;
    printf("Line2 - c : %d\n",c);
    c = a * b;
    printf("Line3 - c : %d\n",c);
    c = a / b;
    printf("Line4 - c : %d\n",c);
    c = a % b;
    printf("Line5 - c : %d\n",c);
    c = a++; //赋值后再加 1 ，c 为 21，a 为 2
    printf("Line6 - c : %d\n",c);
    c = a--;
    printf("Line7 - c : %d\n",c);
    
    /*前置 ++/-- 和 后置 ++/-- */
    int d = 10;
    int e;
    e = d++;
    printf("Line1 - d and e: %d\t%d\n",d,e);
    d = 10;
    e = d--;
    printf("Line2 - d and e: %d\t%d\n",d,e);
    d = 10;
    e = ++d;
    printf("Line3 - d and e: %d\t%d\n",d,e);
    d = 10;
    e = --d;
    printf("Line4 - d and e: %d\t%d\n",d,e);
    
    //关系运算符
    if (a == b)
    {
        printf("Line1 - a 等于 b\n");
    }
    else
    {
        printf("Line1 - a 不等于 b\n"); 
    }
    
    if (a < b)
    {
        printf("Line2 - a 小于 b\n");
    }
    else
    {
        printf("Line2 - a 不小于 b\n"); 
    }
    
    if ( a > b )
    {
      printf("Line 3 - a 大于 b\n" );
    }
    else
    {
      printf("Line 3 - a 不大于 b\n" );
    }
    
    //逻辑运算符
    if (a && b)
    {
        printf("Line1 - 条件为真\n");
    }
    if (a || b)
    {
        printf("Line2 - 条件为真\n");
    }
    a = 0;
    b = 10;
    if (a && b)
    {
        printf("Line3 - 条件为真\n");
    }
    else
    {
        printf("Line3 - 条件不为真\n");
    }
    
    if(!(a && b))
    {
        printf("Line4 - 条件为真\n");
    }
    
    //位运算符
    unsigned int _a = 60;
    unsigned int _b = 13;
    int _c = 0;
    _c = _a & _b;
    printf("Line1 - _c : %d\n",_c);
    _c = _a | _b;
    printf("Line2 - _c : %d\n",_c);
    _c = _a ^ _b;
    printf("Line3 - _c : %d\n",_c);
    _c = ~_a;
    printf("Line4 - _c : %d\n",_c);
    _c = _a << 2;
    printf("Line5 - _c : %d\n",_c);
    _c = _a >> 2;
    printf("Line6 - _c : %d\n",_c);

    //赋值运算符
       c =  a;
   printf("Line 1 - =  运算符实例，c 的值 = %d\n", c );
 
   c +=  a;
   printf("Line 2 - += 运算符实例，c 的值 = %d\n", c );
 
   c -=  a;
   printf("Line 3 - -= 运算符实例，c 的值 = %d\n", c );
 
   c *=  a;
   printf("Line 4 - *= 运算符实例，c 的值 = %d\n", c );
 
   c /=  a;
   printf("Line 5 - /= 运算符实例，c 的值 = %d\n", c );
 
   c  = 200;
   c %=  a;
   printf("Line 6 - %= 运算符实例，c 的值 = %d\n", c );
 
   c <<=  2;
   printf("Line 7 - <<= 运算符实例，c 的值 = %d\n", c );
 
   c >>=  2;
   printf("Line 8 - >>= 运算符实例，c 的值 = %d\n", c );
 
   c &=  2;
   printf("Line 9 - &= 运算符实例，c 的值 = %d\n", c );
 
   c ^=  2;
   printf("Line 10 - ^= 运算符实例，c 的值 = %d\n", c );
 
   c |=  2;
   printf("Line 11 - |= 运算符实例，c 的值 = %d\n", c );
}