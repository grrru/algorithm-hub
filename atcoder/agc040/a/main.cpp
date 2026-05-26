#include <algorithm>
#include <cstdio>
#include <iostream>
#include <string>
#include <vector>

int main() {
  std::string s;
  std::cin >> s;

  long long ans = 0;
  int n = s.length();
  std::vector<int> arr(n + 1);

  for (int i = 0; i < n;) {
    int j = i;
    for (; j < n && s[j] == '>';) {
      j++;
    }

    ans += 1LL * (j - i) * (j - i + 1) / 2;
    if (arr[i] < j - i) {
      ans -= arr[i];
      // std::printf("> arr[%d]: %d, ans: %d", i, arr[i], ans);
    } else {
      ans -= j - i;
      // std::printf("> arr[%d]: %d, ans: %d", i, arr[i], ans);
    }
    // std::cout << '\n';

    // 0<3>2>1>0<1<2>0<1<2<3<4<5>2>1>0<1
    // std::cout << ans << '\n';
    i = j;

    for (; j < n && s[j] == '<';) {
      j++;
    }

    ans += 1LL * (j - i) * (j - i + 1) / 2;

    arr[j] = j - i;
    i = j;
    // std::printf("< arr[%d]: %d, ans: %d", i, arr[i], ans);
    // std::cout << '\n' << ans << '\n';
  }
  std::cout << ans;
  return 0;
}
