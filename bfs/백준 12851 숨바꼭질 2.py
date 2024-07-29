import sys
from collections import deque
input = lambda: sys.stdin.readline().strip()
MAX_SIZE = 100001


def solution(n, k):
    if n == k:
        print("0\n1")
        return

    q = deque([])
    # 지점
    q.append(n)
    way = [0 for _ in range(MAX_SIZE)]
    result = 0
    count = 0
    while len(q) != 0:
        p = q.popleft()
        if p == k:
            result = way[p]
            count += 1
        for n_p in [p-1, p+1, 2*p]:
            if 0 <= n_p < MAX_SIZE and (way[n_p] == 0 or way[n_p] == way[p] + 1):
                way[n_p] = way[p] + 1
                q.append(n_p)

    print(result)
    print(count)


N, K = map(int, input().split(" "))
solution(N, K)
