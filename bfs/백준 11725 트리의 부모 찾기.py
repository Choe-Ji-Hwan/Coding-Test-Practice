import sys
from collections import deque
input = sys.stdin.readline


def solution(n, edges):
    parents = {}

    matrix = [[] for _ in range(n + 1)]
    # O(10^5)
    for e in edges:
        v1, v2 = e
        matrix[v1].append(v2)
        matrix[v2].append(v1)

    queue = deque([])
    for node in matrix[1]:
        queue.append(node)
        parents[node] = 1

    while queue:
        node = queue.popleft()
        children = matrix[node]
        for c in children:
            if parents.get(c) is None:
                queue.append(c)
                parents[c] = node

    for i in range(2, n + 1):
        print(parents[i])
    return


N = int(input())
EDGES = []
for _ in range(N - 1):
    EDGES.append(list(map(int, input().split())))
solution(N, EDGES)
