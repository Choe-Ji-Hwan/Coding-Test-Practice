# 생각 못해봄. dp 2차원 배열 [N번 까지 탐색 했을 때][필요한 비용]
# dp는 역시 어려운 편. 다양한 케이스를 접하는 것이 중요.
import sys
input = lambda: sys.stdin.readline().rstrip()

n, m = map(int, input().split(" "))
a = list(map(int, input().split(" ")))
c = list(map(int, input().split(" ")))
cost_sum = sum(c)

dp = [[0 for _ in range(10001)] for _ in range(n)]

for i in range(n):
    for cost in range(cost_sum + 1):
        if cost - c[i] >= 0:
            dp[i][cost] = max(dp[i][cost], dp[i - 1][cost - c[i]] + a[i])
        dp[i][cost] = max(dp[i][cost], dp[i - 1][cost])


for i in range(cost_sum):
    if dp[n - 1][i] >= m:
        print(i)
        break
