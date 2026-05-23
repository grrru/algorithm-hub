package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	const str = "HelloWorld"
	rb := bufio.NewReader(os.Stdin)

	var i int
	fmt.Fscan(rb, &i)

	fmt.Println(str[0:i-1] + str[i:10])
}
