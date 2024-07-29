import sys
import heapq
input = lambda: sys.stdin.readline().strip()


def dijkstra(start, dest, num):
    pq = []
    heapq.heappush(pq, (0, start))
    distance = [sys.maxsize for _ in range(num)]
    distance[start] = 0
    route = [-1 for _ in range(num)]
    total_path = []

    while len(pq) != 0:
        (v, node) = heapq.heappop(pq)
        if distance[node] < v:
            continue
        for (n_node, n_value) in graph[node]:
            if distance[n_node] > distance[node] + n_value:
                distance[n_node] = distance[node] + n_value
                route[n_node] = node
                heapq.heappush(pq, (distance[n_node], n_node))

    temp = dest
    while temp != start:
        total_path.append(str(temp + 1))
        temp = route[temp]
    total_path.append(str(start + 1))
    total_path.reverse()

    print(distance[dest])
    print(len(total_path))
    print(" ".join(total_path))


n = int(input())
m = int(input())
graph = [[] for _ in range(n)]
for i in range(m):
    v1, v2, value = map(int, input().split())
    graph[v1 - 1].append([v2 - 1, value])
s, d = map(int, input().split())
dijkstra(s - 1, d - 1, n)
