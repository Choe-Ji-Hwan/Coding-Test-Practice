import sys
input = sys.stdin.readline


# Knapsack 알고리즘
def solution(n, k, things):
    if n == 1:
        if things[0][0] <= k:
            return things[0][1]
        else:
            return 0

    # 세로 열이 ~번 째까지 계산, 가로 행이 각 무게.
    dp = [[0] * (k + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        w, v = things[i - 1]
        for j in range(1, k + 1):
            if j < w:
                dp[i][j] = dp[i - 1][j]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - w] + v)

    return max(dp[n])


N, K = map(int, input().split())
T = [list(map(int, input().split())) for _ in range(N)]
print(solution(N, K, T))
