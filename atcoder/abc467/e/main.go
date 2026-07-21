package main

import (
	"bufio"
	"fmt"
	"os"
	"slices"
)

var (
	n   int
	m   int
	arr []int
	crr []int

	rb = bufio.NewReader(os.Stdin)
)

func main() {
	fmt.Fscan(rb, &n, &m)

	arr = make([]int, n+1)
	crr = make([]int, n)

	for i := range n {
		fmt.Fscan(rb, &arr[i+1])
	}

	for i := range n - 1 {
		var b int
		fmt.Fscan(rb, &b)
		crr[i+1] = mod(b - crr[i])
	}

	var total int
	cand := make([]int, 0, n)
	oddAt := make(map[int]int)
	evenAt := make(map[int]int)

	for i := 1; i <= n; i++ {
		d := mod(crr[i-1] - arr[i])
		total += d

		var s int
		if i%2 == 1 {
			s = mod(-d)
			oddAt[s]++
		} else {
			s = d
			evenAt[s]++
		}
		cand = append(cand, s)
	}
	slices.Sort(cand)

	curS := 0
	curTotal := total
	ans := total
	slope := int((n+1)/2 - n/2)

	for _, nextS := range cand {
		if nextS == curS {
			continue
		}

		curTotal = getTotal(nextS, curS, curTotal, slope, oddAt[nextS], evenAt[curS])
		curS = nextS
		if curTotal < ans {
			ans = curTotal
		}
	}

	fmt.Println(ans)
}

func getTotal(nextS, curS, curTotal, slope, nextOdd, curEven int) int {
	res := curTotal + slope*(nextS-curS)
	res += m * curEven
	res -= m * nextOdd
	return res
}

func mod(x int) int {
	x %= m
	if x < 0 {
		x += m
	}
	return x
}
