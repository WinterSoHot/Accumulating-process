#include <iostream>

using namespace std;

class Shape
{
  public:
    void setWidth(int w)
    {
        width = w;
    };
    void setHeight(int h)
    {
        height = h;
    };

  protected:
    int width;
    int height;
};

class PainCost
{
  public:
    int getCost(int area)
    {
        return area * 70;
    }
};

class Rectangle : public Shape, public PainCost
{
  public:
    int getArea()
    {
        return width * height;
    };
};

int main()
{
    Rectangle rect;
    rect.setWidth(10);
    rect.setHeight(10);

    int area = rect.getArea();
    cout << "Total Area:" << area << endl;

    // 输出总花费
    cout << "Total paint cost: $" << rect.getCost(area) << endl;
    return 0;
}