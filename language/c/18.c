#include<stdio.h>
#include<time.h>

void getSeconds(unsigned long *par);


int main()
{
    
    unsigned long sec;
    
    getSeconds(&sec);
    
    printf("Number of seconds:%ld\n",sec);
    
    return 0;
}

void getSeconds(unsigned long *par)
{
    *par = time(NULL);
    //time(par);
    return;
}

