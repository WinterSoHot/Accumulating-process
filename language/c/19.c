#include<stdio.h>
//字符串
#include<string.h>
int main()
{
    char greeting[6] = {'H', 'e', 'l', 'l', 'o', '\0'}; // = char greeting[] = "hello";
    printf("Greeting message:%s\n",greeting);
    
    printf("Length of greeting: %d\n",strlen(greeting));
}