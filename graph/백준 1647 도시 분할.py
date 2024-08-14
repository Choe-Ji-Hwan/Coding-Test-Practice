import sys
input = lambda: sys.stdin.readline().strip()


def get_root(node):
    if root[node] == node:
        return node
    p_node = get_root(root[node])
    return p_node


def union_parent(v1, v2):
    p_v1 = get_root(v1)
    p_v2 = get_root(v2)
    # 작은 걸 부모로 선택.
    if p_v1 < p_v2:
        root[p_v1] = p_v2
    else:
        root[p_v2] = p_v1


# 1. 중복 되는 것 중 높은 가중치를 삭제 한다. <- 최소 스패닝 트리. (최소 스패닝 트리에서 크루스칼 알고리즘 잊지말자)
# 2. 가장 큰 가중치의 값을 없앤다.
N, M = map(int, input().split(" "))
edges = []

for _ in range(M):  # O(10^6)
    a, b, c = map(int, input().split(" "))
    edges.append((a, b, c))
edges.sort(key=lambda x: x[2])  # 가중치로 정렬.

# 각 노드의 부모를 저장.
root = [i for i in range(N + 1)]  # 초기화는 자기 자신이 부모이자 자식, 간선이 없는 상태.

selected_values = []
for (a, b, c) in edges:
    # root가 같지 않다면, 사이클 없음.
    if get_root(a) != get_root(b):
        # 가중치 선택
        selected_values.append(c)
        # 이 두 정점을 합친다.
        union_parent(a, b)

print(sum(selected_values) - max(selected_values))
