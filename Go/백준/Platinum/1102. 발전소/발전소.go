package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	n    int
	ans  int
	p    int
	dp   []int
	cost [][]int
	rb   = bufio.NewReader(os.Stdin)
)

const IMPOSIBLE = 1_234_567_890
const NOT_VISITED = -1

func main() {
	fmt.Fscan(rb, &n)
	cost = make([][]int, n+1)
	for i := 1; i <= n; i++ {
		cost[i] = make([]int, n+1)
		for j := 1; j <= n; j++ {
			fmt.Fscan(rb, &cost[i][j])
		}
	}

	dp = make([]int, 1<<(n+1))
	for i := 0; i < len(dp); i++ {
		dp[i] = NOT_VISITED
	}

	var str string
	fmt.Fscan(rb, &str)
	fmt.Fscan(rb, &p)
	initPos := 0
	cnt := 0
	for i, v := range str {
		if string(v) == "Y" {
			initPos |= 1 << (i + 1)
			cnt++
		}
	}
	if cnt >= p {
		fmt.Println(0)
		return
	}
	dp[initPos] = 0

	ans = IMPOSIBLE
	comb(0, 1, 0)

	if ans == IMPOSIBLE {
		ans = -1
	}
	fmt.Println(ans)
}

func comb(num, cur, cnt int) {
	if cnt == p {
		ans = min(ans, makeDp(num))
		return
	}

	if cur == n+1 {
		return
	}

	comb(num|(1<<cur), cur+1, cnt+1)
	comb(num, cur+1, cnt)
}

func makeDp(num int) int {
	if dp[num] != NOT_VISITED {
		return dp[num]
	}

	dp[num] = IMPOSIBLE

	for i := 1; i <= n; i++ {
		if !haveElec(num, i) {
			continue
		}

		for j := 1; j <= n; j++ {
			if i == j || !haveElec(num, j) {
				continue
			}

			dp[num] = min(makeDp(num&^(1<<i))+cost[j][i], dp[num])
		}
	}

	return dp[num]
}

func haveElec(src, dist int) bool {
	return src&(1<<dist) > 0
}
