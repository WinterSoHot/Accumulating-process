package main

import "fmt"

func main() {
	var arr [10] int
	var i, j int
	for i = 0; i < 10; i++ {
		arr[i] = i
	}

	for j = 0; j < 10; j++ {
		fmt.Println(arr[j])
	}
}
