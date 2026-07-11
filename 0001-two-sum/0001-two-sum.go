func twoSum(nums []int, target int) []int {
	type Nums struct {
		val int
		idx int
	}

	n := len(nums)
	numArr := []Nums{}
	for i := range len(nums) {
		numArr = append(numArr, Nums{nums[i], i})
	}

	slices.SortFunc(numArr, func(a Nums, b Nums) int {
		return a.val - b.val
	})

	i, j := 0, n-1

	for {
		res := numArr[i].val + numArr[j].val

		if res == target {
			break
		}

		if res < target {
			i++
		} else {
			j--
		}
	}

	return []int{numArr[i].idx, numArr[j].idx}
}