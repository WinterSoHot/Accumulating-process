#include <iostream>

using namespace std;

class Line
{
  private:
    double length;

  public:
    void setLength(double len);
    double getLength();
    Line();
    Line(double len); //这是构造参数(可以带参数)
    ~Line();          //这是析构函数声明
};

Line::Line()
{
    cout << "Object is being created" << endl;
}

Line::~Line()
{
    cout << "Object is being deleted." << endl;
}
// Line::Line(double len)
// {
//     length = len;
//     cout << "Object is being created" << endl;
// }

//使用初始化列表来初始化字段 和上面的效果一样
Line::Line(double len) : length(len)
{
    cout << "Object is being created" << endl;
}
double Line::getLength()
{
    return length;
}

void Line::setLength(double len)
{
    length = len;
}

main(int argc, char const *argv[])
{
    // Line line;
    Line line(10.0);
    cout << "Length of line : " << line.getLength() << endl;
    line.setLength(6.66);
    cout << "Length of Line is " << line.getLength() << endl;
    return 0;
}
