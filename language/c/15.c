#include<stdio.h>

//指针
/*
指针是一个变量，其值为另一个变量的地址，即，内存位置的直接地址。就像其他变量或常量一样，您必须在使用指针存储其他变量地址之前，对其进行声明。指针变量声明的一般形式为：type *var-name;
 */
int main()
{
   int var1;
   int var2[10];
   
   printf("var1 的地址：%p\n",&var1);
   printf("var2 的地址：%p\n",&var2);
   
   int var=20;
   int *ip;
   ip = &var;
   
   printf("Address of var :%p\n",&var);
   printf("Address of ip :%p\n",ip);
   printf("Value of *ip :%d\n",*ip);
   
   //在变量声明的时候，如果没有确切的地址可以赋值，为指针变量赋一个 NULL //值是一个良好的编程习惯。赋为 NULL 值的指针被称为空指针。
   int *p = NULL;
   printf("ptr 的值是%p\n",p);
   
   return 0;
}