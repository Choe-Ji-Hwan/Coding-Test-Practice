# 백준 2638 치즈
# Gold 3
import sys
from collections import deque

sys.setrecursionlimit(10 ** 9)
input = sys.stdin.readline

MELT = 5
OUTER = 3

n, m = map(int, input().split(" "))
cheeses = [list(map(int, input().split(" "))) for _ in range(n)]
cheese_location = []
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

for i in range(n):
    for j in range(m):
        if cheeses[i][j] == 1:
            cheese_location.append((i, j))


def bfs(graph, col, row):
    visited = [[False for _ in range(m)] for _ in range(n)]
    queue = deque()
    queue.append((col, row))
    graph[col][row] = OUTER
    visited[col][row] = True
    while queue:
        col, row = queue.popleft()
        for d in range(4):
            n_col = col + dy[d]
            n_row = row + dx[d]
            if 0 <= n_col < n and 0 <= n_row < m:
                if not visited[n_col][n_row] and (graph[n_col][n_row] == 0 or graph[n_col][n_row] == 3):
                    visited[n_col][n_row] = True
                    graph[n_col][n_row] = OUTER
                    queue.append((n_col, n_row))


def setWillDelete(graph, location):
    melt = []
    un_melt = []
    for loc in location:
        y, x = loc[0], loc[1]
        three_count = 0
        if graph[y][x] == 1:
            for d in range(4):
                n_y = y + dy[d]
                n_x = x + dx[d]
                if graph[n_y][n_x] == 3:
                    three_count += 1
            if three_count >= 2:
                graph[y][x] = MELT
                melt.append((y, x))
                continue
        un_melt.append((y, x))
    return melt, un_melt


def melting(graph, melt):
    for loc in melt:
        y, x = loc[0], loc[1]
        graph[y][x] = OUTER


cnt = 0
while cheese_location:
    bfs(cheeses, 0, 0)
    melt_location, un_melt_location = setWillDelete(cheeses, cheese_location)
    melting(cheeses, melt_location)
    cheese_location = un_melt_location
    cnt += 1

print(cnt, end="")
