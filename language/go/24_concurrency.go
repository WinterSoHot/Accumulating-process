package main

import "fmt"
import "time"

func say(s string) {
	for i := 0; i < 5; i++ {
		time.Sleep(100 * time.Millisecond)
		fmt.Println(s)
	}
}

func main() {
	go say("I'm concurrency.")
	say("I'm normal.")
}
