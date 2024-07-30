import sys
from itertools import combinations
input = lambda: sys.stdin.readline().strip()


def solution(ground):
    result = sys.maxsize

    chicken = {}
    chicken_num = 0

    house = {}
    house_num = 0
    for i in range(len(ground)):
        for j in range(len(ground)):
            if ground[i][j] == 2:
                chicken[chicken_num] = (i, j)
                chicken_num += 1
            if ground[i][j] == 1:
                house[house_num] = (i, j)
                house_num += 1

    result = sys.maxsize
    for valid_id in combinations(chicken, M):
        step_result = 0
        for h in house.keys():
            h_y, h_x = house[h]
            min_dist = sys.maxsize
            for i in valid_id:
                c_y, c_x = chicken[i]
                distance = abs(h_y - c_y) + abs(h_x - c_x)
                min_dist = min(min_dist, distance)
            step_result += min_dist
        result = min(result, step_result)

    return result


N, M = map(int, input().split(" "))
g = []
for _ in range(N):
    g.append(list(map(int, input().split(" "))))
print(solution(g))
