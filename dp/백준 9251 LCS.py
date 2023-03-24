# 백준 9251 LCS
# Gold 5
import sys

input = sys.stdin.readline

first = list(input().rstrip())
second = list(input().rstrip())

matrix = [[0 for _ in range(len(first) + 1)] for _ in range(len(second) + 1)]

for y in range(1, len(second) + 1):
    for x in range(1, len(first) + 1):
        if second[y - 1] == first[x - 1]:
            matrix[y][x] = matrix[y - 1][x - 1] + 1
        else:
            matrix[y][x] = max(matrix[y - 1][x], matrix[y][x - 1])

print(matrix[-1][-1], end="")
