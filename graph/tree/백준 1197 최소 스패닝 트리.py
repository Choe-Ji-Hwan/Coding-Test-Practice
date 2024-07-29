import sys
input = lambda: sys.stdin.readline().rstrip()

V, E = map(int, input().split())

# 크루스칼 알고리즘 사용.
# cf. 첫 접근은 플로이드 워셜이었는데, 플로이드 워셜은 가중치가 음수가 들어가면 안됨.
edges = []
for _ in range(E):
    a, b, c = map(int, input().split())
    edges.append((a, b, c))
edges.sort(key=lambda x: x[2])  # cost가 작은 것부터 정렬.

# union-find
parent = [i for i in range(V + 1)]


def get_parent(v):
    if parent[v] == v:
        return v

    parent[v] = get_parent(parent[v])    # 거슬러 올라가, 최 루트 부모를 가져옴.
    return parent[v]


# v1이 속한 트리와 v2가 속한 트리를 합친다.
def union_parent(v1, v2):
    p_v1 = get_parent(v1)
    p_v2 = get_parent(v2)

    if p_v1 < p_v2:  # 작은 쪽이 부모가 된다.
        parent[p_v1] = p_v2
    else:
        parent[p_v2] = p_v1


def same_parent(v1, v2):
    return get_parent(v1) == get_parent(v2)


answer = 0
# edges의 cost가 작은 순으로 정렬되어 있음.
for a, b, c in edges:
    # 부모가 같지 않으면, 사이클이 없음.
    if not same_parent(a, b):
        union_parent(a, b)
        answer += c
print(answer)
