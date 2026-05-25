#include <iostream>

int main() {
  int n;
  std::cin >> n;
  int arr[n + 1];

  for (int i = 1; i <= n; i++) {
    std::cin >> arr[i];
  }

  int cur = 1;
  int count = 0;
  bool visited[n + 1];
  visited[1] = true;

  while (cur != 2) {
    if (visited[arr[cur]]) {
      std::cout << -1;
      return 0;
    }

    count++;
    visited[cur] = true;
    cur = arr[cur];
  }

  std::cout << count;

  return 0;
}
