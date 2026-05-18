package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	n     int
	count [1002]int
	ans   []int
)

func main() {
	fmt.Scan(&n)
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	line, _ := reader.ReadString('\n')
	fields := strings.Fields(line)

	for _, str := range fields {
		i, _ := strconv.Atoi(str)
		count[i]++
	}

	prev := -2

	for n > 0 {
		for cur := 0; cur <= 1000; cur++ {
			if count[cur] == 0 || cur == prev+1 {
				continue
			}

			if n == count[cur+1]+count[cur] && count[cur] != n {
				continue
			}

			count[cur]--
			n--
			prev = cur

			ans = append(ans, cur)
			break
		}
	}

	for i := 0; i < len(ans); i++ {
		writer.WriteString(strconv.Itoa(ans[i]))
		if i != len(ans)-1 {
			writer.WriteString(" ")
		}
	}
}
