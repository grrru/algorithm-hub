package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

var (
	n        int
	athletes []int
	tree     []int

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func main() {
	defer wb.Flush()

	fmt.Fscan(rb, &n)
	athletes = make([]int, n+1)
	compress()

	tree = make([]int, 4*(n+1))

	for i := 1; i <= n; i++ {
		rank := query(athletes[i]+1, n, 1, n, 1) + 1
		fmt.Fprintln(wb, rank)
		update(athletes[i], 1, n, 1)
	}
}

func update(power, cs, ce, idx int) {
	if power < cs || ce < power {
		return
	}

	if cs == ce {
		tree[idx]++
		return
	}

	mid := (cs + ce) / 2

	update(power, cs, mid, idx*2)
	update(power, mid+1, ce, idx*2+1)
	tree[idx] = tree[idx*2] + tree[idx*2+1]
}

func query(s, e, cs, ce, idx int) int {
	if e < cs || ce < s {
		return 0
	}

	if s <= cs && ce <= e {
		return tree[idx]
	}

	mid := (cs + ce) / 2
	return query(s, e, cs, mid, idx*2) + query(s, e, mid+1, ce, idx*2+1)
}

func compress() {
	mapp := make(map[int]int, n+1)
	arr := make([]int, n+1)
	var v int

	for i := 1; i <= n; i++ {
		fmt.Fscan(rb, &v)
		mapp[v] = i
		arr[i] = v
	}
	sort.Ints(arr)
	for i := 1; i <= n; i++ {
		athletes[mapp[arr[i]]] = i
	}
}
