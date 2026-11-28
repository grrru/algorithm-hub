package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
)

var (
	x int

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

type Element struct {
	cur  int
	prev int
}

var save map[int]*Element = make(map[int]*Element)

func main() {
	defer wb.Flush()
	fmt.Fscan(rb, &x)

	if x == 1 {
		fmt.Println(0)
		fmt.Println(1)
		return
	}

	queue := list.New()
	queue.PushBack(&Element{x, 0})

	for queue.Len() != 0 {
		front := queue.Front()
		e := front.Value.(*Element)

		if e.cur%3 == 0 {
			if _, ok := save[e.cur/3]; !ok {
				ne := &Element{e.cur / 3, e.cur}
				if e.cur/3 == 1 {
					save[1] = ne
					break
				}
				queue.PushBack(ne)
				save[e.cur/3] = ne
			}
		}
		if e.cur%2 == 0 {
			if _, ok := save[e.cur/2]; !ok {
				ne := &Element{e.cur / 2, e.cur}
				if e.cur/2 == 1 {
					save[1] = ne
					break
				}
				queue.PushBack(ne)
				save[e.cur/2] = ne
			}
		}

		if _, ok := save[e.cur-1]; !ok {

			ne := &Element{e.cur - 1, e.cur}
			if e.cur-1 == 1 {
				save[1] = ne
				break
			}
			queue.PushBack(ne)
			save[e.cur-1] = ne
		}

		queue.Remove(front)
	}

	li := make([]int, 0)

	n := 1
	for n != x {
		li = append(li, n)
		n = save[n].prev
	}
	li = append(li, x)

	l := len(li) - 1

	fmt.Fprintln(wb, l)

	for i := l; i >= 0; i-- {
		fmt.Fprintf(wb, "%d ", li[i])
	}
}