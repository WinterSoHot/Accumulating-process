#include <iostream>

using namespace std;

class Line
{

  private:
    int *ptr;

  public:
    int getLength();
    Line(int len);         // 简单的构造函数
    Line(const Line &obj); // 拷贝构造函数
    ~Line();               // 析构函数
};

Line::Line(int len)
{
    cout << "调用构造参数" << endl;
    // 为指针分配内存
    ptr = new int;
    *ptr = len;
}

Line::Line(const Line &obj)
{
    cout << "调用拷贝构造函数并为指针 ptr 分配内存" <<  endl;
    ptr = new int;
    *ptr = *obj.ptr; //拷贝值
}

Line::~Line()
{
    cout << "释放内存" << endl;
    delete ptr;
}

int Line::getLength()
{
    return *ptr;
}

//这里 会调用拷贝函数，来构造形参对象
void display(Line obj)
{
    cout << "line 的大小 ：" << obj.getLength() << endl;
}

int main()
{
    Line line(10);

    Line line2 = line ; //这里调用了拷贝参数

    display(line);
    display(line2);
    return 0;
}
