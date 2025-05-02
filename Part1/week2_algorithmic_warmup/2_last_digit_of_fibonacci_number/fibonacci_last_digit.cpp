#include <iostream>

int get_fibonacci_last_digit_naive(int n) {
    if (n <= 1)
        return n;

    int a = 0;
    int b  = 1;

    for (int i = 2; i <= n; ++i) {
        int c = (a + b) % 10;
        a = b;
        b = c;
    }
    return b;
}

    // 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377

int main() {
    int n;
    std::cin >> n;
    int c = get_fibonacci_last_digit_naive(n);
    std::cout << c << '\n';
    }
