import sys
input = sys.stdin.readline


# 1 <= N <= 100
# 1개, 3개, 4개 -> dp로 풀 수 있음.
def solution(n):
    participate = {0: "SK", 1: "CY"}
    if n == 1: return participate[0]
    if n == 2: return participate[1]
    if n == 3: return participate[0]
    if n == 4: return participate[0]

    # 이기는 사람을 저장 하기 위한 배열.
    dp = [0] * (n + 1)
    dp[1] = 0   # (1). 상근 승리.
    dp[2] = 1   # (1, 1). 창영 승리.
    dp[3] = 0   # (3). 상근 승리.
    dp[4] = 0   # (4). 상근 승리.
    # 5 (3, 1, 1). 상근 승리.
    # 6 (4, 1, 1). 상근 승리.
    # 7 (1, 4, 1, 1). 창영 승리.
    # 8 상근이 1개 가져가면, dp[7]과 같은 결과 -> 상근승
    #   상근이 3개 가져가면, dp[5]와 같은 결과 -> 상근승
    #   상근이 4개 가져가면, dp[4]와 같은 결과 -> 상근승

    get = [1, 3, 4]
    for i in range(5, n + 1):
        # 나머지 1, 3, 4개가 가지는 수일 경우 승리.
        for t in get:
            if dp[i - t] == 1:
                dp[i] = 0
                break
            dp[i] = 1

    return participate[dp[n]]


N = int(input())
print(solution(N))
