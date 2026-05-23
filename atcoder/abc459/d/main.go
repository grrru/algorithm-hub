package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"strings"
)

var (
	T int

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

const (
	yes = "Yes"
	no  = "No"
)

type item struct {
	alpha rune
	cnt   int
}

type PriorityQueue []item

func (pq *PriorityQueue) Push(x any) {
	it := x.(item)
	if it.cnt == 0 {
		return
	}
	*pq = append(*pq, it)
}
func (pq *PriorityQueue) Pop() any {
	old := *pq
	n := old.Len()
	x := old[n-1]
	*pq = old[:n-1]
	return x
}
func (pq PriorityQueue) Len() int           { return len(pq) }
func (pq PriorityQueue) Less(i, j int) bool { return pq[i].cnt > pq[j].cnt }
func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func main() {
	defer wb.Flush()
	fmt.Fscan(rb, &T)

	var str string
	pq := PriorityQueue{}

	for range T {
		fmt.Fscan(rb, &str)
		if ans, can := solve(pq, str); !can {
			wb.WriteString(no)
			wb.WriteRune('\n')
		} else {
			wb.WriteString(yes)
			wb.WriteRune('\n')
			wb.WriteString(ans)
			wb.WriteRune('\n')
		}
	}
}

func solve(pq PriorityQueue, str string) (string, bool) {
	count := make([]int, 26)
	heap.Init(&pq)
	sb := strings.Builder{}

	for i := range str {
		count[str[i]-'a']++
	}

	for i := range 26 {
		if count[i] == 0 {
			continue
		}

		heap.Push(&pq, item{rune(i + 'a'), count[i]})
	}

	var last rune
	for pq.Len() != 0 {
		item1 := heap.Pop(&pq).(item)
		if pq.Len() != 0 {
			item2 := heap.Pop(&pq).(item)

			if item1.alpha == last {
				sb.WriteRune(item2.alpha)
				sb.WriteRune(item1.alpha)
				last = item1.alpha
			} else {
				sb.WriteRune(item1.alpha)
				sb.WriteRune(item2.alpha)
				last = item2.alpha
			}

			item1.cnt--
			item2.cnt--
			heap.Push(&pq, item1)
			heap.Push(&pq, item2)

			continue
		}

		if last == item1.alpha {
			return "", false
		}

		sb.WriteRune(item1.alpha)
		item1.cnt--
		heap.Push(&pq, item1)
		last = item1.alpha
	}

	return sb.String(), true
}
