import sys
input = sys.stdin.readline


# 이렇게 풀면, 다른 사람의 풀이의 예제 답변과 다르게 나옴(아무거나 라서 상관 없고, 횟수만 지키면 됨)
# 예를 들면, 16에 대해서 
# 나의 답변: 16 8 4 2 1 -> 4번 (나누기로 풀 경우)
# 타의 답변: 16 8 4 3 1 -> 4번 (곱하기로 풀 경우)
# 또 주의할 점은, before 말고, next 리스트로 관리해서 추척하는 것은 불가능.
def solution(n):
    dp = [i - 1 for i in range(n + 1)]
    before = [0 for _ in range(n + 1)]

    for i in range(2, n + 1):
        if i % 3 == 0 and dp[i] > dp[i // 3] + 1:
            dp[i] = dp[i // 3] + 1
            before[i] = i // 3

        if i % 2 == 0 and dp[i] > dp[i // 2] + 1:
            dp[i] = dp[i // 2] + 1
            before[i] = i // 2

        if dp[i] > dp[i - 1] + 1:
            dp[i] = dp[i - 1] + 1
            before[i] = i - 1

    print(dp[n])
    index = n
    while index > 1:
        print(index, end=" ")
        index = before[index]
    print(1)
    return


N = int(input())
solution(N)
