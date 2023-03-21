# 백준 2448 별 찍기 - 11
# Gold 4
import sys

sys.setrecursionlimit(10 ** 9)
input = sys.stdin.readline

n = int(input())
# 빈 캔버스 만들기
canvas = [[" " for j in range(2 * n - 1)] for i in range(n)]


# 알고리즘
def dfs(divide, col, row):
    # 원래 *로 그릴 수 있는 그림이 기본 default 아님. n == 3일 때 default 그림
    if divide == 3:
        canvas[col][row + divide - 1] = "*"
        canvas[col + 1][row + 1] = "*"
        canvas[col + 1][row + divide] = "*"
        for j in range(0, 2 * divide - 1):
            canvas[col + 2][row + j] = "*"
        return
    dfs(divide // 2, col, row + divide // 2)
    dfs(divide // 2, col + divide // 2, row)
    dfs(divide // 2, col + divide // 2, row + divide)


dfs(n, 0, 0)
for ele in canvas:
    print("".join(ele))
