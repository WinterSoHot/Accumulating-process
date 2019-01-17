#include <iostream>

extern int counter;

void write_extern(void)
{
    std::cout << "Count is " << counter << std::endl;
}