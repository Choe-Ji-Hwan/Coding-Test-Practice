import sys
input = lambda: sys.stdin.readline().rstrip()
# 어려운 문제, dp를 위해서, 3차 배열이 필요함, 그리고 이전 선택한 것을 모두 업데이트 해야 됨.
# dp는 머리를 정말 많이 써야 되는 점..
# 두 발이 동시에 움직이지 않음.
# 두 발이 같은 지점에 있는 것은 허락되지 않음(시작 제외)
# 중앙 발 이동 -> 2의 힘, 반대편 이동 -> 4의 힘, 인접 지점 이동(대각 형태) -> 3의 힘, 같은 지점 -> 1의 힘
# dfs를 하게 된다면, 2^100000 -> 오버 프로우, dp 밖에 답이 없어 보임.


def get_score(c_cmd, p_cmd):
    # 중앙에 발이 있는 것은 맨 처음 밖에 없음.
    if abs(c_cmd - p_cmd) == 0:
        return 1
    if p_cmd == 0:
        return 2
    if abs(c_cmd - p_cmd) == 1 or abs(c_cmd - p_cmd) == 3:
        return 3
    if abs(c_cmd - p_cmd) == 2:
        return 4


cmd = list(map(int, input().split(" ")))
cmd.pop()    # 마지막 0은 제외.
cmd_num = len(cmd)

dp = [[[sys.maxsize for _ in range(5)] for _ in range(5)] for _ in range(cmd_num + 1)]
dp[-1][0][0] = 0    # 첫 번째가 무조건 0이니, i가 0일 때, i-1이 -1로 계산 되므로 circular 하게 초기화.

for i in range(cmd_num):
    c = cmd[i]
    # left 선택.
    for k in range(5):  # 5개의 바로 전 왼쪽 방향 모두 update
        for right in range(5):  # 5개의 오른쪽 방향 모두 update
            dp[i][c][right] = min(dp[i][c][right], dp[i - 1][k][right] + get_score(c, k))

    # right 선택.
    for k in range(5):  # 5개의 바로 전 왼쪽 방향 모두 update
        for left in range(5):  # 5개의 오른쪽 방향 모두 update
            dp[i][left][c] = min(dp[i][left][c], dp[i - 1][left][k] + get_score(c, k))

result = sys.maxsize
for left in range(5):
    for right in range(5):
        result = min(result, dp[cmd_num - 1][left][right])

print(result)
