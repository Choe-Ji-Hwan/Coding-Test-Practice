import sys
input = lambda: sys.stdin.readline().strip()


N = int(input())    # 1 <= N <= 40
M = int(input())    # 0 <= M <= N

dp = [0 for _ in range(N + 1)]
dp[0] = 1

# 고정 석은 먼저 1로 설정(체크 용도)
for i in range(M):
    dp[int(input())] = 1

# m = 0일 때, 구간을 나누면,
# n = 1, 1 -> 1가지.
# n = 2, 1 2, 2 1 -> 2가지.
# n = 3, 1 2 3, 2 1 3, 1 3 2 -> 3가지.
# n = 4, 1 2 3 4, 2 1 3 4, 1 3 2 4 (i-1) / 1 2 4 3, 2 1 4 3 (i-2)-> 5가지.
# ... > 하다 보니까, dp[i] = dp[i - 1] + dp[i - 2]..

count = 0
idx = 1
result = 1

while idx <= N:
    # 고정 석이면, 연속 count 0으로 초기화.
    if dp[idx] == 1:
        result *= dp[idx - 1]
        count = 0
        idx += 1
        continue
    elif count == 0:
        dp[idx] = 1
    elif count == 1:
        dp[idx] = 2
    else:
        dp[idx] = dp[idx - 1] + dp[idx - 2]

    count += 1
    idx += 1

# 마지막 고정석이 없는데, 끝내야 하므로 계산.
result *= dp[N]
print(result)
