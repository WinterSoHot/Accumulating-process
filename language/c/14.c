#include<stdio.h>
#include<stdlib.h>
#include<time.h>

double getAverage(int arr[],int size);
int * getRandom();
int main()
{
    int balance[5] = {100,200,300};
    double avg;
    avg = getAverage(balance,5);
    printf("平均值：%f\n",avg);
    
    int *p;
    int i;
    p = getRandom();
    for(i=0 ; i <10;i++)
    {
        printf("*(p+%d) = %d\n",i,*(p+i));
    }
    return 0;
}

double getAverage(int *attr,int size)
{
    int i;
    double sum = 0.0;
    double avg;
    
    for(i=0;i<size;i++)
    {
        sum+=*(attr+i);
    }
    
    avg = sum / size;
    return avg;
}

int * getRandom()
{
    static int r[10];
    int i;
    
    //设置种子
    srand((unsigned)time(NULL));
    for( i=0 ; i < 10 ;++i )
    {
        r[i] = rand();
        printf("r[%d] = %d\n",i,r[i]);
    }
    return r;
}