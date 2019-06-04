#include<stdio.h>
#include<string.h>
#include<errno.h>
//c 错误处理
int main()
{
    FILE *fp = NULL;
    int errnum;
    fp = fopen("unexits.txt","rb");
    
    if(fp == NULL)
    {
        /*
        errno、perror() 和 strerror()
        C 语言提供了 perror() 和 strerror() 函数来显示与 errno 相关的文本消息。perror()函数显示您传给它的字符串，后跟一个冒号、一个空格和当前 errno 值的文本表示形式。
        strerror() 函数，返回一个指针，指针指向当前 errno 值的文本表示形式。*/
        errnum = errno;
        fprintf(stderr,"错误号：%d\n",errno);
        perror("通过perror输出错误");
        fprintf(stderr,"打开文件错误：%s\n",strerror(errnum));
    }
    else
    {
        fclose(fp);
    }
    
    return 0;
}