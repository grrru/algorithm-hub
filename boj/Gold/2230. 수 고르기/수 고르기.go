package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

var n int
var m int
var arr []int

func main() {

	reader := bufio.NewReader(os.Stdin)

	fmt.Scan(&n, &m)

	arr = make([]int, n)

	idx := 0
	for idx < n {
		line, _ := reader.ReadString('\n')
		nums := strings.Fields(line)
		for _, s := range nums {
			val, _ := strconv.Atoi(s)
			arr[idx] = val
			idx++
		}
	}

	sort.Ints(arr)

	res := math.MaxInt

	l := 0
	r := 1

	for l < n && r < n {
		diff := arr[r] - arr[l]

		if diff < m {
			r++
		} else {
			if diff < res {
				res = diff
			}
			l++
		}
	}

	fmt.Println(res)
}
