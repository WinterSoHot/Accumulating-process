package main

import "fmt"

type Books struct {
	title   string
	author  string
	subject string
	book_id int
}

func main() {
	book1 := Books{"Go 语言", "anonymous", "code", 6495407}
	fmt.Println(book1)
	book2 := Books{title: "Go", author: "anonymous", subject: "code", book_id: 1}
	fmt.Println(book2)
	/* 打印 Book1 信息 */
	fmt.Printf("Book 1 title : %s\n", book1.title)
	fmt.Printf("Book 1 author : %s\n", book1.author)
	fmt.Printf("Book 1 subject : %s\n", book1.subject)
	fmt.Printf("Book 1 book_id : %d\n", book1.book_id)

	/* 打印 Book2 信息 */
	fmt.Printf("Book 2 title : %s\n", book2.title)
	fmt.Printf("Book 2 author : %s\n", book2.author)
	fmt.Printf("Book 2 subject : %s\n", book2.subject)
	fmt.Printf("Book 2 book_id : %d\n", book2.book_id)
}

// 作为函数参数
func printBook( book Books ) {
	fmt.Printf( "Book title : %s\n", book.title)
	fmt.Printf( "Book author : %s\n", book.author)
	fmt.Printf( "Book subject : %s\n", book.subject)
	fmt.Printf( "Book book_id : %d\n", book.book_id)
}