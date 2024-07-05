import sys

input = sys.stdin.readline


# 계산부
def solution(n, stair):
    # (누적 점수, 한 계단 연속 횟수)
    dp = [0] * n
    dp[0] = stair[0]
    if n == 1:
        return dp[0]
    dp[1] = max(stair[1], stair[0] + stair[1])  # 항상 stair[1] < stair[0] + stair[1], 이해를 위해 작성
    if n == 2:
        return dp[1]
    dp[2] = max(stair[0] + stair[2], stair[1] + stair[2])   # 1, 2 / 2, 1 / 1, 1, 1 <- 3개 연속 안됨.
    if n == 3:
        return dp[2]

    for i in range(3, n):
        # 현재, 전전 칸 체크 / 현재, 전, 전전전 칸 체크. -> 연속 3개 불가능 조건 더한 결과.
        dp[i] = max(dp[i - 2] + stair[i], dp[i - 3] + stair[i] + stair[i - 1])

    return dp[n - 1]


# 입력부
N = int(input())
s = []
for _ in range(N):
    s.append(int(input()))
print(solution(N, s))
