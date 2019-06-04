#include<stdio.h>
//指针运算

#define MAX 5

int main()
{
    int var[MAX] = {1,2,3,4,5};
    int i ;
    
    int *ptr = var;
    
    for(i=0;i<MAX;i++)
    {
        printf("存储地址var[%d] = %x\n",i,ptr);
        printf("存储值  var[%d] = %d\n\n",i,*ptr);
        
        ptr++; //因为ptr是整型指针，每次++ 都移动4个字节，如果是字符指针，
        //++ 移动一个字节
    }
    
    
    ptr = &var[MAX - 1];
    
    for(i = MAX - 1;i>=0;i--)
    {
        printf("存储地址var[%d] = %x\n",i,ptr);
        printf("存储值  var[%d] = %d\n\n",i,*ptr);
        ptr--;
    }
    
    //指针的比较 如果指针指向两个相互关联的变量 如同一个数组的元素
    
    ptr = var;
    
    do{
        printf("存储地址%x\n",ptr);
        printf("存储值  %d\n\n",*ptr);
        ptr++;
    }while(ptr <= &var[MAX-1]);
    
    return 0;
}