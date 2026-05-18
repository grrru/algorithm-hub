package main

import (
	"bufio"
	"fmt"
	"os"
	"slices"
)

var (
	n       int
	src     []int
	pattern []int
	lps     []int

	rb = bufio.NewReader(os.Stdin)
)

const (
	Impossible = "impossible"
	Possible   = "possible"
)

func main() {
	fmt.Fscan(rb, &n)
	input()

	makeLPS(pattern)

	if find() {
		fmt.Println(Possible)
		return
	}

	fmt.Println(Impossible)
}

func find() bool {

	ti := 0
	pi := 0

	for ti < n*2 {
		if src[ti] == pattern[pi] {
			ti++
			pi++
			if pi == n {
				return true
			}
		} else {
			if pi == 0 {
				ti++
			} else {
				pi = lps[pi-1]
			}
		}
	}

	return false
}

func makeLPS(arr []int) {
	lps = make([]int, len(arr))
	i := 1
	len := 0

	for i < n {
		if arr[i] == arr[len] {
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

func input() {
	arr := make([]int, n)
	src = make([]int, n*2)
	pattern = make([]int, n)

	for i := range n {
		fmt.Fscan(rb, &arr[i])
	}
	slices.Sort(arr)
	for i := range n - 1 {
		src[i] = arr[i+1] - arr[i]
		src[i+n] = src[i]
	}
	src[n-1] = 360000 - (arr[n-1] - arr[0])
	src[2*n-1] = src[n-1]

	for i := range n {
		fmt.Fscan(rb, &arr[i])
	}
	slices.Sort(arr)
	for i := range n - 1 {
		pattern[i] = arr[i+1] - arr[i]
	}
	pattern[n-1] = 360000 - (arr[n-1] - arr[0])
}
