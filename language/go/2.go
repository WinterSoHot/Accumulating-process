package main

import "fmt"

const (
	A = 1 << iota
	B = 3 << iota
	C
	D
)

func main() {
	fmt.Println(A, B, C, D)
}
