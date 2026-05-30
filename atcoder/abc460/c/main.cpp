
#include <algorithm>
#include <iostream>
#include <vector>
int n, m;

std::vector<int> arr;
std::vector<int> brr;

int main() {
  std::cin >> n >> m;
  arr.resize(n);
  brr.resize(m);

  for (int i = 0; i < n; i++) {
    std::cin >> arr[i];
  }
  for (int j = 0; j < m; j++) {
    std::cin >> brr[j];
  }

  std::sort(arr.begin(), arr.end());
  std::sort(brr.begin(), brr.end());

  int i = 0;
  int j = 0;

  int ans = 0;
  for (; i < n && j < m;) {
    if (arr[i] * 2 < brr[j]) {
      i++;
      continue;
    }

    i++;
    j++;
    ans++;
  }

  std::cout << ans;

  return 0;
}
