import sys
input = sys.stdin.readline


maximum = 0


def dfs(y, x, r, c, cnt, board, visited):
    global maximum
    maximum = max(maximum, cnt)

    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]

    for i in range(4):
        n_y = y + dy[i]
        n_x = x + dx[i]

        if 0 <= n_y < r and 0 <= n_x < c and not visited[ord(board[n_y][n_x]) - ord('A')]:
            visited[ord(board[n_y][n_x]) - ord('A')] = True
            dfs(n_y, n_x, r, c, cnt + 1, board, visited)
            visited[ord(board[n_y][n_x]) - ord('A')] = False


def solution(r, c, board):
    visited = [False] * 26

    visited[ord(board[0][0]) - ord('A')] = True
    dfs(0, 0, r, c, 1, board, visited)

    return maximum


R, C = map(int, input().split())
BOARD = [list(input().strip()) for i in range(R)]
print(solution(R, C, BOARD))
