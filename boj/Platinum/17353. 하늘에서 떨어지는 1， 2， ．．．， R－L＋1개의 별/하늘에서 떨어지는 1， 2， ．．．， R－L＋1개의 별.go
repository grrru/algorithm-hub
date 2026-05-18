package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n    int
	arr  []int
	Q    int
	tree []int
	lazy []int
)

var (
	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func Start() {
	fmt.Fscan(rb, &n)
	arr = make([]int, n+1)
	tree = make([]int, 4*(n+2))
	lazy = make([]int, 4*(n+2))

	for i := 1; i <= n; i++ {
		fmt.Fscan(rb, &arr[i])
	}
}

func main() {
	defer wb.Flush()
	Start()

	fmt.Fscan(rb, &Q)
	var cmd, l, r, x int
	for Q > 0 {
		Q--
		fmt.Fscan(rb, &cmd)
		if cmd == 1 {
			fmt.Fscan(rb, &l, &r)
			Fall(l, r)
		} else {
			fmt.Fscan(rb, &x)
			Query(x)
		}
	}
}

func Fall(l, r int) {
	Update(1, l, r, 1, n, 1)
	Update(-(r - l + 1), r+1, r+1, 1, n, 1)
}

func Query(x int) {
	fmt.Fprintln(wb, GetTree(1, x, 1, n, 1)+arr[x])
}

func Update(val, l, r, s, e, idx int) {
	Propagate(s, e, idx)

	if r < s || e < l {
		return
	}

	if l <= s && e <= r {
		lazy[idx] += val
		Propagate(s, e, idx)
		return
	}

	mid := (s + e) / 2
	Update(val, l, r, s, mid, idx*2)
	Update(val, l, r, mid+1, e, idx*2+1)
	tree[idx] = tree[idx*2] + tree[idx*2+1]
}

func Propagate(s, e, idx int) {
	if lazy[idx] != 0 {
		tree[idx] += lazy[idx] * (e - s + 1)

		if s != e {
			lazy[idx*2] += lazy[idx]
			lazy[idx*2+1] += lazy[idx]
		}

		lazy[idx] = 0
	}
}

func GetTree(l, r, s, e, idx int) int {
	Propagate(s, e, idx)

	if r < s || e < l {
		return 0
	}

	if l <= s && e <= r {
		return tree[idx]
	}

	mid := (s + e) / 2
	return GetTree(l, r, s, mid, idx*2) + GetTree(l, r, mid+1, e, idx*2+1)
}
