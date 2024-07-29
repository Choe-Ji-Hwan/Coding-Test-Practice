import sys
import heapq
input = lambda: sys.stdin.readline().strip()


def dijkstra(start, n):
    pq = []
    distance = [sys.maxsize for _ in range(n)]
    distance[start] = 0
    heapq.heappush(pq, (0, start))

    while len(pq) != 0:
        v, node = heapq.heappop(pq)

        if v > distance[node]:
            continue

        for (n_node, n_value) in graph[node]:
            if distance[n_node] > distance[node] + n_value:
                distance[n_node] = distance[node] + n_value
                heapq.heappush(pq, (distance[n_node], n_node))

    return distance


N, m, r = map(int, input().split(" "))
items = list(map(int, input().split(" ")))

graph = [[] for _ in range(N + 1)]
for i in range(r):
    # 지역 번호 a, b, 길이 value
    a, b, value = map(int, input().split(" "))
    graph[a-1].append((b-1, value))
    graph[b-1].append((a-1, value))


result = 0
for i in range(N):
    temp = 0
    dist = dijkstra(i, N)
    for j in range(len(dist)):
        if dist[j] <= m:
            temp += items[j]
    result = max(result, temp)

print(result)
