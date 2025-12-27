package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func main() {
	defer wb.Flush()

	var str string
	for {
		fmt.Fscan(rb, &str)
		if str == "." {
			break
		}

		n := len(str)
		lps := makeLPS(n, str)

		patternLen := n - lps[n-1]

		ans := 1
		if n%patternLen == 0 {
			ans = n / patternLen
		}
		fmt.Fprintln(wb, ans)
	}
}

func makeLPS(n int, str string) []int {
	lps := make([]int, n)
	len := 0
	i := 1
	for i < n {
		if str[i] == str[len] {
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

	return lps
}
