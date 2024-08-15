import sys
input = lambda: sys.stdin.readline().rstrip()

a = list(input())
b = list(input())

max_size = max(len(a), len(b))
matrix = [[0 for _ in range(max_size + 1)] for _ in range(max_size + 1)]

# 겹치는 개수는 LCS 알고리즘으로 구함.
for i in range(1, len(a) + 1):
    for j in range(1, len(b) + 1):
        if a[i - 1] == b[j - 1]:
            matrix[i][j] = matrix[i - 1][j - 1] + 1
        else:
            matrix[i][j] = max(matrix[i - 1][j], matrix[i][j - 1])
print(matrix[len(a)][len(b)])

# 최장공통부분수열을 찾는 방법은 맨 밑 오른쪽에서부터 위, 왼으로 따라가면 됨.
col, row = len(a), len(b)
result = []
while col >= 1 and row >= 1:
    target = matrix[col][row]
    # 위, 왼 검사하여 같으면 이동.
    if col - 1 >= 0 and matrix[col - 1][row] == target:
        col -= 1
        continue
    if row - 1 >= 0 and matrix[col][row - 1] == target:
        row -= 1
        continue

    # 둘 다 같은 것이 없다면 문자열을 저장하고 대각으로 이동한다.
    result.append(a[col - 1])
    col -= 1
    row -= 1

result.reverse()
print("".join(map(str, result)))
