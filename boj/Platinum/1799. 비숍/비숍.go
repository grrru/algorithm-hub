package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	n     int
	white int
	black int
	arr   [][]int

	leftUp  []bool
	rightUp []bool
)

func backtracking(idx int, cnt int) {
	if idx >= n*n {
		if idx != n*n {
			if white < cnt {
				white = cnt
			}
		} else {
			if black < cnt {
				black = cnt
			}
		}
		return
	}

	x := idx / n
	y := idx % n

	if canPlace(x, y) {
		leftUp[x-y+n-1] = true
		rightUp[x+y] = true

		i := idx + 1
		for (i/n+i%n)%2 != (x+y)%2 {
			i++
		}
		backtracking(i, cnt+1)

		leftUp[x-y+n-1] = false
		rightUp[x+y] = false
	}

	i := idx + 1
	for (i/n+i%n)%2 != (x+y)%2 {
		i++
	}
	backtracking(i, cnt)
}

func canPlace(x int, y int) bool {
	if arr[x][y] == 0 {
		return false
	}

	if leftUp[x-y+n-1] || rightUp[x+y] {
		return false
	}

	return true
}

func main() {
	reader := bufio.NewReader(os.Stdin)

	fmt.Scan(&n)
	arr = make([][]int, n)
	for i := 0; i < n; i++ {
		arr[i] = make([]int, n)

		line, _ := reader.ReadString('\n')
		line = strings.TrimSpace(line)
		fields := strings.Fields(line)

		for j := 0; j < n; j++ {
			arr[i][j], _ = strconv.Atoi(fields[j])
		}
	}

	leftUp = make([]bool, 2*n-1)
	rightUp = make([]bool, 2*n-1)

	backtracking(0, 0)
	backtracking(1, 0)

	fmt.Println(white + black)
}
