package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n    int
	arr  []int
	tree []int
	lazy []int
)

var (
	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func main() {
	defer wb.Flush()

	fmt.Fscan(rb, &n)
	arr = make([]int, n+1)
	tree = make([]int, 4*(n+1))
	lazy = make([]int, 4*(n+1))

	for i := 1; i <= n; i++ {
		fmt.Fscan(rb, &arr[i])
		Update(i, i, 1, n, 1, arr[i])
	}

	var Q, cmd, l, r, k int
	fmt.Fscan(rb, &Q)
	for Q > 0 {
		Q--
		fmt.Fscan(rb, &cmd, &l, &r)

		if cmd == 1 {
			fmt.Fscan(rb, &k)
			Update(l+1, r+1, 1, n, 1, k)
		} else {
			fmt.Fprintln(wb, Query(l+1, r+1, 1, n, 1))
		}
	}
}

func Query(l, r, s, e, idx int) int {
	Propagate(s, e, idx)

	if r < s || e < l {
		return 0
	}

	if l <= s && e <= r {
		return tree[idx]
	}

	mid := (s + e) / 2

	return Query(l, r, s, mid, idx*2) ^ Query(l, r, mid+1, e, idx*2+1)
}

func Update(l, r, s, e, idx, k int) {
	Propagate(s, e, idx)

	if r < s || e < l {
		return
	}

	if l <= s && e <= r {
		lazy[idx] ^= k
		Propagate(s, e, idx)
		return
	}

	mid := (s + e) / 2
	Update(l, r, s, mid, idx*2, k)
	Update(l, r, mid+1, e, idx*2+1, k)
	tree[idx] = tree[idx*2] ^ tree[idx*2+1]
}

func Propagate(s, e, idx int) {
	if lazy[idx] != 0 {
		if (e-s+1)%2 == 1 {
			tree[idx] ^= lazy[idx]
		}

		if s != e {
			lazy[idx*2] ^= lazy[idx]
			lazy[idx*2+1] ^= lazy[idx]
		}
		lazy[idx] = 0
	}
}
