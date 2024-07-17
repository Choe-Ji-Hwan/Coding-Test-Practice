import sys
input = lambda: sys.stdin.readline().strip()


def solution(drop, t, w):
    drop = [0] + drop
    # 2차원 배열, [시간][w 사용 수]
    dp = [[0 for _ in range(w + 1)] for _ in range(t + 1)]

    # 1초 때,
    dp[1][0] = 1 if drop[1] == 1 else 0     # 초기 위치 1번이라, 1번에 떨어질 때 점수
    dp[1][1] = 1 if drop[1] == 2 else 0     # w=1로, 2로 이동. 2번에 떨어질 때 점수.

    # w 짝수면 1위치, 홀수면 2위치
    for i in range(2, t + 1):
        for j in range(w + 1):
            if j % 2 == 0:
                # 만약, j가 현재 9 / i-1일 때 j를 0~10까지 확인했을 때 j가 5일 때 였다면,
                # 6, 7, 8 이동했을 때가 아닌, 5번 이동하고 6번째다. j가 9지만. 6번 이동했을 때.
                dp[i][j] = max(dp[i - 1][0: j + 1]) + (1 if drop[i] == 1 else 0)
            else:
                dp[i][j] = max(dp[i - 1][0: j + 1]) + (1 if drop[i] == 2 else 0)

    return max(dp[t])


T, W = map(int, input().split())
DROP = []
for _ in range(T):
    DROP.append(int(input()))
print(solution(DROP, T, W))
