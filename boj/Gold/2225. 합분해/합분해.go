package main

import "fmt"

var n int32
var k int32
var dp [][]int32

const MOD int32 = 1_000_000_000
const NOT_VISITED int32 = -1

func makeDp(num int32, cnt int32) int32 {

	if num == 0 {
		return 1
	}

	if cnt == 0 {
		return 0
	}

	if dp[num][cnt] != NOT_VISITED {
		return dp[num][cnt]
	}

	var res int32 = 0

	for i := int32(0); i <= n; i++ {
		if num < i {
			break
		}

		res += makeDp(num-i, cnt-1)
		res %= MOD
	}

	dp[num][cnt] = res

	return dp[num][cnt]
}

func main() {
	fmt.Scan(&n, &k)

	dp = make([][]int32, n+1)
	for i := range dp {
		dp[i] = make([]int32, k+1)

		for j := range dp[i] {
			dp[i][j] = NOT_VISITED
		}
	}

	fmt.Println(makeDp(n, k))
}
