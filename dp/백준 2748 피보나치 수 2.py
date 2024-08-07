import sys
input = sys.stdin.readline


# 계산부
def solution(n):
    dp = [0] * (n + 1)
    dp[0] = 0
    dp[1] = 1

    for i in range(2, n + 1):
        dp[i] = dp[i - 2] + dp[i - 1]

    return dp[n]


# 입력부
N = int(input())
print(solution(N))
