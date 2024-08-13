import sys
input = lambda: sys.stdin.readline().strip()
sys.setrecursionlimit(1_000_000)    # 파이썬이 정한 리커전 최대 깊이가 다르므로, 백만 정도 설정.


N, R, Q = map(int, input().split(" "))
node = [[] for _ in range(N + 1)]
size = [0 for _ in range(N + 1)]

for _ in range(N - 1):
    U, V = map(int, input().split(" "))
    node[U].append(V)
    node[V].append(U)


def count_subtree(current_node, visited):
    if visited[current_node]:
        return
    size[current_node] = 1
    visited[current_node] = True
    for child in node[current_node]:
        if not visited[child]:
            count_subtree(child, visited)
            size[current_node] += size[child]


v = [False for _ in range(N + 1)]
count_subtree(R, v)

for i in range(Q):
    find_node = int(input())
    print(size[find_node])
