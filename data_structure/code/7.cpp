#include <iostream>

using namespace std;

#define chunksize 100
typedef struct chunk
{
    char ch[chunksize];
    chunk *next;
} chunk;

typedef struct
{
    chunk *head, *tail;
    int curlen;
} Lstring;