package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	n    int
	m    int
	arr  []int
	tree []int
)

func buildTree(s int, e int, i int) {
	if s == e {
		tree[i] = s
		return
	}

	mid := (s + e) / 2

	buildTree(s, mid, i*2)
	buildTree(mid+1, e, i*2+1)

	if arr[tree[i*2]] > arr[tree[i*2+1]] {
		tree[i] = tree[i*2]
	} else {
		tree[i] = tree[i*2+1]
	}
}

func getTree(gs int, ge int, as int, ae int, i int) int {
	if gs > ae || ge < as {
		return 0
	}

	if gs <= as && ae <= ge {
		return tree[i]
	}

	mid := (as + ae) / 2
	idx1 := getTree(gs, ge, as, mid, i*2)
	idx2 := getTree(gs, ge, mid+1, ae, i*2+1)

	if arr[idx1] > arr[idx2] {
		return idx1
	} else {
		return idx2
	}
}

func main() {
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	fmt.Scanln(&n, &m)
	reader := bufio.NewReader(os.Stdin)

	line, _ := reader.ReadString('\n')

	fields := strings.Fields(line)
	arr = make([]int, n+1)
	for i := 1; i <= n; i++ {
		arr[i], _ = strconv.Atoi(fields[i-1])
	}

	tree = make([]int, 4*n)
	buildTree(1, n, 1)

	for i := m; i <= n-m+1; i++ {
		writer.WriteString(strconv.Itoa(arr[getTree(i-m+1, i+m-1, 1, n, 1)]))

		if i != n-m+1 {
			writer.WriteString(" ")
		}
	}
}
