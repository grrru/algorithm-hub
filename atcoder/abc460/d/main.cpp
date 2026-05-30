#include <iostream>
#include <queue>
#include <utility>
#include <vector>
int h, w;
std::vector<std::string> arr;
int di[8] = {0, -1, -1, -1, 0, 1, 1, 1};
int dj[8] = {1, 1, 0, -1, -1, -1, 0, 1};

bool isOut(int i, int j) { return i < 0 || i >= h || j < 0 || j >= w; }

int main() {
  std::cin >> h >> w;
  arr.resize(h);

  for (int i = 0; i < h; i++) {
    std::cin >> arr[i];
  }

  std::vector<std::vector<int>> bdist(h, std::vector<int>(w));
  std::vector<std::vector<int>> wdist(h, std::vector<int>(w));
  std::queue<std::pair<int, int>> bq;
  std::queue<std::pair<int, int>> wq;

  for (int i = 0; i < h; i++) {
    for (int j = 0; j < w; j++) {
      if (arr[i][j] == '#') {
        bq.push({i, j});
        bdist[i][j] = 0;
        wdist[i][j] = -1;
      } else {
        wq.push({i, j});
        bdist[i][j] = -1;
        wdist[i][j] = 0;
      }
    }
  }

  while (!bq.empty()) {
    std::pair<int, int> p = bq.front();
    bq.pop();

    for (int k = 0; k < 8; k++) {
      int ni = p.first + di[k];
      int nj = p.second + dj[k];

      if (isOut(ni, nj)) {
        continue;
      }

      if (bdist[ni][nj] != -1) {
        continue;
      }

      bdist[ni][nj] = bdist[p.first][p.second] + 1;
      bq.push({ni, nj});
    }
  }
  while (!wq.empty()) {
    std::pair<int, int> p = wq.front();
    wq.pop();

    for (int k = 0; k < 8; k++) {
      int ni = p.first + di[k];
      int nj = p.second + dj[k];

      if (isOut(ni, nj)) {
        continue;
      }

      if (wdist[ni][nj] != -1) {
        continue;
      }

      wdist[ni][nj] = wdist[p.first][p.second] + 1;
      wq.push({ni, nj});
    }
  }

  for (int i = 0; i < h; i++) {
    for (int j = 0; j < w; j++) {
      if (arr[i][j] == '#') {
        if (wdist[i][j] == -1) {
          std::cout << '.';
          continue;
        }
        if (wdist[i][j] % 2 == 0) {
          std::cout << '.';
        } else {
          std::cout << '#';
        }
      } else {
        if (bdist[i][j] == -1) {
          std::cout << '.';
          continue;
        }
        if (bdist[i][j] % 2 == 0) {
          std::cout << '#';
        } else {
          std::cout << '.';
        }
      }
    }
    std::cout << '\n';
  }

  return 0;
}
