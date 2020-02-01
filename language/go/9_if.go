package main

import "fmt"

func main() {
	var a int = 100
	if a < 20 {
		fmt.Println("a < 20")
	} else {
		fmt.Println("a >= 20")
	}
	fmt.Printf("a = %d", a)
}
