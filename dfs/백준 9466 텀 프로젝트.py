# 스패닝 트리 생각했음, 하지만 머리와 꼬리가 만나야 되니, dfs 방식.
# dfs 잘 쓰면 되는 문제, 한 부모가 자식을 2개 이상 갖지 않는다면, 풀기 쉬운 문제.
import sys
input = lambda: sys.stdin.readline().rstrip()
sys.setrecursionlimit(10**9)


t = int(input())
for _ in range(t):
    n = int(input())
    graph = [0] + list(map(int, input().split(" ")))
    visited = [False for _ in range(n + 1)]
    count = 0

    def dfs(p, team):
        global count

        visited[p] = True
        team.append(p)
        next_p = graph[p]

        if visited[next_p]:
            if next_p in team:
                count += len(team[team.index(next_p):])
        else:
            dfs(next_p, team)

    for i in range(1, n + 1):
        if not visited[i]:
            group = []
            dfs(i, group)

    print(n - count)
