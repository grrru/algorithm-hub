package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n    int
	m    int
	k    int
	arr  []int64
	tree []int64
	lazy []int64

	reader *bufio.Reader
	writer *bufio.Writer
)

func init() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
}

func buildTree(as, ae, i int) {
	if as == ae {
		tree[i] = arr[as]
		return
	}

	mid := (as + ae) / 2
	buildTree(as, mid, i*2)
	buildTree(mid+1, ae, i*2+1)

	tree[i] = tree[i*2] + tree[i*2+1]
}

func getTree(gs, ge, as, ae, i int) int64 {
	if ge < as || ae < gs {
		return 0
	}

	if gs <= as && ae <= ge {
		return tree[i]
	}

	pushLazy(as, ae, i)
	mid := (as + ae) / 2

	return getTree(gs, ge, as, mid, i*2) + getTree(gs, ge, mid+1, ae, i*2+1)
}

func updateTree(gs, ge, as, ae, i, d int) {
	if ge < as || ae < gs {
		return
	}

	if gs <= as && ae <= ge {
		applyLazy(as, ae, i, d)
		return
	}

	pushLazy(as, ae, i)

	mid := (as + ae) / 2
	updateTree(gs, ge, as, mid, i*2, d)
	updateTree(gs, ge, mid+1, ae, i*2+1, d)
	tree[i] = tree[i*2] + tree[i*2+1]
}

func applyLazy(as, ae, i, d int) {
	tree[i] += int64(d * (ae - as + 1))
	lazy[i] += int64(d)
}

func pushLazy(as, ae, i int) {
	if lazy[i] == 0 || as == ae {
		return
	}

	mid := (as + ae) / 2
	applyLazy(as, mid, i*2, int(lazy[i]))
	applyLazy(mid+1, ae, i*2+1, int(lazy[i]))
	lazy[i] = 0
}

func main() {
	defer writer.Flush()
	fmt.Fscan(reader, &n, &m, &k)
	arr = make([]int64, n+1)
	for i := 1; i <= n; i++ {
		fmt.Fscan(reader, &arr[i])
	}

	tree = make([]int64, 4*(n+1))
	lazy = make([]int64, 4*(n+1))
	buildTree(1, n, 1)

	var a, b, c, d int
	for m+k > 0 {
		fmt.Fscan(reader, &a)

		if a == 1 {
			fmt.Fscan(reader, &b, &c, &d)
			updateTree(b, c, 1, n, 1, d)
			m--
		} else {
			fmt.Fscan(reader, &b, &c)
			fmt.Fprintln(writer, getTree(b, c, 1, n, 1))
			k--
		}
	}
}
