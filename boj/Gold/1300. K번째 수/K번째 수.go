package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n  int
	k  int
	rb = bufio.NewReader(os.Stdin)
)

func main() {
	fmt.Fscan(rb, &n, &k)

	s := 1
	e := k

	fmt.Println(search(s, e))
}

func search(s, e int) int {
	var target int

	for s <= e {
		mid := (s + e) / 2
		if isMidGreaterorEqualThenKstNum(mid) {
			target = mid
			e = mid - 1
		} else {
			s = mid + 1
		}
	}

	return target
}

func isMidGreaterorEqualThenKstNum(mid int) bool {
	cnt := 0

	for i := 1; i <= n; i++ {
		cnt += min(n, mid/i)
		if cnt >= k {
			return true
		}
	}
	return false
}
