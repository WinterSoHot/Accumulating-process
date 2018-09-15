#include <iostream>

using namespace std;

const int LISTINCREMENT = 10;
typedef int ElemType;

class SqList_d
{
  private:
    ElemType *elem;
    int length;
    int maxSize;

  public:
    SqList_d(int n);
    ~SqList_d();
    void SqListInsert(int i, int e);
    int SqListDelete(int i);
    void printAllData();
};

SqList_d::SqList_d(int n)
{
    elem = (int *)malloc(n * sizeof(int));
    length = 0;
    maxSize = n;
}

SqList_d::~SqList_d()
{
    delete[] elem;
    length = 0;
    maxSize = 0;
}

void SqList_d::SqListInsert(int i, int e)
{
    if (length >= maxSize)
    {
        elem = (int *)realloc(elem, (maxSize + LISTINCREMENT) * sizeof(int));
    }
    if (i < 1 || i > length + 1)
    {
        cout << "插入位置异常";
        return;
    }

    for (int j = length; j >= i; j--)
        elem[j] = elem[j - 1];
    elem[i - 1] = e;
    length++;
}

int SqList_d::SqListDelete(int i)
{
    int e;

    if (length <= 0)
    {
        cout << "溢出";
        return -1;
    }

    if (i < 1 || i > length + 1)
    {
        /* code */
        cout << "删除位置异常";
        return -1;
    }

    e = elem[i - 1];

    for (int j = i; j < length; j++)
    {
        elem[j - 1] = elem[j];
    }
    length--;
    return e;
}

void SqList_d::printAllData()
{

    for (int i = 0; i < length; i++)
    {
        /* code */
        cout << "Item: " << i << " value: " << this->elem[i] << endl;
    }
}

main(int argc, char const *argv[])
{
    SqList_d la = SqList_d(10);
    la.SqListInsert(1, 1);
    la.printAllData();
    return 0;
}
