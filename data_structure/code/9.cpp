#include <iostream>
#include <string>
using namespace std;

struct GLNode
{
    int tag;
    char data;
    struct atom
    {
        GLNode *hp, *tp;
    } ptr;
};

class GList
{
  private:
    GLNode *ls; //指向表头的指针
    char result[50];
    int count;                      //计数
    GLNode *CreateGList(string st); //由广义表的书面格式s创建广义表
    GLNode *CopyGList(GLNode *ts, GLNode *ls);
    int Depth(GLNode *ls);
    void Print(GLNode *ls);

  public:
    GList();
    GList(string s);
    ~GList();
    int DepthGList();
    void GListCopy();
    void Server(string &str, string &hstr);
    void GListDisplay();
};

GList::GList()
{
    ls = NULL;
    count = 0;
}

GList::~GList()
{
    delete[] ls;
}
