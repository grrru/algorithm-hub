package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n   int
	arr []int
	rb  = bufio.NewReader(os.Stdin)
)

func main() {
	fmt.Fscan(rb, &n)
	arr = make([]int, n+1)
	for i := 1; i <= n; i++ {
		fmt.Fscan(rb, &arr[i])
	}

	fmt.Println(maxVal(1, n))
}

func maxVal(l, r int) int {
	if r == l {
		return arr[r]
	}

	mid := (l + r) / 2

	res := max(maxVal(l, mid), maxVal(mid+1, r))

	x, y := mid, mid+1

	h := min(arr[x], arr[y])
	res = max(res, h*2)

	for x > l || y < r {
		if x == l {
			y++
			h = min(h, arr[y])
		} else if y == r {
			x--
			h = min(h, arr[x])
		} else if arr[x-1] > arr[y+1] {
			x--
			h = min(h, arr[x])
		} else {
			y++
			h = min(h, arr[y])
		}

		res = max(res, h*(y-x+1))
	}
	return res
}
