# algorithm-hub

- 백준
- 프로그래머스
- atcoder

## AtCoder

Publish a solution from any directory without modifying a local checkout:

```bash
# Install once (make sure ~/.local/bin is in PATH)
mkdir -p ~/.local/bin
install -m 755 scripts/atcoder-pusher ~/.local/bin/atcoder-pusher
atcoder-pusher setup git@github.com:grrru/algorithm-hub.git

# Run in a directory containing main.go
atcoder-pusher https://atcoder.jp/contests/abc467/tasks/abc467_e

# Or provide the source file explicitly
atcoder-pusher https://atcoder.jp/contests/abc467/tasks/abc467_e ./solution.go
```

`atcoder-pusher` uses a temporary shallow clone, commits only the target problem
directory, and pushes it to the configured repository. Set
`ATCODER_PUSHER_REPO` to override the configured repository for one command.

The commit message is generated automatically, for example
`Submit AtCoder ABC350 A solution in Go`.

<!---LeetCode Topics Start-->
# LeetCode Topics
## Array
|  |
| ------- |
| [0001-two-sum](https://github.com/grrru/algorithm-hub/tree/master/leetcode/0001-two-sum) |
## Hash Table
|  |
| ------- |
| [0001-two-sum](https://github.com/grrru/algorithm-hub/tree/master/leetcode/0001-two-sum) |
## Linked List
|  |
| ------- |
| [0002-add-two-numbers](https://github.com/grrru/algorithm-hub/tree/master/leetcode/0002-add-two-numbers) |
## Math
|  |
| ------- |
| [0002-add-two-numbers](https://github.com/grrru/algorithm-hub/tree/master/leetcode/0002-add-two-numbers) |
## Recursion
|  |
| ------- |
| [0002-add-two-numbers](https://github.com/grrru/algorithm-hub/tree/master/leetcode/0002-add-two-numbers) |
<!---LeetCode Topics End-->
