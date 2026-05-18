package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	nums	[]int
	dp		[101][21]int64
)

func main() {
	reader := bufio.NewReader(os.Stdin)

	nLine, _ := reader.ReadString('\n')
	n, _ := strconv.Atoi(strings.TrimSpace(nLine))
	_ = n

	line, _ := reader.ReadString('\n')
	parts := strings.Fields(line)

	for _, p := range parts {
		num, _ := strconv.Atoi(p)
		nums = append(nums, num)
	}

	for i := 0; i < 101; i++ {
		for j := 0; j <= 20; j++ {
			dp[i][j] = -1;
		}
	}

	result := dfs(0, nums[0])
	fmt.Println(result)
}

func dfs(pos int, sum int) int64 {
	if sum < 0 || sum > 20 {
		return 0
	}

	if pos == len(nums) - 2 {
		if (sum == nums[len(nums) - 1]) {
			return 1
		}

		return 0
	}

	if (dp[pos][sum] != -1) {
		return dp[pos][sum]
	}

	next := nums[pos + 1]
	dp[pos][sum] = dfs(pos + 1, sum + next) + dfs(pos + 1, sum - next)

	return dp[pos][sum]
}