func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	ans := &ListNode{}
	remained := 0

	curNode := ans

	for {
		cur := l1.Val + l2.Val + remained

		curNode.Val = cur % 10
		remained = cur / 10

		l1, l2 = l1.Next, l2.Next

		if l1 != nil || l2 != nil {
			curNode.Next = &ListNode{}
			curNode = curNode.Next
		}

		if l1 == nil || l2 == nil {
			break
		}
	}

	for l1 != nil {
		cur := l1.Val + remained

		curNode.Val = cur % 10
		remained = cur / 10

		l1 = l1.Next

		if l1 == nil {
			break
		}

		curNode.Next = &ListNode{}
		curNode = curNode.Next
	}
	for l2 != nil {
		cur := l2.Val + remained

		curNode.Val = cur % 10
		remained = cur / 10

		l2 = l2.Next

		if l2 == nil {
			break
		}

		curNode.Next = &ListNode{}
		curNode = curNode.Next
	}

	if remained != 0 {
		curNode.Next = &ListNode{}
		curNode = curNode.Next
		curNode.Val = remained
	}

	return ans
}