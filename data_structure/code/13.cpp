#include <iostream>

using namespace std;

//邻接表
struct ArcNode
{
    int adjvex;
    ArcNode *next;
};

struct VertexNode
{
    int vertex;
    ArcNode *firstedge;
};

#define MAX_VERTEX_NUM 20

struct ArcNode
{
    int adjvex;
    struct ArcNode *nextarc;
    int *info;
};

struct VNode
{
    string data;
    ArcNode *firstarc;
};

struct AdjLGraph
{
    VNode vertices[MAX_VERTEX_NUM];
    int vexnum;
    int arcnum;
    int kind;
};

class ALGraph
{
  private:
    AdjLGraph algraph;
    bool visited[MAX_VERTEX_NUM];

  public:
    ALGraph(/* args */);
    ~ALGraph();
    void CreateGraph();
    int LocateVex(string u);
    void ALGraphDisplay();
    void FindInDegree(int indegree[]);
    bool TopologicalSort();
};

ALGraph::ALGraph(/* args */)
{
}

ALGraph::~ALGraph()
{
}

//十字链表

#define MAX_INFO 10

struct ArcBox
{
    int tailvex, headvex;
    ArcBox *hlink, *tlink;
    char *info;
};

struct VexNode
{
    string data;
    ArcBox *firstin, *firstout;
};

struct OLGraph
{
    VexNode xlist[MAX_VERTEX_NUM];
    int vexnum, arcnum;
};

class OrListGraph //有向图的十字链表表示
{
  private:
    OLGraph olgraph;
    bool visited[MAX_VERTEX_NUM];

  public:
    OrListGraph(/* args */) {}
    ~OrListGraph() {}
    void CreateGraph();
    int LocateVex(string u);
    void Display();
};

//邻接多重表
