import sys
from collections import deque
input = lambda: sys.stdin.readline().rstrip()
# 위상 정렬로 풀어봐야겠다는 생각.

n, count = map(int, input().split(" "))

graph = [[] for _ in range(n + 1)]
in_degree = [0 for _ in range(n + 1)]
result = []

for _ in range(count):
    front, back = map(int, input().split(" "))
    graph[front].append(back)
    in_degree[back] += 1

queue = deque([])
for node in range(1, n + 1):
    if in_degree[node] == 0:
        queue.append(node)

while len(queue) != 0:
    node = queue.popleft()
    result.append(node)

    for (child) in graph[node]:
        in_degree[child] -= 1
        if in_degree[child] == 0:
            queue.append(child)

print(" ".join(map(str, result)))
