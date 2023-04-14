# 백준 10164 격자상의 경로
# Silver 1
import sys

input = sys.stdin.readline

n, m, k = map(int, input().split(" "))
grid = [[0 for j in range(m)] for i in range(n)]
first = 1
col = k // m if k % m != 0 else k // m - 1
row = k % m - 1 if k % m != 0 else m - 1
if k == 0:
    col = 0
    row = 0

for i in range(col + 1 if k != 0 else n):
    grid[i][0] = 1
for j in range(row + 1 if k != 0 else m):
    grid[0][j] = 1

if k != 0:
    for i in range(1, col + 1):
        for j in range(1, row + 1):
            grid[i][j] = grid[i - 1][j] + grid[i][j - 1]

    first = grid[col][row]
    grid[col][row] = 0
    for i in range(col, n):
        grid[i][row] = 1
    for j in range(row, m):
        grid[col][j] = 1

for i in range(col + 1, n):
    for j in range(row + 1, m):
        grid[i][j] = grid[i - 1][j] + grid[i][j - 1]

print(first * grid[n - 1][m - 1])
