# 백준 1253 좋다
# Gold 4
import sys
from itertools import combinations

input = sys.stdin.readline

n = int(input())
a_list = list(map(int, input().split(" ")))
check_set = {}
for a, b in combinations(range(0, len(a_list)), 2):
    summit = a_list[a] + a_list[b]
    if summit not in check_set:
        check_set[summit] = []
    check_set[summit].append((a, b))

result = 0
for i in range(len(a_list)):
    if a_list[i] in check_set.keys():
        for ele in check_set[a_list[i]]:
            a, b = ele
            if a != i and b != i:
                result += 1
                break

print(result)
