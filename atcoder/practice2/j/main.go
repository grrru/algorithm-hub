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

func Replace(v, idx int) {
	doReplace(1, n, 1, v, idx)
}

func doReplace(s, e, i, v, idx int) {
	if e < idx || idx < s {
		return
	}

	if s == e {
		tree[i] = v
		return
	}

	mid := (s + e) / 2
	doReplace(s, mid, i*2, v, idx)
	doReplace(mid+1, e, i*2+1, v, idx)

	tree[i] = max(tree[i*2], tree[i*2+1])
}

func GetMax(l, r int) int {
	return doGetMax(1, n, 1, l, r)
}

func doGetMax(s, e, i, l, r int) int {
	if r < s || e < l {
		return 0
	}

	if l <= s && e <= r {
		return tree[i]
	}

	if s == e {
		return tree[i]
	}

	mid := (s + e) / 2

	return max(doGetMax(s, mid, i*2, l, r), doGetMax(mid+1, e, i*2+1, l, r))
}

func GetMaxLeft(idx, v int) int {
	return doGetMaxLeft(1, n, 1, idx, v)
}

func doGetMaxLeft(s, e, i, idx, v int) int {
	if e < idx {
		return n + 1
	}

	if tree[i] < v {
		return n + 1
	}

	if s == e {
		return s
	}

	mid := (s + e) / 2

	if left := doGetMaxLeft(s, mid, i*2, idx, v); left != n+1 {
		return left
	}

	return doGetMaxLeft(mid+1, e, i*2+1, idx, v)
}

func main() {
	defer wb.Flush()

	fmt.Fscan(rb, &n, &q)
	tree = make([]int, 4*n+1)

	var v int
	for i := range n {
		fmt.Fscan(rb, &v)
		Replace(v, i+1)
	}

	var cmd, l, r, x int
	for range q {
		fmt.Fscan(rb, &cmd)

		switch cmd {
		case 1:
			fmt.Fscan(rb, &x, &v)
			Replace(v, x)
		case 2:
			fmt.Fscan(rb, &l, &r)
			wb.WriteString(strconv.Itoa(GetMax(l, r)))
			wb.WriteRune('\n')
		case 3:
			fmt.Fscan(rb, &x, &v)
			wb.WriteString(strconv.Itoa(GetMaxLeft(x, v)))
			wb.WriteRune('\n')
		}
	}
}
