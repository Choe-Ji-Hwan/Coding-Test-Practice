import sys
from collections import deque
input = lambda: sys.stdin.readline().strip()

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]


def wind(d):
    move_dust = deque([])
    up_y, up_x = cleaner[0]
    down_y, down_x = cleaner[1]

    while len(d) != 0:
        col, row, value = d.popleft()
        # 위 순환.
        if col == up_y and 1 <= row < r - 1:
            move_dust.append((col, row + 1, value))
        elif row == r - 1 and 0 < col <= up_y:
            move_dust.append((col - 1, row, value))
        elif col == 0 and 1 <= row < r:
            if not (up_y == 0 and up_x == row - 1):
                move_dust.append((col, row - 1, value))
        elif row == 0 and 0 <= col < up_y:
            if not (up_y == col + 1 and up_x == 0):
                move_dust.append((col + 1, row, value))

        # 아래 순환.
        elif col == down_y and 1 <= row < r - 1:
            move_dust.append((col, row + 1, value))
        elif row == r - 1 and down_y <= col < c - 1:
            move_dust.append((col + 1, row, value))
        elif col == c - 1 and 1 <= row < r:
            if not (down_y == c - 1 and down_x == row - 1):
                move_dust.append((col, row - 1, value))
        elif row == 0 and down_y < col <= c - 1:
            if not (down_y == col - 1 and down_x == 0):
                move_dust.append((col - 1, row, value))
        else:  # 순환하지 않는 것은 그대로 저장.
            move_dust.append((col, row, value))

    return move_dust


cleaner = {}
dust = deque([])

r, c, t = map(int, input().split(" "))
temp1 = r
r = c
c = temp1

room = []
for _ in range(c):
    room.append(list(map(int, input().split(" "))))

direct = 0  # 0이 위, 1이 아래.
for i in range(c):
    for j in range(r):
        if room[i][j] != 0 and room[i][j] != -1:
            dust.append((i, j, room[i][j]))
        if room[i][j] == -1:
            cleaner[direct] = (i, j)
            direct += 1

# 총 O(1000 * 2500*10) = 총 최대 O(25,000,000) -> 1초 내외.
# 최대 O(1000)
for _ in range(t):
    new_dust = deque([])
    # 최대 O(2500 * 4)
    while len(dust) != 0:
        y, x, amount = dust.popleft()
        amount_part = amount // 5
        if amount_part == 0:
            new_dust.append((y, x, amount))
            continue
        for i in range(4):  # 4방위
            n_y, n_x = y + dy[i], x + dx[i]
            if 0 <= n_y < c and 0 <= n_x < r and room[n_y][n_x] != -1:
                new_dust.append((n_y, n_x, amount_part))
                amount -= amount_part
        # 나눠 주고 남은 것 담음.
        new_dust.append((y, x, amount))

    # 재배치(이 작업을 하지 않으면, 계속 최대 4배수로 늘어남).
    new_room = [[0 for _ in range(r)] for _ in range(c)]
    clear1_y, clear1_x = cleaner[0]
    clear2_y, clear2_x = cleaner[1]
    new_room[clear1_y][clear1_x] = -1
    new_room[clear2_y][clear2_x] = -1
    # 최대 O(2500 * 4)
    for ele in new_dust:
        new_y, new_x, new_amount = ele
        new_room[new_y][new_x] += new_amount

    # 다시 큐에 담음.
    new_dust.clear()
    # 최대 O(2500)
    for i in range(c):
        for j in range(r):
            if new_room[i][j] != 0 and new_room[i][j] != -1:
                new_dust.append((i, j, new_room[i][j]))
    # 움직임. # 최대 O(2500)
    dust = wind(new_dust)


total = 0
for ele in dust:
    _, _, v = ele
    if v != -1 and v != 0:
        total += v

print(total)
