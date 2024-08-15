import sys
input = lambda: sys.stdin.readline().rstrip()


N = int(input())
target = list(map(int, input().split()))
M = int(input())    # 질문 수
dp = [[0] * N for _ in range(N)]

for i in range(N):
    for start in range(N - i):
        end = start + i

        if start == end:
            dp[start][end] = 1
        elif target[start] == target[end]:
            if start + 1 == end:
                dp[start][end] = 1
            elif dp[start + 1][end - 1] == 1:
                dp[start][end] = 1

for i in range(M):
    s, e = map(int, input().split(" "))
    print(dp[s - 1][e - 1])

