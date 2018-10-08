#include <iostream>

using namespace std;

#define MAX_VERTEX_NUM 20
const int infinity = INT32_MAX;

struct ArcCell
{
    int adj; //对无权图1,0表示是否相邻，对带权图表示权值类型
    char *info;
};

struct MGraph
{
    string vexs[MAX_VERTEX_NUM];                  //顶点表
    ArcCell arcs[MAX_VERTEX_NUM][MAX_VERTEX_NUM]; //邻接矩阵表，即边表
    int vexnum;                                   //顶点数
    int arcnum;                                   //边数
    int kind;                                     //邻接矩阵存储的图的种类
};

class Graph
{
  private:
    MGraph mgraph;
    bool visited[MAX_VERTEX_NUM];

  public:
    Graph(/* args */);
    ~Graph();
    void CreateGraph();
    int LocateVex(string u); //返回顶点u在图中的位置
    bool CreateDG();         //构造有向图
    bool CreateUDG();        //构造无向图
    bool CreateDN();         //构造有向网
    bool CreateUDN();        //构造无向网
    void Display();          //输出邻接矩阵
    void DFSTraverse(int v); //深度优先遍历
    void BFDTraverse(int v); //广度优先遍历
};

Graph::~Graph()
{
}
