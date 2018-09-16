#include <iostream>

//一元多项式

using namespace std;

#define MAX 20

typedef struct
{
    float coef;
    int exp;
} PolyArray[MAX];

struct PolyNode
{
    float coef;
    int exp;
    PolyNode *next;
};

class Poly
{
  private:
    PolyNode *Head;

  public:
    Poly(){};
    ~Poly(){};
    void CreatePoly(PolyArray a, int n); //创建多项式
    void PolyDisplay();                  //多项式显示
    void PolySort();                     // 有序表排列
    void PolyAdd(Poly LB);               //多项式相加
};

void Poly::CreatePoly(PolyArray a, int n)
{
    PolyNode *s, *r;
    int i;
    r = Head;

    for (i = 0; i < n; i++)
    {
        s = new PolyNode;
        s->coef = a[i].coef;
        s->exp = a[i].coef;
        s->next = NULL;
        r->next = s;
        r = s;
    }
}

void Poly::PolySort()
{
    PolyNode *p, *q, *r;
    p = Head->next;

    if (p != NULL)
    {
        r = p->next;
        p->next = NULL;
        p = r;

        while (p != NULL)
        {
            r = p->next;
            q = Head;
            while (q->next != NULL && q->next->exp < p->exp)
            {
                q = q->next;
                p->next = q->next;
                q->next = p;
                p = r;
            }
        }
    }
}

void Poly::PolyAdd(Poly LB)
{
    float sum;
    PolyNode *pa, *pb, *qa, *qb;
    pa = Head;
    qa = pa->next;
    pb = LB.Head;
    qb = qb->next;

    while (qa != NULL && qb != NULL)
    {
        if (qa->exp < qb->exp)
        {
            pa = qa;
            qa = qa->next;
        }
        else if (qa->exp > qb->exp)
        {
            pb->next = qb->next;
            qb->next = qa;
            pa->next = qb;
            pa = qb;
            qb = qb->next;
        }
        else
        {
            sum = qa->coef + qb->coef;
            if (sum == 0)
            {
                pa->next = qa->next;
                delete qa;
                qa = pa->next;
                pb->next = qb->next;
                delete qb;
                qb = pb->next;
            }

            else
            {
                qa->coef = sum;
                pa = qa;
                qa = qa->next;
                pb->next = qb->next;
                delete qb;
                qb = pb->next;
            }
        }
    }

    if (qb != NULL)
    {
        pa->next = qb;
    }
}
main(int argc, char const *argv[])
{
    PolyArray a = {{7.0, 0}, {3.0, 1}, {9.0, 8}, {5.0, 16}};
    cout << a;
    PolyArray b = {{8.0, 1}, {22, 7}, {-9.0, 8}};
    Poly LA = Poly(), LB = Poly();
    LA.CreatePoly(a, 4);
    LB.CreatePoly(b, 3);
    LA.PolySort();
    LB.PolySort();
    LA.PolyAdd(LB);
    return 0;
}
