#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

typedef struct SqList
{
    int *r;
    int length;
} SqList;

void BInsertSort(SqList &L)
{
    int high, low, m;
    for (int i = 2; i <= L.length; i++)
    {
        L.r[0] = L.r[i];
        low = 1;
        high = i - 1;
        while (low <= high)
        {
            m = (low + high) / 2;
            if (L.r[0] <= L.r[m])
                high = m - 1;
            else
                low = m + 1;
        }
        for (int j = i - 1; j > high + 1; j--)
            L.r[j + 1] = L.r[j];
        L.r[high + 1] = L.r[0];
    }
}

class QSort
{
  public:
    QSort(/* args */){};
    void CreateSqList(SqList &L);
    void SqListDisplay(SqList L);
    void SInsertSort(SqList &L); //直接插入排序
    void BubbleSort(SqList &L);  //冒泡排序
    //快速排序
    int Partition(SqList &L, int low, int high);   //对序列的一次划分
    void QuickSort1(SqList &L, int low, int high); //按分区对子程序进行调用
    void SSelectionSort(SqList &L);
};

void QSort::CreateSqList(SqList &L)
{
    srand((int)time(0));
    int length = rand() % 10;
    cout << "长度为：" << length << endl;
    L.length = length;
    L.r = (int *)realloc(L.r, length * sizeof(int));
    for (int i = 0; i < length; i++)
    {
        int item = rand() % 100;
        L.r[i] = item;
    }
}
void QSort::SqListDisplay(SqList L)
{
    for (int i = 0; i < L.length; i++)
    {
        cout << L.r[i] << "\t";
    }
    cout << endl;
}
//直接插入排序:第一个作为暂存待插入的元素
void QSort::SInsertSort(SqList &L)
{
    for (int i = 2; i <= L.length; i++)
    {
        if (L.r[i] <= L.r[i - 1])
        {
            L.r[0] = L.r[i];
            L.r[i] = L.r[i - 1];
            int j;
            for (j = i - 2; L.r[0] <= L.r[j]; j--)
                L.r[j + 1] = L.r[j];
            L.r[j + 1] = L.r[0];
        }
    }
}

//冒泡排序
void QSort::BubbleSort(SqList &L)
{

    for (int i = 1; i < L.length; i++)
    {

        for (int j = 0; j < L.length - i; j++)
        {

            if (L.r[j] > L.r[j + 1])
            {
                int t = L.r[j];
                L.r[j] = L.r[j + 1];
                L.r[j + 1] = t;
            }
        }
    }
}

int QSort::Partition(SqList &L, int low, int high) //对序列的一次划分
{
    int pivotkey;
    L.r[0] = L.r[low];   //用子表的第一个记录作枢轴记录
    pivotkey = L.r[low]; //关键字

    while (low < high) //从表的两边交替向中间扫描
    {

        while (low < high && L.r[high] >= pivotkey)
        {
            --high;
        }
        L.r[low] = L.r[high]; //将比枢轴小的记录移至低端

        while (low < high && L.r[low] <= pivotkey)
        {
            ++low;
        }
        L.r[high] = L.r[low]; //将比枢轴大的记录移至高端
    }
    L.r[low] = L.r[0]; //枢轴记录到位
    return low;        //返回枢轴位置
}
void QSort::QuickSort1(SqList &L, int low, int high) //按分区对子程序进行调用
{
    int mid;

    if (low < high)
    {
        mid = Partition(L, low, high);
        QuickSort1(L, low, mid - 1);
        QuickSort1(L, mid + 1, high);
    }
}
//简单选择排序
void QSort::SSelectionSort(SqList &L)
{
    int t, j;

    for (int x = 0; x <= L.length - 1; x++)
    {

        j = x;
        for (int y = x; y <= L.length - 1; y++)
        {

            if (L.r[y] < L.r[j])
            {
                j = y;
            }
        }

        if (x != j)
        {
            t = L.r[x];
            L.r[x] = L.r[j];
            L.r[j] = t;
        }
    }
}

//堆的建立
void HeapAdjust(SqList &L, int s, int m) //对顺序表做查找，从值最小的孩子结点向下筛选，找到最小值
{
    int rc = L.r[s];

    for (int j = 2 * s; j <= m; j *= 2)
    {

        if (j < m && L.r[j] >= L.r[j + 1])
            j++;

        if (rc < L.r[j])
            break;
        L.r[s] = L.r[j];
        s = j;
    }
    L.r[s] = rc;
}

//完整的堆排序
void HeapSort(SqList &L) //对顺序表L进行堆排序
{
    int value;
    int i;
    for (i = L.length / 2; i > 0; i--) //把L.r[1...L.length]调整为小顶堆
        HeapAdjust(L, i, L.length);

    for (i = L.length; i > 1; i--)
    {
        value = L.r[1];
        L.r[1] = L.r[i];
        L.r[i] = value;
        HeapAdjust(L, 1, i - 1);
    }
}

//一趟归并排序
void Merge(int *SR, int *TR, int i, int m, int n)
{
    int j, k;
    for (j = m + 1, k = i; i <= m && j <= n; k++) //将SR中的记录由大到小并入TR
    {

        if (SR[i] <= SR[j])
        {
            TR[k] = SR[i++];
        }
        else
        {
            TR[k] = SR[j++];
        }
    }

    if (i <= m) //将剩余的SR[i...m]赋值到TR
    {

        for (int a = i; a <= m; a++)
        {
            TR[k++] = SR[a];
        }
    }
    else if (j <= n) //将剩余的SR[j...n]赋值到TR
    {
        for (int b = j; b <= n; b++)
        {
            TR[k++] = SR[b];
        }
    }
}

//归并排序递归算法
void MergeSort(int *SR, int *TR1, int s, int t)
{
    int TR2[100];
    int m;

    if (s == t)
    {
        TR1[s] = SR[s];
    }

    else
    {
        m = (s + t) / 2;
        MergeSort(SR, TR2, s, m);
        MergeSort(SR, TR2, m+1, t);
        Merge(TR2,TR1,s,m,t);
    }
}

int main(int argc, char const *argv[])
{
    SqList L = SqList();
    QSort q = QSort();
    q.CreateSqList(L);
    q.SqListDisplay(L);
    // q.BubbleSort(L);
    q.SSelectionSort(L);
    q.SqListDisplay(L);
    return 0;
}
