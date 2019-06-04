#include<stdio.h>
#include<string.h>


int main()
{
    char s1[12] = "Hello";
    char s2[12] = "World";
    char s3[12];
    int len;
    //复制s1到s3
    strcpy(s3,s1);
    printf("strcpy(s3,s1) s3:%s\n",s3);
    
    //连接s1和s2
    strcat(s1,s2);
    printf("strcat(s1,s2) s1:%s\n",s1);
    
    //s1 的长度
    len = strlen(s1);
    printf("strlen(s1) len:%d\n",len);

    //strcmp(s1, s2);
    //如果 s1 和 s2 是相同的，则返回 0；如果 s1<s2 则返回小于 0；如果 //s1>s2 则返回大于 0。
    int dis = strcmp(s1,s2);
    printf("dis : %d\n",dis);
    
    return 0;
}