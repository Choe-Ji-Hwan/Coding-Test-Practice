import sys
from collections import deque
input = lambda: sys.stdin.readline().rstrip()
# 처음 풀어본, 위상 정렬.
#   위상 정렬 모를 때, 그래프를 normalize(부모-자식 관계가 하나일 경우를 간선 가중치 합칠 수 있음)하고,
#   dfs로 풀 생각이었음. -> 시간 복잡도는 비슷하거나 조금 더 걸리는 수준이지만, 구현하기 까다롭기 때문에,
#   위상 정렬로 풀이하는 것이 좋다.


T = int(input())
for _ in range(T):
    N, K = map(int, input().split(" "))
    # 걸리는 시간.
    complete_time = list(map(int, input().split(" ")))
    # 위상
    in_degree = [0 for _ in range(N)]
    graph = [[] for _ in range(N)]
    dp = [0 for _ in range(N)]

    for _ in range(K):
        X, Y = map(int, input().split(" "))
        graph[X - 1].append(Y - 1)
        in_degree[Y - 1] += 1

    queue = deque([])
    for node in range(N):
        if in_degree[node] == 0:
            queue.append(node)
            dp[node] = complete_time[node]   # 맨 처음 처리를 하기 때문에 그걸로 결정.

    while len(queue) != 0:
        target = queue.popleft()
        for child in graph[target]:
            # 동시에 진행 될 수 있으니, dp를 사용하여 최대 걸리는 시간이 최소 필요한 시간.
            dp[child] = max(dp[child], dp[target] + complete_time[child])
            # 위상 update.
            in_degree[child] -= 1
            if in_degree[child] == 0:
                queue.append(child)

    w = int(input())
    print(dp[w - 1])
