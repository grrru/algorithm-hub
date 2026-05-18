package main

import (
	"fmt"
	"math"
	"sort"
	"strconv"
)

var (
	n   int
	arr []int
)

type Queue struct {
	list []int
}

func (q *Queue) isEmpty() bool {
	return len(q.list) == 0
}

func (q *Queue) poll() int {

	r := q.list[0]
	q.list = q.list[1:]
	return r
}

func (q *Queue) offer(v int) {
	q.list = append(q.list, v)
}

func main() {
	fmt.Scan(&n)

	var q *Queue = new(Queue)

	for i := 0; i < 10; i++ {
		q.offer(i)
	}

	for !q.isEmpty() {
		cur := q.poll()
		arr = append(arr, cur)

		s := strconv.Itoa(cur)
		first := int(s[0] - '0')

		cnt := len(s)

		h := int(math.Pow(10, float64(cnt)))

		for i := int(first) + 1; i <= 9; i++ {
			q.offer(cur + i*h)
		}
	}

	if n-1 >= len(arr) {
		fmt.Println(-1)
	} else {
		sort.Ints(arr)
		fmt.Println(arr[n-1])
	}
}
