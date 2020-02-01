package main

import "fmt"

func max(num1, num2 int) int {
	if num1 > num2 {
		return num1
	} else {
		return num2
	}
}

//返回多个值
func swap(x, y string) (string, string) {
	return y, x
}

func main() {
	fmt.Println(max(12, 2))
	x, y := swap("gu", "dongxian")
	fmt.Println(x, y)
}
