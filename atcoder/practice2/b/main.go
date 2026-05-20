package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

var (
	n, q int
	tree []int

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func Add(v, idx int) {
	for idx <= n {
		tree[idx] += v
		idx += idx & -idx
	}
}

func Get(l, r int) int {
	return sum(r) - sum(l-1)
}

func sum(idx int) int {
	res := 0
	for idx > 0 {
		res += tree[idx]
		idx -= idx & -idx
	}
	return res
}

func main() {
	defer wb.Flush()

	fmt.Fscan(rb, &n, &q)

	tree = make([]int, n+1)

	var v int
	for i := range n {
		fmt.Fscan(rb, &v)
		Add(v, i+1)
	}

	var cmd, a, b int
	for range q {
		fmt.Fscan(rb, &cmd, &a, &b)

		if cmd == 0 {
			Add(b, a+1)
		} else {
			res := Get(a+1, b)
			wb.WriteString(strconv.Itoa(res))
			wb.WriteRune('\n')
		}
	}
}
