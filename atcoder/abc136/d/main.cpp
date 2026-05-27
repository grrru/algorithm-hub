#include <iostream>
#include <string>
#include <vector>

std::string s;
std::vector<int> arr;
std::vector<int> dp;

int findNearest(int idx) {
  if (dp[idx] != -1) {
    return dp[idx];
  }

  if (s[idx] == 'R') {
    if (s[idx + 1] == 'L') {
      dp[idx] = 0;
    } else {
      dp[idx] = findNearest(idx + 1) + 1;
    }
  } else {
    if (s[idx - 1] == 'R') {
      dp[idx] = 0;
    } else {
      dp[idx] = findNearest(idx - 1) - 1;
    }
  }

  return dp[idx];
}

int main() {
  std::cin >> s;
  int n = s.length();
  arr.resize(n);
  dp.assign(n, -1);

  for (int i = 0; i < n; i++) {
    findNearest(i);

    arr[i + dp[i] + (dp[i] % 2)]++;
  }

  for (int i = 0; i < n; i++) {
    std::cout << arr[i] << ' ';
  }

  return 0;
}
