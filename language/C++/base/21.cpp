#include <iostream>

using namespace std;

class Shape
{
  protected:
    int width, height;

  public:
    Shape(int a = 0, int b = 0) : width(a), height(b){};
    // 不加 virtual 导致错误输出的原因是，调用函数 area() 被编译器设置为基类中的版本，
    //这就是所谓的静态多态，或静态链接 - 函数调用在程序执行前就准备好了。有时候这也被称为早绑定
    //，因为 area() 函数在程序编译期间就已经设置好了。
    virtual int area()
    {
        cout << "Parent class area : " << endl;
        return 0;
    }
};

class Rectangle : public Shape
{
  public:
    Rectangle(int a = 0, int b = 0) : Shape(a, b){};
    int area()
    {
        cout << "Rectangle class area :" << endl;
        return width * height;
    };
};

class Triangle : public Shape
{
  public:
    Triangle(int a = 0, int b = 0) : Shape(a, b){};
    int area()
    {
        cout << "Triangle class area :" << endl;
        return ((width * height) / 2);
    }
};

int main()
{
    // 程序的主函数
    Shape *shape;
    Rectangle rec(10, 10);
    Triangle tri(10, 10);
    // 存储矩形的地址
    shape = &rec;
    // 调用矩形的求面积函数 area
    shape->area();

    // 存储三角形的地址
    shape = &tri;
    // 调用三角形的求面积函数 area
    shape->area();
    return 0;
}