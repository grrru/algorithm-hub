package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n    int
	m    int
	tree []int
	lazy []int
)

var (
	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func main() {
	defer wb.Flush()

	fmt.Fscan(rb, &n, &m)
	initTree()

	var cmd, l, r int
	for m > 0 {
		m--
		fmt.Fscan(rb, &cmd, &l, &r)
		command(cmd, l, r)
	}
}

func initTree() {
	size := 4 * (n + 1)
	tree = make([]int, size)
	lazy = make([]int, size)
}

func command(cmd, l, r int) {
	if cmd == 0 {
		clickSwitch(l, r)
	} else {
		checkSwitch(l, r)
	}
}

func clickSwitch(l, r int) {
	updateTree(l, r, 1, n, 1)
}

func checkSwitch(l, r int) {
	fmt.Fprintln(wb, query(l, r, 1, n, 1))
}

func query(l, r, s, e, idx int) int {
	propagate(s, e, idx)

	if e < l || r < s {
		return 0
	}

	if l <= s && e <= r {
		return tree[idx]
	}

	mid := (s + e) / 2
	return query(l, r, s, mid, idx*2) + query(l, r, mid+1, e, idx*2+1)
}

func updateTree(l, r, s, e, idx int) {
	propagate(s, e, idx)

	if e < l || r < s {
		return
	}

	if l <= s && e <= r {
		lazy[idx]++
		propagate(s, e, idx)
		return
	}

	mid := (s + e) / 2

	updateTree(l, r, s, mid, idx*2)
	updateTree(l, r, mid+1, e, idx*2+1)
	tree[idx] = tree[idx*2] + tree[idx*2+1]
}

// lazy의 값을 tree에 반영하고 한 단계 아래의 자식 lazy 노드에 전파하는 함수
func propagate(s, e, idx int) {
	if lazy[idx]%2 == 1 {
		tree[idx] = e - s + 1 - tree[idx]
		if s != e {
			lazy[idx*2] += lazy[idx]
			lazy[idx*2+1] += lazy[idx]
		}

		lazy[idx] = 0
	}
}
