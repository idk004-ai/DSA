#include <iostream>

long long get_period(long long m)
{
    if (m <= 1)
        return m;
    long long a = 0, b = 1, c = a + b;
    for (long long i = 0; i < m * m; i++)
    {
        c = (a + b) % m;
        a = b;
        b = c;
        if (a == 0 && b == 1)
        {
            return i + 1;
        }
    }
    return -1;
}

long long get_fibonacci_huge_naive(long long n, long long m) {
    if (m <= 1) return n % m;
    long long period = get_period(m);
    n = n % period;
    if (n <= 1) return n;
    long long a = 0, b = 1, c ;
    for (long long i = 0; i < n - 1; i++)
    {
        c = (a + b) % m;
        a = b;
        b = c;
    }
    return b;
}

int main() {
    long long n, m;
    std::cin >> n >> m;
    std::cout << get_fibonacci_huge_naive(n, m) << '\n';
}
