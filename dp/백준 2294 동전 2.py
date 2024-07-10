import sys
input = sys.stdin.readline


# 1 <= n <= 100
# 1 <= k <= 10000
def solution(n, k, coins):
    dp = [k + 1] * (k + 1)
    # 초기화.
    for c in coins:
        if c <= k:  # <- 이걸 생각 하는 게 쉽지 않았음.
            dp[c] = 1

    # 총 횟수, 1,000,000
    for i in range(1, k + 1):
        for c in coins:
            if 0 <= i + c < k + 1:
                dp[i + c] = min(dp[i + c], dp[i] + 1)

    if dp[k] == k + 1:
        return - 1
    else:
        return dp[k]


N, K = map(int, input().split())
COINS = []
for _ in range(N):
    COINS.append(int(input()))
print(solution(N, K, COINS))
