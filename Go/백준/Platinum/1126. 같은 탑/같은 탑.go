package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n   int
	arr []int
	dp  [][]int

	rb = bufio.NewReader(os.Stdin)
)

const IMPOSSIBE = -1

func main() {
	fmt.Fscan(rb, &n)
	arr = make([]int, n+1)
	dp = make([][]int, n+1)

	maxHeight := 0
	for i := range n {
		fmt.Fscan(rb, &arr[i+1])
		maxHeight += arr[i+1]
		dp[i+1] = make([]int, 500001)
		for j := range dp[i+1] {
			dp[i+1][j] = IMPOSSIBE
		}
	}

	dp[1][arr[1]] = arr[1]
	dp[1][0] = 0

	for i := 2; i <= n; i++ {
		h := arr[i]
		for diff := range maxHeight + 1 {
			if dp[i-1][diff] == IMPOSSIBE {
				continue
			}

			dp[i][diff+h] = max(dp[i][diff+h], dp[i-1][diff]+h)

			nextDiff := diff - h
			if nextDiff < 0 {
				nextDiff = -nextDiff
				dp[i][nextDiff] = max(dp[i][nextDiff], dp[i-1][diff]+nextDiff)
			} else {
				dp[i][nextDiff] = max(dp[i][nextDiff], dp[i-1][diff])
			}

			dp[i][diff] = max(dp[i][diff], dp[i-1][diff])
		}
	}

	if dp[n][0] == 0 {
		dp[n][0] = -1
	}
	fmt.Println(dp[n][0])
}
