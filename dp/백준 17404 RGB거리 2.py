import sys
input = lambda: sys.stdin.readline().rstrip()

R = 0
G = 1
B = 2
answer = sys.maxsize
N = int(input())

info = []
for _ in range(N):
    red, green, blue = map(int, input().split())
    info.append((red, green, blue))

for color in range(3):
    # 순서는 R[0]G[1]B[2]
    dp = [[1_000_000 for _ in range(N)] for _ in range(3)]
    # 지금 첫 번째를 색칠 했다는 걸 판별 조건: 결과가 max_size 아니면 됨.
    dp[color][0] = info[0][color]
    for i in range(1, N):
        dp[R][i] = min(dp[G][i - 1], dp[B][i - 1]) + info[i][R]
        dp[G][i] = min(dp[R][i - 1], dp[B][i - 1]) + info[i][G]
        dp[B][i] = min(dp[R][i - 1], dp[G][i - 1]) + info[i][B]

    # 선택한 지금 컬러는 이미 알고 있다. 따라서 하나는 처리 안해도 된다.
    for c in range(3):
        if c != color:
            answer = min(answer, dp[c][-1])

print(answer)
