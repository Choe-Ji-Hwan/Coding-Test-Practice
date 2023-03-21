# 백준 2533 사회망 서비스(SNS)
# Gold 3

import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

n = int(input())
graph = [[] for i in range(n)]

for i in range(n - 1):
    first, second = map(int, input().split(" "))
    graph[first - 1].append(second - 1)
    graph[second - 1].append(first - 1)

visited = [False for i in range(n)]
dp = [[0, 0] for i in range(n)]


def dfs(start):
    visited[start] = True
    if len(graph[start]) == 0:
        dp[start][0] = 0
        dp[start][1] = 1
    else:
        for i in graph[start]:
            if not visited[i]:
                dfs(i)
                dp[start][0] += dp[i][1]
                dp[start][1] += min(dp[i][0], dp[i][1])
        dp[start][1] += 1


dfs(0)
print(min(dp[0][0], dp[0][1]), end="")
