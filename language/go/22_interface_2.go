package main

import (
	"fmt"
)

type DataWriter interface {
	WriteData(data interface{}) error
	CanWriter() bool
}

type file struct {
}

//interface{}可用于向函数传递任意类型的变量，
//但对于函数内部，该变量仍然为interface{}类型（空接口类型），
func (f *file) WriteData(data interface{}) error {
	// 模拟写入数据
	fmt.Println("WriteData:", data)
	// 接口类型向普通类型的转换称为类型断言(运行期确定)-
	// b,ok :=a.([]int)//通过断言实现类型转换
	return nil
}

func (f *file) CanWriter() bool {
	return true
}

func main() {
	var f DataWriter
	f = new(file)
	f.WriteData("data")
}
