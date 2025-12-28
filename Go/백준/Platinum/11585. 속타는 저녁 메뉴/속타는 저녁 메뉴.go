package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

var (
	n   int
	src []rune
	pat []rune
	lps []int

	rb = bufio.NewReader(os.Stdin)
)

func main() {
	fmt.Fscan(rb, &n)
	rb.ReadString('\n')

	src = make([]rune, n*2)
	pat = make([]rune, n)

	line, _ := rb.ReadString('\n')
	sl := strings.Fields(line)
	for i := range n {
		pat[i] = rune(sl[i][0])
	}

	line, _ = rb.ReadString('\n')
	sl = strings.Fields(line)
	for i := range n {
		src[i] = rune(sl[i][0])
		src[i+n] = src[i]
	}

	lps = makeLPS()
	cnt := LPS()

	gcd := GCD(cnt, n)
	fmt.Printf("%d/%d", cnt/gcd, n/gcd)
}

func LPS() int {
	si := 0
	pi := 0
	cnt := 0

	for si < 2*n-1 {
		if src[si] == pat[pi] {
			si++
			pi++
			if pi == n {
				cnt++
				pi = lps[pi-1]
			}
		} else {
			if pi == 0 {
				si++
			} else {
				pi = lps[pi-1]
			}
		}
	}
	return cnt
}

func GCD(a, b int) int {
	max, min := max(a, b), min(a, b)

	if max%min == 0 {
		return min
	}

	return GCD(max%min, min)
}

func makeLPS() []int {
	lps := make([]int, n)
	i := 1
	len := 0
	for i < n {
		if pat[len] == pat[i] {
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
