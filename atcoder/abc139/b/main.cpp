#include <iostream>

int main() {
  int a;
  int b;
  int ans = 0;
  int sockets = 1;

  std::cin >> a >> b;

  while (sockets < b) {
    sockets += (a - 1);
    ans++;
  }

  std::cout << ans;

  return 0;
}
