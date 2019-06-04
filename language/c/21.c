#include<stdio.h>
#include<string.h>
struct Books
{
    char title[50];
    char author[50];
    char subject[150];
    int book_id;
};
void print_book(struct Books book);
void print_book2(struct Books *book);
int main()
{
    struct Books Book1;
    struct Books Book2;
  
     /* Book1 详述 */
   strcpy( Book1.title, "C Programming");
   strcpy( Book1.author, "Nuha Ali"); 
   strcpy( Book1.subject, "C Programming Tutorial");
   Book1.book_id = 6495407;
 
   /* Book2 详述 */
   strcpy( Book2.title, "Telecom Billing");
   strcpy( Book2.author, "Zara Ali");
   strcpy( Book2.subject, "Telecom Billing Tutorial");
   Book2.book_id = 6495700;
   
   /* 输出 Book1 信息 */
   printf( "Book 1 title : %s\n", Book1.title);
   printf( "Book 1 author : %s\n", Book1.author);
   printf( "Book 1 subject : %s\n", Book1.subject);
   printf( "Book 1 book_id : %d\n", Book1.book_id);
 
   /* 输出 Book2 信息 */
   printf( "Book 2 title : %s\n", Book2.title);
   printf( "Book 2 author : %s\n", Book2.author);
   printf( "Book 2 subject : %s\n", Book2.subject);
   printf( "Book 2 book_id : %d\n", Book2.book_id);
   
   print_book(Book1);
   print_book(Book2);
   print_book2(&Book1);
   print_book2(&Book2);
}

//结构体作为参数
void print_book(struct Books book)
{
   printf( "Book  title : %s\n", book.title);
   printf( "Book  author : %s\n", book.author);
   printf( "Book  subject : %s\n", book.subject);
   printf( "Book  book_id : %d\n", book.book_id);
}

void print_book2(struct Books *book)
{   //为了使用指向该结构的指针访问结构的成员，您必须使用 -> 运算符
   printf( "Book  title : %s\n", book->title);
   printf( "Book  author : %s\n", book->author);
   printf( "Book  subject : %s\n", book->subject);
   printf( "Book  book_id : %d\n", book->book_id);
}