#include <cstdio>
#include <iostream>
#include <string>

bool solve(int n, const std::string &s) {
  for (int i = 0; i < n / 2; i++) {
    // std::printf("s[%d]: %c, s[%d]: %c\n", i, s[i], i + n / 2, s[i + n / 2]);
    if (s[i] != s[i + n / 2]) {
      return false;
    }
  }

  return true;
}

int main() {
  std::string s;
  std::cin >> s;

  int n = s.length() - 1;

  if (n % 2 != 0) {
    n--;
  }

  for (int i = n; i >= 2; i -= 2) {
    if (solve(i, s)) {
      std::cout << i;
      return 0;
    }
  }

  return 0;
}
