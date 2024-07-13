import sys
input = lambda: sys.stdin.readline().strip()


f = {1: 1, 2: 1, 3: 2, 4: 3, 5: 5}


def fibo(n):
    if f.get(n) is not None:
        return f[n]
    else:
        if n % 2 == 0:
            f[n] = (fibo(n // 2 + 1) ** 2 - fibo(n // 2 - 1) ** 2) % 1000000007
        else:
            f[n] = (fibo(n // 2) ** 2 + fibo(n // 2 + 1) ** 2) % 1000000007
    return f[n]


N = int(input())
print(fibo(N))
