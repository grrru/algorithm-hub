package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	T        int
	n        int
	m        int
	tree     []int
	position []int

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func main() {
	defer wb.Flush()
	fmt.Fscan(rb, &T)
	var book int
	for T > 0 {
		T--
		fmt.Fscan(rb, &n, &m)
		setting()

		cnt := m
		for cnt > 0 {
			fmt.Fscan(rb, &book)
			fmt.Fprintf(wb, "%d ", Query(book))
			Update(book, cnt)
			cnt--
		}

		fmt.Fprintln(wb)
	}
}

func Query(book int) int {
	return getSum(1, 1, n+m, 1, position[book]-1)
}

func Update(book, cnt int) {
	oldPos := position[book]
	position[book] = cnt

	updateTree(1, 1, n+m, oldPos, -1)
	updateTree(1, 1, n+m, position[book], 1)
}

func updateTree(idx, s, e, pos, diff int) {
	if pos < s || e < pos {
		return
	}

	if s == e {
		tree[idx] += diff
		return
	}

	mid := (s + e) / 2

	updateTree(idx*2, s, mid, pos, diff)
	updateTree(idx*2+1, mid+1, e, pos, diff)

	tree[idx] = tree[idx*2] + tree[idx*2+1]
}

func getSum(idx, cs, ce, gs, ge int) int {
	if gs <= cs && ce <= ge {
		return tree[idx]
	}

	if ge < cs || ce < gs {
		return 0
	}

	mid := (cs + ce) / 2

	return getSum(idx*2, cs, mid, gs, ge) + getSum(idx*2+1, mid+1, ce, gs, ge)
}

func setting() {
	tree = make([]int, 4*(n+m+1))
	position = make([]int, n+1)

	for i := 1; i <= n; i++ {
		position[i] = m + i
	}
	makeTree(1, 1, n+m)
}

func makeTree(idx, s, e int) int {
	if s == e {
		if s > m {
			tree[idx] = 1
		}
		return tree[idx]
	}

	mid := (s + e) / 2

	tree[idx] = makeTree(idx*2, s, mid) + makeTree(idx*2+1, mid+1, e)

	return tree[idx]
}
