#include<stdio.h>

const int MAX = 5;

//指针数组 
int main()
{
    int *p[MAX];
    
    int attr[] = {1,2,3,4,5};
    
    int i;
    for(i=0;i<MAX;i++)
    {
        p[i] = &attr[i];
    }
    
    for(i=0;i<MAX;i++)
    {
        printf("指针数组 %x  =  %d\n",p[i],*p[i]);
    }
    
    char *name[] = {"abc","efg","hij"};
    
    for(i=0;i<3;i++)
    {
        printf("%x=%s\n",name[i],name[i]);
    }
    return 0;
}