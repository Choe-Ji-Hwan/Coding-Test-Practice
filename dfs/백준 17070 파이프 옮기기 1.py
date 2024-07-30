# 이 문제, 처음으로 bfs로 접근했으나 시간초과 -> bfs로 푼다면 지수적으로 증가한다.
# 따라서, 하나를 구할 때는 bfs, 나오는 개수를 구한다면 dfs, 또는 dp로 푸는 것이 올바름.
import sys
input = lambda: sys.stdin.readline().strip()

count = 0


def can_move_dig(y, x, g):
    return y + 1 < N and x + 1 < N and g[y + 1][x] == 0 and g[y][x + 1] == 0 and g[y + 1][x + 1] == 0


def can_move_col(y, x, g):
    return y + 1 < N and g[y + 1][x] == 0


def can_move_row(y, x, g):
    return x + 1 < N and g[y][x + 1] == 0


def traval(g, y, x, dire):
    global count

    if y == N - 1 and x == N - 1:
        count += 1
        return

    if dire == "R":
        if can_move_row(y, x, g):
            traval(g, y, x + 1, "R")
        if can_move_dig(y, x, g):
            traval(g, y + 1, x + 1, "D")

    if dire == "C":
        if can_move_col(y, x, g):
            traval(g, y + 1, x, "C")
        if can_move_dig(y, x, g):
            traval(g, y + 1, x + 1, "D")

    if dire == "D":
        if can_move_row(y, x, g):
            traval(g, y, x + 1, "R")
        if can_move_col(y, x, g):
            traval(g, y + 1, x, "C")
        if can_move_dig(y, x, g):
            traval(g, y + 1, x + 1, "D")


def solution(g):
    if g[N - 1][N - 1] == 1:
        return 0
    # 처음 위치는 오른쪽 끝 좌표.
    traval(g, 0, 1, "R")
    return count


ground = []
N = int(input())
for _ in range(N):
    ground.append(list(map(int, input().split(" "))))
print(solution(ground))
