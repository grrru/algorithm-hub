package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

var (
	n       int
	arr     []int
	tree    [][]int
	l, r, k int

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func Input() {
	fmt.Fscan(rb, &n)
	arr = make([]int, n+1)
	tree = make([][]int, 4*(n+1))

	for i := 1; i <= n; i++ {
		fmt.Fscan(rb, &arr[i])
	}
}

func Build(s, e, idx int) {
	tree[idx] = make([]int, e-s+2)

	if s == e {
		tree[idx][1] = arr[s]
		return
	}

	mid := (s + e) / 2
	Build(s, mid, idx*2)
	Build(mid+1, e, idx*2+1)
	Merge(idx)
}

func Merge(idx int) {
	var i, x, y int = 1, 1, 1
	for x < len(tree[idx*2]) && y < len(tree[idx*2+1]) {
		if tree[idx*2][x] > tree[idx*2+1][y] {
			tree[idx][i] = tree[idx*2+1][y]
			y++
		} else {
			tree[idx][i] = tree[idx*2][x]
			x++
		}
		i++
	}

	for x < len(tree[idx*2]) {
		tree[idx][i] = tree[idx*2][x]
		i++
		x++
	}

	for y < len(tree[idx*2+1]) {
		tree[idx][i] = tree[idx*2+1][y]
		i++
		y++
	}
}

func main() {
	defer wb.Flush()
	Input()
	Build(1, n, 1)

	var Q int
	fmt.Fscan(rb, &Q)
	for Q > 0 {
		Q--

		fmt.Fscan(rb, &l, &r, &k)

		fmt.Fprintln(wb, Query(l, r, 1, n, 1, k))
	}
}

func Query(l, r, s, e, idx, k int) int {
	if r < s || e < l {
		return 0
	}

	if l <= s && e <= r {
		findIdx := sort.Search(len(tree[idx]), func(i int) bool {
			return tree[idx][i] > k
		})

		return len(tree[idx]) - findIdx
	}

	mid := (s + e) / 2
	return Query(l, r, s, mid, idx*2, k) + Query(l, r, mid+1, e, idx*2+1, k)
}
