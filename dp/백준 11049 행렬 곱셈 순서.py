# dp 같은 문제..
# python 3으로 풀면, 어렵다.. 다시 생각하지 못할 듯 pypy3으로는 가능.
import sys
input = lambda: sys.stdin.readline().rstrip()

n = int(input())
# dp[i][j], i는 행, j는 열.
dp = [[0 for _ in range(n)] for _ in range(n)]
matrix = []

for _ in range(n):
    matrix.append(list(map(int, input().split(" "))))

# 순서 중요, 1짜리 먼저 계산 후에 점차 term 을 늘려 가면서 update 해야 됨.
for term in range(1, n):
    for start in range(n - term):
        end = start + term
        # min 값 업데이트를 위함.
        dp[start][end] = sys.maxsize

        for k in range(start, end):
            left = dp[start][k]
            right = dp[k+1][end]
            value = matrix[start][0] * matrix[k][1] * matrix[end][1]

            dp[start][end] = min(dp[start][end], left + right + value)

print(dp[0][-1])
