#include<stdio.h>
#include <limits.h>
#include<float.h>

int main()
{
    /*第一个c程序*/
	printf("Hello World!\n");
    int a = 10;
    
    printf("a 的字节长度%d\n",sizeof(a));
    printf("int 存储大小: %lu \n",sizeof(int));
    
    printf("float 存储的最大字节数：%lu \n",sizeof(float));
    printf("float 的最大值：%E\n",FLT_MAX);
    printf("float 的最小值：%E\n",FLT_MIN);
    printf("精度值: %d\n", FLT_DIG );

	return 0;
}


