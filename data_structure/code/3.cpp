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
        Poly();
        ~Poly();
        void CreatePoly(PolyArray a,int n);//创建多项式
        void PolyDisplay();//多项式显示
        void PolySort();// 有序表排列
        void PolyAdd(Poly LB);//多项式相加
};

void Poly::CreatePoly(PolyArray a,int n)
{
    PolyNode *s,*r;
    int i;
    r = Head;
    
    for(i = 0; i < n; i++)
    {
        s = new PolyNode;
        s->coef = a[i].coef;
        s->exp = a[i].coef;
        s->next = NULL;
        r->next = s;
        r = s;
    }
    
}
main(int argc, char const *argv[])
{
    /* code */
    return 0;
}
