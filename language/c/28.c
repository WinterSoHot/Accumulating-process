#include<stdio.h>

int main()
{
    FILE* fp = NULL;
    
    fp = fopen("test.txt","r");
    
    char buff[255];
    
    fscanf(fp,"%s",buff);
    printf("1: %s\n",buff);
    
    fgets(buff,255,fp);
    printf("2: %s\n",buff);
    
    fgets(buff,255,fp);
    printf("3: %s\n",buff);
    /*
    首先，fscanf() 方法只读取了 This，因为它在后边遇到了一个空格。其次，调用 fgets() 读取剩余的部分，直到行尾。最后，调用 fgets() 完整地读取第二行。
    */
    fclose(fp);
    return 0;
}