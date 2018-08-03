#include <iostream>

using namespace std;


class Calcuator{
    public:
        int Calcuate(int,int,char);
};


int main(int argc, char const *argv[])
{
    int x,y;
    char oper;

    cout << "Hello, I'm a calacuator."<<endl;
    cout << "Please input x y oper" << endl;

    cin >> x >> y >> oper;

    cout << x << oper << y;

    cin.ignore();
    cin.get();
    
    return 0;
}

int Calcuator::Calcuate(int x,int y,char oper)
{
    return 0;
}