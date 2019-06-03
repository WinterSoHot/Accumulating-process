#include <iostream>

using namespace std;

class printData
{
  public:
    void print(int i)
    {
        cout << "整数为: " << i << endl;
    };
    void print(double i)
    {
        cout << "double : " << i << endl;
    };
    void print(char a[])
    {
        cout << "string :" << a << endl;
    }
};

class Box
{
  public:
    double getVolume(void)
    {
        return length * breadth * height;
    }
    void setLength(double len)
    {
        length = len;
    }

    void setBreadth(double bre)
    {
        breadth = bre;
    }

    void setHeight(double hei)
    {
        height = hei;
    }
    // 重载 + 运算符，用于把两个 Box 对象相加
    Box operator+(const Box &b)
    {
        Box box;
        box.length = this->length + b.length;
        box.breadth = this->breadth + b.breadth;
        box.height = this->height + b.height;
        return box;
    }

  private:
    double length;  // 长度
    double breadth; // 宽度
    double height;  // 高度
};

class Distance
{
  private:
    int feet;   //0到无穷
    int inches; //0到12
  public:
    Distance()
    {
        feet = 0;
        inches = 0;
    };
    Distance(int f, int i)
    {
        feet = f;
        inches = i;
    };
    //显示距离
    void displayDistance()
    {
        cout << "Feet: " << feet << " Inche: " << inches << endl;
    }
    //重载运算符 -
    Distance operator-()
    {
        feet = -feet;
        inches = -inches;
        return Distance(feet, inches);
    }
    //重载<运算符
    bool operator<(const Distance &d)
    {
        if (feet < d.feet)
        {
            return true;
        }
        if (feet == d.feet && inches < d.inches)
        {
            return true;
        }
        return false;
    }
    //重载赋值运算符
    void operator=(const Distance &D)
    {
        feet = D.feet;
        inches = D.inches;
    }

    // 重载函数调用运算符
    Distance operator()(int a, int b, int c)
    {
        Distance D;
        // 进行随机计算
        D.feet = a + c + 10;
        D.inches = b + c + 100;
        return D;
    }
    //重载输入输出运算符
    friend ostream &operator<<(ostream &output, const Distance &D)
    {
        output << "F : " << D.feet << " I : " << D.inches;
        return output;
    }

    friend istream &operator>>(istream &input, Distance &D)
    {
        input >> D.feet >> D.inches;
        return input;
    }
};

class Time
{
  private:
    int hours;   // 0 到 23
    int minutes; // 0 到 59
  public:
    // 所需的构造函数
    Time()
    {
        hours = 0;
        minutes = 0;
    }
    Time(int h, int m)
    {
        hours = h;
        minutes = m;
    }
    // 显示时间的方法
    void displayTime()
    {
        cout << "H: " << hours << " M:" << minutes << endl;
    }
    // 重载前缀递增运算符（ ++ ）
    Time operator++()
    {
        ++minutes; // 对象加 1
        if (minutes >= 60)
        {
            ++hours;
            minutes -= 60;
        }
        return Time(hours, minutes);
    }
    // 重载后缀递增运算符（ ++ ）
    Time operator++(int)
    {
        // 保存原始值
        Time T(hours, minutes);
        // 对象加 1
        ++minutes;
        if (minutes >= 60)
        {
            ++hours;
            minutes -= 60;
        }
        // 返回旧的原始值
        return T;
    }
};

#define SIZE 10
class safearay
{
  private:
    int arr[SIZE];

  public:
    safearay()
    {
        register int i;
        for (i = 0; i < SIZE; i++)
        {
            arr[i] = i;
        }
    }
    int &operator[](int i)
    {
        if (i > SIZE)
        {
            cout << "索引超过最大值" << endl;
            // 返回第一个元素
            return arr[0];
        }
        return arr[i];
    }
};
int main()
{
    printData pd;
    pd.print(1);
    pd.print(1.0);
    char a[] = "Hello";
    pd.print(a);

    Box Box1;            // 声明 Box1，类型为 Box
    Box Box2;            // 声明 Box2，类型为 Box
    Box Box3;            // 声明 Box3，类型为 Box
    double volume = 0.0; // 把体积存储在该变量中

    // Box1 详述
    Box1.setLength(6.0);
    Box1.setBreadth(7.0);
    Box1.setHeight(5.0);

    // Box2 详述
    Box2.setLength(12.0);
    Box2.setBreadth(13.0);
    Box2.setHeight(10.0);

    // Box1 的体积
    volume = Box1.getVolume();
    cout << "Volume of Box1 : " << volume << endl;

    // Box2 的体积
    volume = Box2.getVolume();
    cout << "Volume of Box2 : " << volume << endl;

    // 把两个对象相加，得到 Box3
    Box3 = Box1 + Box2;

    // Box3 的体积
    volume = Box3.getVolume();
    cout << "Volume of Box3 : " << volume << endl;

    Distance D1(11, 10), D2(5, 11), D3;

    if (D1 < D2)
    {
        cout << "D1 is less than D2 " << endl;
    }
    else
    {
        cout << "D2 is less than D1 " << endl;
    }
    cout << "Enter the value of object : " << endl;
    cin >> D3;
    cout << "First Distance : " << D1 << endl;
    cout << "Second Distance :" << D2 << endl;
    cout << "Third Distance :" << D3 << endl;

    D2 = D1(10, 10, 10); // invoke operator()
    cout << "Second Distance :";
    D2.displayDistance();

    safearay A;

    cout << "A[2] 的值为 : " << A[2] << endl;
    cout << "A[5] 的值为 : " << A[5] << endl;
    cout << "A[12] 的值为 : " << A[12] << endl; 
    return 0;
}