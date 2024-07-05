import sys

input = sys.stdin.readline
MODULER = 1_000_000_000


# 계산부
def solution(n):
    # dp[x][y]: x는 길이, y는 마지막 자리 숫자.
    dp = [[0] * 10 for _ in range(n + 1)]
    # 한 자리일 경우 경우의 수 1.
    for i in range(1, 10):
        dp[1][i] = 1

    for i in range(2, n + 1):
        for j in range(10):
            if j == 0:
                dp[i][j] = dp[i - 1][1] % MODULER
            elif j == 9:
                dp[i][j] = dp[i - 1][8] % MODULER
            else:
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MODULER

    return sum(dp[n]) % MODULER


# 입력부
N = int(input())
print(solution(N))
