package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

var (
	n    int
	q    int
	arr  []int
	tree []int
	base int

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func Update(v, c int) {
	doUpdate(0, q, 1, v, c)
}

func doUpdate(s, e, i, v, c int) {
	if e < v || v < s {
		return
	}

	tree[i] += c

	if s == e {
		return
	}

	mid := (s + e) / 2

	doUpdate(s, mid, i*2, v, c)
	doUpdate(mid+1, e, i*2+1, v, c)
}

func Query(v int) int {
	return doQuery(0, q, 1, base+v)
}

func doQuery(s, e, i, v int) int {
	if e < v {
		return 0
	}

	if v <= s {
		return tree[i]
	}

	mid := (s + e) / 2

	return doQuery(s, mid, i*2, v) + doQuery(mid+1, e, i*2+1, v)
}

func main() {
	defer wb.Flush()

	fmt.Fscan(rb, &n, &q)

	arr = make([]int, n+1)
	tree = make([]int, 4*(q+1))

	Update(0, n)

	var cmd, x int
	for range q {
		fmt.Fscan(rb, &cmd, &x)

		switch cmd {
		case 1:
			Update(arr[x], -1)
			arr[x]++
			Update(arr[x], 1)

			if Query(1) == n {
				base++
			}
		case 2:
			wb.WriteString(strconv.Itoa(Query(x)))
			wb.WriteRune('\n')
		}
	}
}
