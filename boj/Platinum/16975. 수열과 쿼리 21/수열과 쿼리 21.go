package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n    int
	m    int
	arr  []int
	tree []int
)

func Update(g, v, as, ae, i int) {
	if g < as || ae < g {
		return
	}

	if as == ae {
		tree[i] += v
		return
	}

	mid := (as + ae) / 2
	Update(g, v, as, mid, i*2)
	Update(g, v, mid+1, ae, i*2+1)

	tree[i] += v
}

func Query(gs, ge, as, ae, i int) int {
	if ge < as || ae < gs {
		return 0
	}

	if gs <= as && ae <= ge {
		return tree[i]
	}

	mid := (as + ae) / 2
	return Query(gs, ge, as, mid, i*2) + Query(gs, ge, mid+1, ae, i*2+1)
}

func main() {
	rb := bufio.NewReader(os.Stdin)
	wb := bufio.NewWriter(os.Stdout)
	defer wb.Flush()

	fmt.Fscan(rb, &n)
	arr = make([]int, n+1)
	for i := 1; i <= n; i++ {
		fmt.Fscan(rb, &arr[i])
	}

	tree = make([]int, 4*n+1)

	fmt.Fscan(rb, &m)

	var cmd, s, e, v int
	for m > 0 {
		m--
		fmt.Fscan(rb, &cmd)
		if cmd == 1 {
			fmt.Fscan(rb, &s, &e, &v)
			Update(s, v, 1, n, 1)
			Update(e+1, -v, 1, n, 1)
		} else {
			fmt.Fscan(rb, &v)
			res := Query(1, v, 1, n, 1) + arr[v]

			fmt.Fprintln(wb, res)
		}
	}
}
