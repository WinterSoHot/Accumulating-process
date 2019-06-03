#include <iostream>

using namespace std;

class Box
{
  private:
    double length;
    double breath;
    double height;

  public:
    static int objectCount;
    Box(double l = 2.0, double b = 2.0, double h = 2.0)
    {
        cout << "Constructor called" << endl;
        length = l;
        breath = b;
        height = h;
        // 每次创建对象时增加 1
        objectCount++;
    }
    double Volume()
    {
        return length * breath * height;
    }
    int compare(Box box)
    {
        return this->Volume() > box.Volume();
    }
};
// 初始化类 Box 的静态成员
int Box::objectCount = 0;

int main()
{
    Box Box1(3.3, 1.2, 1.5); // Declare box1
    Box Box2(8.5, 6.0, 2.0); // Declare box2

    if (Box1.compare(Box2))
    {
        cout << "Box2 is smaller than Box1" << endl;
    }
    else
    {
        cout << "Box2 is equal to or larger than Box1" << endl;
    }

    Box *ptrBox;
    // 保存第一个对象的地址
    ptrBox = &Box1;

    // 现在尝试使用成员访问运算符来访问成员
    cout << "Volume of Box1: " << ptrBox->Volume() << endl;

    // 保存第二个对象的地址
    ptrBox = &Box2;

    // 现在尝试使用成员访问运算符来访问成员
    cout << "Volume of Box2: " << ptrBox->Volume() << endl;

    // 输出对象的总数
    cout << "Total objects: " << Box::objectCount << endl;
    return 0;
}