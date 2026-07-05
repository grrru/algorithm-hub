package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

const ansMod int = 998244353

var (
	T      int
	n, m   int
	modMap map[int]*Mod

	rb = bufio.NewReader(os.Stdin)
	wb = bufio.NewWriter(os.Stdout)
)

type Mod struct {
	mod int
	cnt int
}

func main() {
	defer wb.Flush()

	fmt.Fscan(rb, &T)
	modMap = make(map[int]*Mod)

	for range T {
		fmt.Fscan(rb, &n, &m)

		var ans int
		var d int

		x := n
		for x != 0 {
			x /= 10
			d++
		}

		x = 1
		for i := range d {
			x *= 10

			modMap[i+1] = &Mod{
				mod: (x - 1) % m,
				cnt: x / 10 * 9,
			}

			if i+1 == d {
				modMap[i+1].cnt -= x - n - 1
			}
		}

		for i := 1; i <= d; i++ {
			cmod := modMap[i]

			cnt := n / (m / gcd(m, cmod.mod))
			ans += cnt * cmod.cnt
			ans %= ansMod
		}

		wb.WriteString(strconv.Itoa(ans))
		wb.WriteRune('\n')
	}
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}

	return a
}
