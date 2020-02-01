package main

import "fmt"

func main() {
	var a int = 20
	fmt.Printf("变量的地址：%p\n", &a)
	var p *int
	p = &a
	fmt.Printf("变量的地址：%p\n", p)

	var arr = [5]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
	var arrPoint [5]*float32
	for i := 0; i < 5; i++ {
		arrPoint[i] = &arr[i]
	}
	for i := 0; i < 5; i++ {
		fmt.Printf("地址：%p,值：%v\n",arrPoint[i],*arrPoint[i])
	}
}
