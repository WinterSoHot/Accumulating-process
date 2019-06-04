#include<stdio.h>
#include<stdlib.h>

int main()
{
   int dividend = 20;
   int divisor = 0;
   int quotient;
   
   if(divisor == 0)
   {
       fprintf(stderr,"除数为0退出程序...\n");
       exit(EXIT_FAILURE);
   }
   quotient = dividend / divisor;
   fprintf(stderr, "quotient 变量的值为: %d\n", quotient );
   exit(EXIT_SUCCESS);
}