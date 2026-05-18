package main

import (
	"bufio"
	"fmt"
	"os"
	"slices"
	"strings"
)

var (
	n   int
	arr []string

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func main() {
	defer wb.Flush()
	fmt.Fscan(rb, &n)
	arr = make([]string, n)

	isValid := false
	for i := range n {
		fmt.Fscan(rb, &arr[i])
		isValid = isValid || !strings.HasPrefix(arr[i], "0")
	}
	if !isValid {
		wb.WriteString("INVALID")
		return
	}

	wb.WriteString(solve(arr))
}

func solve(arr []string) string {
	var ans string

	slices.SortFunc(arr, func(a, b string) int {
		if a+b < b+a {
			return -1
		} else if b+a < a+b {
			return 1
		} else {
			return 0
		}
	})

	for i := range n {
		if strings.HasPrefix(arr[i], "0") {
			continue
		}

		var sb strings.Builder
		sb.WriteString(arr[i])
		for j := range arr {
			if i == j {
				continue
			}
			sb.WriteString(arr[j])
		}

		cur := sb.String()
		if ans == "" || cur < ans {
			ans = cur
		}
	}

	return ans
}