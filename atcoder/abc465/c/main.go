package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
	"strconv"
)

var (
	n int
	s string

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

type Deque struct {
	li         *list.List
	isReversed bool
}

func (dq *Deque) append(x int, changed bool) {
	if dq.isReversed {
		dq.li.PushFront(x)
	} else {
		dq.li.PushBack(x)
	}

	if changed {
		dq.isReversed = !dq.isReversed
	}
}

func main() {
	defer wb.Flush()

	fmt.Fscan(rb, &n, &s)

	dq := buildQ()

	for i, c := range s {
		x := i + 1
		if c == 'o' {
			dq.append(x, true)
		} else {
			dq.append(x, false)
		}
	}

	if !dq.isReversed {
		for i := dq.li.Front(); i != nil; i = i.Next() {
			x := i.Value.(int)
			wb.WriteString(strconv.Itoa(x))
			wb.WriteRune(' ')
		}
	} else {
		for i := dq.li.Back(); i != nil; i = i.Prev() {
			x := i.Value.(int)
			wb.WriteString(strconv.Itoa(x))
			wb.WriteRune(' ')
		}
	}
}

func buildQ() *Deque {
	li := list.New()
	dq := &Deque{li, false}

	return dq
}
