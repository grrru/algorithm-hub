package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	N     int
	Q     int
	graph []int

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

func union(u, v int) {
	up := find(u)
	vp := find(v)

	if up == vp {
		return
	}

	graph[up] = vp
}

func find(u int) int {
	if graph[u] == u {
		return u
	}

	graph[u] = find(graph[u])
	return graph[u]
}

func isConnected(u, v int) bool {
	return find(u) == find(v)
}

func main() {
	defer wb.Flush()

	fmt.Fscan(rb, &N, &Q)
	graph = make([]int, N)
	for i := range N {
		graph[i] = i
	}

	var cmd, u, v int
	for range Q {
		fmt.Fscan(rb, &cmd, &u, &v)

		switch cmd {
		case 0:
			union(u, v)
		case 1:
			if isConnected(u, v) {
				wb.WriteString("1")
			} else {
				wb.WriteString("0")
			}
			wb.WriteRune('\n')
		}
	}
}
