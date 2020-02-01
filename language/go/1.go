package main

import "fmt"

const (
	Unknown = 0
	Female  = iota
	Male
)

func main() {
	fmt.Println("Hello,World!")
	var a string = "gudongxian"
	var b, c int = 1, 2
	fmt.Println(a)
	fmt.Println(b, c)

	var d bool
	fmt.Println(d)
	var i int
	var f float64
	var b1 bool
	var s string
	fmt.Printf("%v %v %v %q\n", i, f, b1, s)
	var d1 = true
	fmt.Println(d1)
	g := "1234"
	fmt.Println(g, &g)

	fmt.Print(Unknown, Female, Male)
}
