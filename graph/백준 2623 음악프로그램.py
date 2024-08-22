# 문제를 보자마자 위상정렬 생각남.
# 문제에, "전체" 가수의 순서니까, 보조 PD가 순서를 정하지 않는 가수도 포함 시켜야 됨.
import sys
from collections import deque
input = lambda: sys.stdin.readline().rstrip()

n, m = map(int, input().split(" "))
graph = [[] for _ in range(n + 1)]
in_degree = [0 for _ in range(n + 1)]

for _ in range(m):
    chain = list(map(int, input().split(" ")))
    length = chain[0]
    if length == 0:
        continue

    for i in range(2, length + 1):
        parent = chain[i - 1]
        child = chain[i]

        graph[parent].append(child)
        in_degree[child] += 1

queue = deque([])
init_in_degree_num = 0
for node in range(1, n + 1):
    if in_degree[node] == 0:
        queue.append(node)
        init_in_degree_num += 1

result = []
while len(queue) != 0:
    node = queue.popleft()
    result.append(node)

    for (child) in graph[node]:
        in_degree[child] -= 1
        if in_degree[child] == 0:
            queue.append(child)

# 사이클이 있는 경우
is_cycle = False
# 1. 맨 처음 부터 in_degree 0이 있는 노드가 없는 경우.
if len(result) == 0:
    is_cycle = True
# 2. in_degree 값이 남아 있는 상태로, 큐 탐색이 끝난 경우
for ele in in_degree:
    if ele != 0:
        is_cycle = True
        break


if not is_cycle:
    for ele in range(1, n + 1):
        if ele not in result:
            result.append(ele)

    for ele in result:
        print(ele)
else:
    print(0)
