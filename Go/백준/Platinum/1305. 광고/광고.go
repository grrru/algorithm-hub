package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n   int
	str string
	lps []int
)

var rb = bufio.NewReader(os.Stdin)

func main() {
	fmt.Fscan(rb, &n, &str)

	buildLPS()
	fmt.Println(n - lps[n-1])
}

func buildLPS() {
	lps = make([]int, n)

	i := 1
	len := 0

	for i < n {
		if str[len] == str[i] {
			len++
			lps[i] = len
			i++
		} else {
			if len == 0 {
				lps[i] = 0
				i++
			} else {
				len = lps[len-1]
			}
		}
	}
}
