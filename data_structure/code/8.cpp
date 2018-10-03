#include <iostream>

using namespace std;

const int MaxSize = 1000;

typedef int ElemType;

struct Triple
{
    int i;
    int j;
    ElemType e;
};

class SMatrix
{
  private:
    int mu; //行数
    int nu; //列数
    int tu; //非零数据的个数
    Triple *data;

  public:
    SMatrix();
    SMatrix(int m, int n, int k, Triple data[]);
    ~SMatrix();
    SMatrix MCreate(int d[][3], int m, int n, int k);
    void MDisplay(SMatrix a);
    void MatrixTrans_1(SMatrix A, SMatrix &B); //矩阵转置算法1
    void MatrixTrans(SMatrix A, SMatrix &B);   //快速转置算法
};

SMatrix::SMatrix()
{
    mu = 0;
    nu = 0;
    tu = 0;

    for (int p = 0; p < tu; p++)
    {
        data[p].i = 0;
        data[p].j = 0;
        data[p].e = 0;
    }
}

SMatrix::SMatrix(int m, int n, int k, Triple data[])
{
    this->mu = m;
    this->nu = n;
    this->tu = k;
    this->data = data;
}

SMatrix SMatrix::MCreate(int d[][3], int m, int n, int k)
{
    SMatrix M = {m, n, k, NULL};
    if (k != 0)
        M.data = new Triple[k];

    for (int i = 0; i < k; i++)
    {
        M.data[i].i = d[i][0];
        M.data[i].j = d[i][1];
        M.data[i].e = d[i][2];
    }
    return M;
}
//矩阵遍历
void SMatrix::MDisplay(SMatrix a)
{
    Triple p;
    int i, j, k, c = 0;
    p = a.data[k];

    for (i = 0; i < a.mu; i++)
    {

        for (j = 1; j < a.nu; j++)
        {

            if (k < a.tu && p.i == i && p.j == j)
            {
                cout << "\t" << p.e;
                k++;
                if (k < a.tu)
                    p = a.data[k];
            }
            else
            {
                cout << "\t" << c;
            }
        }
        cout << endl;
    }
}

//基于三元组顺序表的转置算法
void SMatrix::MatrixTrans_1(SMatrix A, SMatrix &B)
{
    B.mu = A.nu;
    B.nu = A.tu;
    B.tu = A.tu;
    int q, p;
    int col;
    if (B.tu)
    {
        q = 0;
        for (col = 1; col < A.nu; col++)
        {

            for (p = 0; p <= A.tu - 1; p++)
            {

                if (A.data[p].j == col)
                {
                    B.data[q].i = A.data[p].j;
                    B.data[q].j = A.data[p].i;
                    B.data[q].e = A.data[p].e;
                    q++;
                }
            }
        }
    }
}

//快速转置算法
void SMatrix::MatrixTrans(SMatrix A, SMatrix &B)
{
    int col, k, p, q;
    int *num, *cpot;
    num = new int[B.nu];
    cpot = new int[B.nu];

    if (B.tu)
    {

        for (col = 0; col < A.tu; col++)
        {
            num[col] = 0;
        }

        for (k = 0; k < A.tu; k++)
        {
            num[A.data[k].j]++;
        }
        cpot[0] = 0;

        for (col = 1; col <= A.nu; col++)
        {
            cpot[col] = cpot[col - 1] + num[col - 1];
        }

        for (p = 0; p < A.tu; p++)
        {
            col = A.data[p].j;
            q = cpot[col];
            B.data[q].i = A.data[p].j;
            B.data[q].j = A.data[p].i;
            B.data[q].e = A.data[p].e;
            cpot[col]++;
        }
    }
}
