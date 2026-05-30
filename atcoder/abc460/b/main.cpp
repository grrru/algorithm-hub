#include <cmath>
#include <cstdlib>
#include <iostream>

int T;
const std::string yes = "Yes";
const std::string no = "No";

int main() {
  std::cin >> T;

  long long x1, y1, r1, x2, y2, r2;
  while (T-- > 0) {
    std::cin >> x1 >> y1 >> r1 >> x2 >> y2 >> r2;

    long long dx = x1 - x2;
    long long dy = y1 - y2;

    long long dist = dx * dx + dy * dy;
    if ((r1 - r2) * (r1 - r2) <= dist && dist <= (r1 + r2) * (r1 + r2)) {
      std::cout << yes << '\n';
    } else {
      std::cout << no << '\n';
    }
  }
  return 0;
}
