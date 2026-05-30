#include <algorithm>
#include <iostream>
#include <vector>

int h, w;

int Count(int i, int j, const std::vector<std::string> &arr,
          const std::vector<std::vector<int>> &dp) {

  int a;
  int b;
  if (j == 0) {
    b = dp[i - 1][j];
    bool down = arr[i - 1][j] == '.' && arr[i][j] == '#';

    if (down) {
      b++;
    }
    return b;
  }

  if (i == 0) {
    a = dp[i][j - 1];
    bool left = arr[i][j - 1] == '.' && arr[i][j] == '#';

    if (left) {
      a++;
    }
    return a;
  }

  a = dp[i][j - 1];
  b = dp[i - 1][j];
  bool left = arr[i][j - 1] == '.' && arr[i][j] == '#';
  bool down = arr[i - 1][j] == '.' && arr[i][j] == '#';

  if (left) {
    a++;
  }

  if (down) {
    b++;
  }

  return std::min(a, b);
}

int main() {
  std::cin >> h >> w;

  std::vector<std::string> arr(h);
  std::vector<std::vector<int>> dp(h, std::vector<int>(w));

  for (int i = 0; i < h; i++) {
    std::cin >> arr[i];

    dp[i].assign(w, -1);
  }

  if (arr[0][0] == '.') {
    dp[0][0] = 0;
  } else {
    dp[0][0] = 1;
  }

  for (int i = 0; i < h; i++) {
    for (int j = 0; j < w; j++) {
      if (dp[i][j] != -1) {
        continue;
      }

      dp[i][j] = Count(i, j, arr, dp);
    }
  }

  std::cout << dp[h - 1][w - 1];

  return 0;
}
