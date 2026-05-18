package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

var (
	up    [3][3]rune
	down  [3][3]rune
	front [3][3]rune
	back  [3][3]rune
	left  [3][3]rune
	right [3][3]rune
)

const (
	RED    = 'r'
	BLUE   = 'b'
	WHITE  = 'w'
	YELLOW = 'y'
	ORANGE = 'o'
	GREEN  = 'g'
)

func initCubic() {
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			up[i][j] = WHITE
			down[i][j] = YELLOW
			front[i][j] = RED
			back[i][j] = ORANGE
			left[i][j] = GREEN
			right[i][j] = BLUE
		}
	}
}

func Rotate(arr [3][3]rune, isClock bool) [3][3]rune {
	var copyArr [3][3]rune

	if isClock {
		copyArr[0][0] = arr[2][0]
		copyArr[0][1] = arr[1][0]
		copyArr[0][2] = arr[0][0]
		copyArr[1][0] = arr[2][1]
		copyArr[1][1] = arr[1][1]
		copyArr[1][2] = arr[0][1]
		copyArr[2][0] = arr[2][2]
		copyArr[2][1] = arr[1][2]
		copyArr[2][2] = arr[0][2]
	} else {
		copyArr[0][0] = arr[0][2]
		copyArr[0][1] = arr[1][2]
		copyArr[0][2] = arr[2][2]
		copyArr[1][0] = arr[0][1]
		copyArr[1][1] = arr[1][1]
		copyArr[1][2] = arr[2][1]
		copyArr[2][0] = arr[0][0]
		copyArr[2][1] = arr[1][0]
		copyArr[2][2] = arr[2][0]
	}

	return copyArr
}

func UpMinus() {
	up = Rotate(up, false)

	save := front[0]

	front[0] = left[0]
	left[0] = back[0]
	back[0] = right[0]
	right[0] = save
}

func UpPlus() {
	up = Rotate(up, true)

	save := front[0]

	front[0] = right[0]
	right[0] = back[0]
	back[0] = left[0]
	left[0] = save
}

func DownMinus() {
	down = Rotate(down, false)

	save := front[2]

	front[2] = right[2]
	right[2] = back[2]
	back[2] = left[2]
	left[2] = save
}

func DownPlus() {
	down = Rotate(down, true)

	save := front[2]

	front[2] = left[2]
	left[2] = back[2]
	back[2] = right[2]
	right[2] = save
}

func FrontMinus() {
	front = Rotate(front, false)

	a := up[2][0]
	b := up[2][1]
	c := up[2][2]

	up[2][0] = right[0][0]
	up[2][1] = right[1][0]
	up[2][2] = right[2][0]

	right[0][0] = down[0][2]
	right[1][0] = down[0][1]
	right[2][0] = down[0][0]

	down[0][2] = left[2][2]
	down[0][1] = left[1][2]
	down[0][0] = left[0][2]

	left[2][2] = a
	left[1][2] = b
	left[0][2] = c
}

func FrontPlus() {
	front = Rotate(front, true)

	a := up[2][0]
	b := up[2][1]
	c := up[2][2]

	up[2][0] = left[2][2]
	up[2][1] = left[1][2]
	up[2][2] = left[0][2]

	left[2][2] = down[0][2]
	left[1][2] = down[0][1]
	left[0][2] = down[0][0]

	down[0][2] = right[0][0]
	down[0][1] = right[1][0]
	down[0][0] = right[2][0]

	right[0][0] = a
	right[1][0] = b
	right[2][0] = c
}

func BackMinus() {
	back = Rotate(back, false)

	a := up[0][0]
	b := up[0][1]
	c := up[0][2]

	up[0][0] = left[2][0]
	up[0][1] = left[1][0]
	up[0][2] = left[0][0]

	left[2][0] = down[2][2]
	left[1][0] = down[2][1]
	left[0][0] = down[2][0]

	down[2][2] = right[0][2]
	down[2][1] = right[1][2]
	down[2][0] = right[2][2]

	right[0][2] = a
	right[1][2] = b
	right[2][2] = c
}

func BackPlus() {
	back = Rotate(back, true)

	a := up[0][0]
	b := up[0][1]
	c := up[0][2]

	up[0][0] = right[0][2]
	up[0][1] = right[1][2]
	up[0][2] = right[2][2]

	right[0][2] = down[2][2]
	right[1][2] = down[2][1]
	right[2][2] = down[2][0]

	down[2][2] = left[2][0]
	down[2][1] = left[1][0]
	down[2][0] = left[0][0]

	left[2][0] = a
	left[1][0] = b
	left[0][0] = c
}

func LeftMinus() {
	left = Rotate(left, false)

	a := up[0][0]
	b := up[1][0]
	c := up[2][0]

	up[0][0] = front[0][0]
	up[1][0] = front[1][0]
	up[2][0] = front[2][0]

	front[0][0] = down[0][0]
	front[1][0] = down[1][0]
	front[2][0] = down[2][0]

	down[0][0] = back[2][2]
	down[1][0] = back[1][2]
	down[2][0] = back[0][2]

	back[2][2] = a
	back[1][2] = b
	back[0][2] = c
}

func LeftPlus() {
	left = Rotate(left, true)

	a := up[0][0]
	b := up[1][0]
	c := up[2][0]

	up[0][0] = back[2][2]
	up[1][0] = back[1][2]
	up[2][0] = back[0][2]

	back[2][2] = down[0][0]
	back[1][2] = down[1][0]
	back[0][2] = down[2][0]

	down[0][0] = front[0][0]
	down[1][0] = front[1][0]
	down[2][0] = front[2][0]

	front[0][0] = a
	front[1][0] = b
	front[2][0] = c
}

func RightMinus() {
	right = Rotate(right, false)

	a := up[0][2]
	b := up[1][2]
	c := up[2][2]

	up[0][2] = back[2][0]
	up[1][2] = back[1][0]
	up[2][2] = back[0][0]

	back[2][0] = down[0][2]
	back[1][0] = down[1][2]
	back[0][0] = down[2][2]

	down[0][2] = front[0][2]
	down[1][2] = front[1][2]
	down[2][2] = front[2][2]

	front[0][2] = a
	front[1][2] = b
	front[2][2] = c
}

func RightPlus() {
	right = Rotate(right, true)

	a := up[0][2]
	b := up[1][2]
	c := up[2][2]

	up[0][2] = front[0][2]
	up[1][2] = front[1][2]
	up[2][2] = front[2][2]

	front[0][2] = down[0][2]
	front[1][2] = down[1][2]
	front[2][2] = down[2][2]

	down[0][2] = back[2][0]
	down[1][2] = back[1][0]
	down[2][2] = back[0][0]

	back[2][0] = a
	back[1][0] = b
	back[0][0] = c
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var T int
	fmt.Fscanln(reader, &T)

	for T > 0 {
		T--
		var cnt int
		fmt.Fscanln(reader, &cnt)

		str, _ := reader.ReadString('\n')
		str = strings.TrimSpace(str)
		line := strings.Split(str, " ")

		initCubic()

		for i := 0; i < cnt; i++ {
			switch line[i] {
			case "L-":
				LeftMinus()
			case "L+":
				LeftPlus()
			case "R-":
				RightMinus()
			case "R+":
				RightPlus()
			case "U-":
				UpMinus()
			case "U+":
				UpPlus()
			case "B-":
				BackMinus()
			case "B+":
				BackPlus()
			case "F-":
				FrontMinus()
			case "F+":
				FrontPlus()
			case "D-":
				DownMinus()
			case "D+":
				DownPlus()
			}
		}

		for i := 0; i < 3; i++ {
			for j := 0; j < 3; j++ {
				fmt.Fprintf(writer, "%c", up[i][j])
			}
			fmt.Fprint(writer, "\n")
		}
	}
}
