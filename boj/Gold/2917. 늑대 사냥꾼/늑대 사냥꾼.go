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
	arr  [][]rune
	dist [][]int

	di = []int{1, -1, 0, 0}
	dj = []int{0, 0, 1, -1}

	V Point
	J Point
)

type Point struct {
	x, y int
}

type Queue struct {
	list []Point
}

func (q *Queue) Offer(p Point) {
	q.list = append(q.list, p)
}

func (q *Queue) Poll() Point {
	p := q.list[0]
	q.list = q.list[1:]
	return p
}

func (q *Queue) IsEmpty() bool {
	return len(q.list) == 0
}

func makeDist(queue *Queue) {
	for !queue.IsEmpty() {
		cur := queue.Poll()

		for d := 0; d < 4; d++ {
			ni := cur.x + di[d]
			nj := cur.y + dj[d]

			if !(ni >= 0 && ni < n && nj >= 0 && nj < m) {
				continue
			}

			if dist[ni][nj] != -1 {
				continue
			}

			dist[ni][nj] = dist[cur.x][cur.y] + 1
			queue.Offer(Point{ni, nj})
		}
	}
}

func check(mid int) bool {

	if dist[J.x][J.y] < mid || dist[V.x][V.y] < mid {
		return false
	}

	queue := Queue{}
	queue.Offer(V)

	visited := make([][]bool, n)
	for j := range visited {
		visited[j] = make([]bool, m)
	}
	visited[V.x][V.y] = true

	for !queue.IsEmpty() {
		cur := queue.Poll()

		for d := 0; d < 4; d++ {
			ni := cur.x + di[d]
			nj := cur.y + dj[d]

			if !(ni >= 0 && ni < n && nj >= 0 && nj < m) {
				continue
			}

			if visited[ni][nj] {
				continue
			}

			if ni == J.x && nj == J.y {
				return true
			}

			visited[ni][nj] = true
			if dist[ni][nj] < mid {
				continue
			}

			queue.Offer(Point{ni, nj})
		}
	}

	return false
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	line, _ := reader.ReadString('\n')
	parts := strings.Fields(line)
	n, _ = strconv.Atoi(parts[0])
	m, _ = strconv.Atoi(parts[1])

	arr = make([][]rune, n)
	dist = make([][]int, n)
	queue := &Queue{}

	for i := range arr {
		line, _ := reader.ReadString('\n')
		arr[i] = []rune(strings.TrimSpace(line))

		dist[i] = make([]int, m)

		for j := 0; j < m; j++ {

			if arr[i][j] == '+' {
				queue.Offer(Point{i, j})
				dist[i][j] = 0
			} else {
				dist[i][j] = -1

				if arr[i][j] == 'V' {
					V = Point{i, j}
				} else if arr[i][j] == 'J' {
					J = Point{i, j}
				}
			}
		}
	}

	makeDist(queue)

	l := 0
	r := 1000

	ans := 1000

	for l <= r {
		mid := (l + r) / 2

		if check(mid) {
			ans = mid
			l = mid + 1
		} else {
			r = mid - 1
		}
	}

	fmt.Fprintln(writer, ans)
}
