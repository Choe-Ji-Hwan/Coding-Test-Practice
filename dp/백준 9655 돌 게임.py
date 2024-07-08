import sys
input = sys.stdin.readline


participate = {0: "SK", 1: "CY"}


# dp 사용
def solution(n):
    dp = [0] * (n + 1)
    dp[1] = 0
    dp[2] = 1
    dp[3] = 0

    for i in range(4, n + 1):
        # 항상, 3번째 전이나 1번째 전이 창영 -> 상근이가 이김.
        if dp[i - 3] == 1 or dp[i - 1] == 1:
            dp[i] = 0
        else:
            dp[i] = 1

    return participate[dp[n]]


"""
수학 그냥 풀리는 문제.
def solution(n):
    if n % 2 == 0:
        return CHANG_YOUNG
    else:
        return SANG_GUN
"""

N = int(input())
print(solution(N))
