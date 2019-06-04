#include<stdio.h>

struct
{
   unsigned int widthVaildated;
   unsigned int heightVaildated;
} status1;
struct
{
   unsigned int widthVaildated : 1;
   unsigned int heightVaildated : 1;
} status2;

int main()
{
    printf("Memory size occupied by status1 : %d\n",sizeof(status1));
    printf("Memory size occupied by status2 : %d\n",sizeof(status2));
    
    return 0;
}

