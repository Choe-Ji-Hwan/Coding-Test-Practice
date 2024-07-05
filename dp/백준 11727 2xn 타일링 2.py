import sys
input = sys.stdin.readline
MODULAR = 10_007


def solution(n):
    dp = [0] * (n + 1)
    dp[1] = 1
    if n == 1:
        return dp[1]
    dp[2] = 3
    if n == 2:
        return dp[2]

    for i in range(3, n + 1):
        dp[i] = (dp[i - 1] + dp[i - 2] * 2) % MODULAR

    return dp[n]


N = int(input())
print(solution(N))
