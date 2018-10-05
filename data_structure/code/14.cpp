#include <iostream>

using namespace std;

typedef struct SqList
{
    int *r;
    int length;
} SqList;

void SInsertSort(SqList &L)
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

int main(int argc, char const *argv[])
{
    SqList *L = new SqList;
    L->length = 6;
    L->r = (int *)malloc(L->length * sizeof(int));
    L->r[0] = 0;
    L->r[1] = 20;
    L->r[2] = 11;
    L->r[3] = 60;
    L->r[4] = 12;
    L->r[5] = 11;

    SInsertSort(*L);
    
    for(int i = 1; i < L->length; i++)
    {
        cout << L->r[i] << " ";
    }
    cout << endl;
    return 0;
}
