package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
)

var (
	n   int
	arr [][2]int
	rb  = bufio.NewReader(os.Stdin)
)

func main() {
	fmt.Fscan(rb, &n)

	arr = make([][2]int, n)
	for i := range arr {
		fmt.Fscan(rb, &arr[i][0], &arr[i][1])
	}

	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] == arr[j][0] {
			return arr[i][1] < arr[j][1]
		}
		return arr[i][0] < arr[j][0]
	})

	fmt.Println(divide(0, n-1))
}

func divide(l, r int) int {
	if r-l <= 2 {
		return compareThree(l, r)
	}

	mid := (l + r) / 2
	res := min(divide(l, mid), divide(mid+1, r))

	if con := conquer(l, r, mid, res); res > con {
		res = con
	}

	return res
}

func conquer(l, r, mid, max int) int {
	i := l
	j := mid + 1

	for i <= mid && j <= r {
		if (arr[i][0]-arr[j][0])*(arr[i][0]-arr[j][0]) >= max {
			i++
		} else {
			for k := i; k <= mid; k++ {
				if cand := distance(k, j); max > cand {
					max = cand
				}
			}
			j++
		}
	}

	return max
}

func compareThree(l, r int) int {
	res := math.MaxInt

	for i := l; i < r; i++ {
		for j := i + 1; j <= r; j++ {
			if cand := distance(i, j); res > cand {
				res = cand
			}
		}
	}

	return res
}

func distance(i, j int) int {
	return (arr[j][0]-arr[i][0])*(arr[j][0]-arr[i][0]) + (arr[j][1]-arr[i][1])*(arr[j][1]-arr[i][1])
}
