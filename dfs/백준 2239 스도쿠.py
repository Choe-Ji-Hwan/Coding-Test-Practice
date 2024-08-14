import sys
input = lambda: sys.stdin.readline().strip()

COL = 9
ROW = 9
sudoku = []
is_find = False


def check_col_line(c, num):
    for k in range(ROW):
        if sudoku[c][k] == num:
            return False
    return True


def check_row_line(r, num):
    for k in range(COL):
        if sudoku[k][r] == num:
            return False
    return True


def check_box(c, r, num):
    # 3x3 검사
    nc = (c // 3) * 3
    nr = (r // 3) * 3
    for x in range(3):
        for y in range(3):
            if sudoku[nc + y][nr + x] == num:
                return False
    return True


def dfs(depth):
    global is_find
    if is_find:
        return
    if depth == len(zero_p):
        for k in range(COL):
            print(''.join(map(str, sudoku[k])))
        is_find = True
        return

    col, row = zero_p[depth]
    for num in range(1, 10):
        if check_col_line(col, num) and check_row_line(row, num) and check_box(col, row, num):
            sudoku[col][row] = num
            dfs(depth + 1)
            sudoku[col][row] = 0


for _ in range(COL):
    sudoku.append(list(map(int, input())))

zero_p = []
for i in range(COL):
    for j in range(ROW):
        if sudoku[i][j] == 0:
            zero_p.append((i, j))

dfs(0)
