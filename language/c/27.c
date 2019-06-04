#include<stdio.h>
// 文件读写

int main()
{
    FILE *fp = NULL;
    fp = fopen("test.txt","w+");
    fprintf(fp,"This is testing for fprint");
    fputs("This is testng for fputs \n",fp);
    fclose(fp);
    
    return 0;
}