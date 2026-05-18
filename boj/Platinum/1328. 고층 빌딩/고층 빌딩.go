package main

import (
	"fmt"
)

var (
	n int
	l int
	r int

	comb [101][101]int64
	dp   [101][101]int64
	fact [101]int64
)

const (
	MOD         = 1000000007
	NOT_VISITED = -1
)

func getFact(n int) int64 {
	if fact[n] != NOT_VISITED {
		return fact[n]
	}

	if n == 1 || n == 0 {
		fact[n] = 1
		return fact[n]
	}

	fact[n] = getFact(n-1) * int64(n)
	fact[n] %= MOD

	return fact[n]
}

func getComb(n int, r int) int64 {
	if comb[n][r] != NOT_VISITED {
		return comb[n][r]
	}

	if n == r || r == 0 {
		comb[n][r] = 1
		return 1
	}

	if r == 1 {
		comb[n][r] = int64(n)
		return comb[n][r]
	}

	comb[n][r] = (getComb(n-1, r-1) + getComb(n-1, r)) % MOD

	return comb[n][r]
}

func getDp(n int, cnt int) int64 {

	if dp[n][cnt] != NOT_VISITED {
		return dp[n][cnt]
	}

	if n < cnt {
		dp[n][cnt] = 0
		return dp[n][cnt]
	} else if n == cnt {
		dp[n][cnt] = 1
		return dp[n][cnt]
	}

	if cnt == 0 {
		dp[n][cnt] = 0
		return dp[n][cnt]
	}

	dp[n][cnt] = 0
	for i := n; i >= cnt; i-- {
		dp[n][cnt] += getComb(n-1, n-i) * getDp(i-1, cnt-1) % MOD * getFact(n-i) % MOD
		dp[n][cnt] %= MOD
	}

	return dp[n][cnt]
}

func init() {
	for i := 0; i <= 100; i++ {
		for j := 0; j <= 100; j++ {
			comb[i][j] = NOT_VISITED
			dp[i][j] = NOT_VISITED
		}
		fact[i] = -1
	}
}

func main() {
	fmt.Scanln(&n, &l, &r)

	var ans int64

	for i := 1; i <= n; i++ {
		res := getDp(i-1, l-1) * getDp(n-i, r-1) % MOD

		// fmt.Printf("getDp(%d, %d) = %d\t", i-1, l, getDp(i-1, l))
		// fmt.Printf("getDp(%d, %d) = %d\n", n-i, r, getDp(n-i, r))

		res *= getComb(n-1, n-i)
		res %= MOD

		ans += res
		ans %= MOD
	}
	fmt.Println(ans)
}
