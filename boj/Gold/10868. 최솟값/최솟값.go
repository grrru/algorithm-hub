package main

import (
	"bufio"
	"fmt"
	"os"
)

const INF = 1_000_000_001

var (
	n, T int
	arr  []int
	tree []int
)

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func buildTree(s, e, i int) {
	if s == e {
		tree[i] = arr[s]
		return
	}
	mid := (s + e) / 2
	buildTree(s, mid, i*2)
	buildTree(mid+1, e, i*2+1)
	tree[i] = min(tree[i*2], tree[i*2+1])
}

func getTree(gs, ge, s, e, i int) int {
	if ge < s || e < gs {
		return INF
	}
	if gs <= s && e <= ge {
		return tree[i]
	}
	mid := (s + e) / 2
	a1 := getTree(gs, ge, s, mid, i*2)
	a2 := getTree(gs, ge, mid+1, e, i*2+1)
	return min(a1, a2)
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	fmt.Fscan(reader, &n, &T)

	arr = make([]int, n+1)
	for i := 1; i <= n; i++ {
		fmt.Fscan(reader, &arr[i])
	}

	tree = make([]int, 4*(n+1))
	buildTree(1, n, 1)

	for ; T > 0; T-- {
		var gs, ge int
		fmt.Fscan(reader, &gs, &ge)
		if gs > ge {
			gs, ge = ge, gs
		}
		fmt.Fprintln(writer, getTree(gs, ge, 1, n, 1))
	}
}
