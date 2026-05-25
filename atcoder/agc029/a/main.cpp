#include <algorithm>
#include <cstdio>
#include <iostream>
#include <vector>

int main() {
  std::string s;
  std::cin >> s;

  long long ans = 0;
  int n = s.length();
  int lastB = n;

  std::vector<char> arr(n);

  for (int i = 0; i < n; i++) {
    arr[i] = s[i];

    if (arr[i] == 'B') {
      lastB = std::min(lastB, i);
      continue;
    }

    if (lastB >= i) {
      continue;
    }

    ans += i - lastB;

    arr[i] = 'B';
    lastB++;
  }

  std::cout << ans;

  return 0;
}
