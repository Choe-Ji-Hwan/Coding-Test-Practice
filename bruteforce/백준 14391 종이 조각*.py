import sys
input = lambda: sys.stdin.readline().strip()


def dfs(paper):
    answer = -1
    for k in range(1 << (N*M)):
        total = 0
        # 행방향 탐색
        for i in range(N):
            horizontal_sum = 0
            for j in range(M):
                idx = (i*M) + j
                if k & (1 << idx):
                    horizontal_sum = horizontal_sum*10 + paper[i][j]
                else:
                    total += horizontal_sum
                    horizontal_sum = 0
            total += horizontal_sum

        # 열방향 탐색
        for j in range(M):
            vertical_sum = 0
            for i in range(N):
                idx = (i*M) + j
                if not (k & (1 << idx)):
                    vertical_sum = vertical_sum*10 + paper[i][j]
                else:
                    total += vertical_sum
                    vertical_sum = 0
            total += vertical_sum

        answer = max(answer, total)
    return answer


N, M = map(int, input().split(" "))
p = []
for _ in range(N):
    p.append(list(map(int, list(input()))))
print(dfs(p))
