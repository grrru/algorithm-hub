# algorithm-hub

- 백준
- 프로그래머스
- atcoder

## AtCoder

Create a problem workspace:

```bash
scripts/atcoder new abc350 a
scripts/atcoder new https://atcoder.jp/contests/abc139/tasks/abc139_b
```

If no language is provided, the script prompts for `go` or `cpp`.

Push AtCoder changes:

```bash
scripts/atcoder done
```

The default commit message is inferred from the staged AtCoder path, for example `Submit AtCoder ABC350 A solution in Go`.

Legacy commands still work:

```bash
./atcoder-new abc350 a
./atcoder-push
```
