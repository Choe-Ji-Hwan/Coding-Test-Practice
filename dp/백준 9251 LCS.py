# 백준 9251 LCS
# Gold 5
import sys

input = sys.stdin.readline

first = list(input().rstrip())
second = list(input().rstrip())
max_size = max(len(first), len(second))

matrix = [[0 for _ in range(max_size + 1)] for _ in range(max_size + 1)]

for i in range(1, len(first) + 1):
    for j in range(1, len(second) + 1):
        if first[i - 1] == second[j - 1]:
            matrix[i][j] = matrix[i - 1][j - 1] + 1
        else:
            matrix[i][j] = max(matrix[i - 1][j], matrix[i][j - 1])

print(matrix[len(first)][len(second)])
