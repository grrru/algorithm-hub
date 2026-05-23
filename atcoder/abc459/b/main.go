package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
)

var (
	n   int
	str string

	rb = bufio.NewReader(os.Stdin)
)

func main() {
	fmt.Fscan(rb, &n)

	ans := 0

	for i := range n {
		fmt.Fscan(rb, &str)

		p := float64(n - i - 1)

		switch str[0] {
		case 'a', 'b', 'c':
			ans += 2 * int(math.Pow(10, p))
		case 'd', 'e', 'f':
			ans += 3 * int(math.Pow(10, p))
		case 'g', 'h', 'i':
			ans += 4 * int(math.Pow(10, p))
		case 'j', 'k', 'l':
			ans += 5 * int(math.Pow(10, p))
		case 'm', 'n', 'o':
			ans += 6 * int(math.Pow(10, p))
		case 'p', 'q', 'r', 's':
			ans += 7 * int(math.Pow(10, p))
		case 't', 'u', 'v':
			ans += 8 * int(math.Pow(10, p))
		case 'w', 'x', 'y', 'z':
			ans += 9 * int(math.Pow(10, p))
		}
	}

	fmt.Println(ans)
}
