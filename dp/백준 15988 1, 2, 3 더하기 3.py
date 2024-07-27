import sys
input = lambda: sys.stdin.readline().strip()


DIVIDER = 1_000_000_009
N = 1_000_001


T = int(input())
dp = [0 for _ in range(N + 1)]
dp[1] = 1   # 1 -> 1
dp[2] = 2   # 1+1 2 -> 2
dp[3] = 4   # 1+1+1, 1+2, 2+1, 3 -> 4

# (i - 1) 거 + 1 한 거니까, 그대로 더한다. 1. dp[n] = dp[n-1] + a
# (i - 2) 거 + 2 한 거니까, 2를 뺀 것에서 더한다. dp[n] = dp[n-1] + dp[n-2] + a
# (i - 3) 거 + 3 한 거니까, 3을 뺀 것에서 더한다. dp[n] = dp[n-1] + dp[n-2] + dp[n-3]

for i in range(4, N + 1):
    dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % DIVIDER

for i in range(T):
    targetNum = int(input())
    print(dp[targetNum])
