import sys
input = sys.stdin.readline

# 상 하 좌 우
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]


# 1 <= n <= 100,000
def solution(n, stickers):
    if n == 1:
        return max(stickers[0][0], stickers[1][0])

    # (y,x): (0,0) 선택시, (1,0) 선택시 -> 2케이스
    dp1 = [0] * n
    dp2 = [0] * n

    dp1[0] = stickers[0][0]
    dp2[0] = stickers[1][0]
    dp1[1] = dp2[0] + stickers[0][1]
    dp2[1] = dp1[0] + stickers[1][1]

    for i in range(2, n):
        dp1[i] = max(dp2[i - 1], dp2[i - 2]) + stickers[0][i]
        dp2[i] = max(dp1[i - 1], dp1[i - 2]) + stickers[1][i]

    return max(dp1[n - 1], dp2[n - 1])


T = int(input())
for _ in range(T):
    N = int(input())
    S = []
    for _ in range(2):
        S.append(list(map(int, input().split())))
    print(solution(N, S))
