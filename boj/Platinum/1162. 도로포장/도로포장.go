package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
)

var (
	n       int
	m       int
	k       int
	dp      [][]int64
	distMap map[int]map[int]int64

	rb = bufio.NewReader(os.Stdin)
)

const INF int64 = 1 << 60

type State struct {
	dist int64
	node int
	cnt  int
}

type PriorityQueue []State

func (pq PriorityQueue) Len() int           { return len(pq) }
func (pq PriorityQueue) Less(i, j int) bool { return pq[i].dist < pq[j].dist }
func (pq PriorityQueue) Swap(i, j int)      { pq[i], pq[j] = pq[j], pq[i] }
func (pq *PriorityQueue) Push(x any)        { *pq = append(*pq, x.(State)) }
func (pq *PriorityQueue) Pop() any {
	old := *pq
	n := len(old)
	x := old[n-1]
	*pq = old[:n-1]
	return x
}

func main() {
	fmt.Fscan(rb, &n, &m, &k)

	dp = make([][]int64, n+1)
	for i := range dp {
		dp[i] = make([]int64, k+1)
		for j := range dp[i] {
			dp[i][j] = INF
		}
	}

	distMap = make(map[int]map[int]int64)

	var a, b int
	var di int64
	for range m {
		fmt.Fscan(rb, &a, &b, &di)
		PutDist(a, b, di)
		PutDist(b, a, di)
	}

	pq := &PriorityQueue{}
	heap.Init(pq)
	heap.Push(pq, State{0, 1, 0})

	for pq.Len() != 0 {
		cur := heap.Pop(pq).(State)

		if dp[cur.node][cur.cnt] < cur.dist {
			continue
		}

		dp[cur.node][cur.cnt] = cur.dist

		nextMap := distMap[cur.node]

		for nextNode, dist := range nextMap {
			if dp[nextNode][cur.cnt] > dp[cur.node][cur.cnt]+dist {
				dp[nextNode][cur.cnt] = dp[cur.node][cur.cnt] + dist
				heap.Push(pq, State{dp[nextNode][cur.cnt], nextNode, cur.cnt})
			}

			if cur.cnt == k {
				continue
			}

			if dp[nextNode][cur.cnt+1] > dp[cur.node][cur.cnt] {
				dp[nextNode][cur.cnt+1] = dp[cur.node][cur.cnt]
				heap.Push(pq, State{dp[nextNode][cur.cnt+1], nextNode, cur.cnt + 1})
			}
		}
	}

	ans := INF
	for i := range dp[n] {
		ans = min(ans, dp[n][i])
	}

	fmt.Println(ans)
}

func PutDist(a, b int, di int64) {
	if inner, ok := distMap[a]; ok {
		if dist, ok := inner[b]; ok {
			if dist < di {
				return
			}
		}
	} else {
		distMap[a] = make(map[int]int64)
	}

	distMap[a][b] = di
}