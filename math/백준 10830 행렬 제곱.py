import sys
input = lambda: sys.stdin.readline().strip()


def mul_matrix(matrix1, matrix2, divider):
    matrix1_col = len(matrix1)  # m
    matrix1_row = len(matrix1[0])  # k

    matrix2_col = len(matrix2)  # k
    matrix2_row = len(matrix2[0])  # n

    result = [[0] * matrix2_row for _ in range(matrix1_col)]  # mxn

    for i in range(matrix1_col):
        for j in range(matrix2_row):
            for k in range(matrix1_row):  # == matrix2_col
                result[i][j] += matrix1[i][k] * matrix2[k][j]
                result[i][j] %= divider

    return result


def power_square_matrix(matrix, size, n, divider):
    # 정방 행렬로 초기화
    result = [[0] * size for _ in range(size)]
    for s in range(size):
        result[s][s] = 1

    while n != 0:
        if n % 2 != 0:
            result = mul_matrix(result, matrix, divider)
            n -= 1
        else:
            matrix = mul_matrix(matrix, matrix, divider)
            n //= 2

    return result


N, B = map(int, input().split())

MATRIX = []
for _ in range(N):
    MATRIX.append(list(map(int, input().split())))

result_matrix = power_square_matrix(MATRIX, N, B, 1000)
for i in range(N):
    print(" ".join(map(str, result_matrix[i])))
