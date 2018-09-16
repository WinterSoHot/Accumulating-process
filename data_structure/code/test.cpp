#include <iostream>

using namespace std;


typedef struct
{
    int a;
    float b;
    char c;
} MyArray[5];


int main(int argc, char const *argv[])
{
    MyArray a = {{1,1,65},{2,1,66}};
    
    
    for(int i = 0; i < 2; i++)
    {
        cout << a[i].a << " " << a[i].b << " " << a[i].c << endl; 
    }
    
    return 0;
}
