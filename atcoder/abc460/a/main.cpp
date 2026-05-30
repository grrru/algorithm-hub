#include <iostream>

int n;
int m;

int main() {

  std::cin >> n >> m;
  int ans = 0;

  while (m != 0) {
    m = n % m;
    ans++;
  }

  std::cout << ans;

  return 0;
}
