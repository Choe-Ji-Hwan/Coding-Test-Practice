import sys
input = lambda: sys.stdin.readline().rstrip()
# 시간 오래 걸린 이유: 문제의 해석 속도가 느렸음 (이건 질문 게시판에서 계속 나오는 이야기)
# 다른 방법으로, union-find를 하려고 해서, 오래 걸림 -> 결국 접근이 제일 중요하다.

# 자기 자신이 부모로 초기화
parent = [i for i in range(500_001)]


def get_parent(node):
    if node == parent[node]:
        return node
    return get_parent(parent[node])


def union_tree(node1, node2):
    parent1 = get_parent(node1)
    parent2 = get_parent(node2)

    if parent1 == parent2:
        return False
    # parents 가 작은 노드를 부모로 설정한다.
    # parent 를 저장하여 parent 탐색 과정을 압축.
    if parent1 < parent2:
        parent[parent2] = parent1
    else:
        parent[parent1] = parent2
    return True


n, m = map(int, input().split(" "))
result = 0
for turn in range(m):
    point1, point2 = map(int, input().split(" "))
    if not union_tree(point1, point2):
        result = turn + 1
        break

print(result)
