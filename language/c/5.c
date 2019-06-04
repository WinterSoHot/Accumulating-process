#include<stdio.h>
/*const ÉùÃ÷³£Á¿*/
int main()
{
    const int WIDTH = 5;
    const int LENGTH = 10;
    const char NEWLINE = '\n';
    int area;
    area = LENGTH * WIDTH;
    printf("value of area : %d",area);
    printf("%c",NEWLINE);
    return 0;
}