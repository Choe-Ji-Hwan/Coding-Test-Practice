import sys
input = sys.stdin.readline
# N-Queen 문제는 유명한 문제로, 백트래킹으로 모두 살펴 봐야지 알 수 있다, 규칙 같은 건 없음.
result = 0


def check(chess, start):
    for i in range(start):
        if chess[start] == chess[i] or abs(chess[start] - chess[i]) == abs(start - i):
            return False
    return True


def dfs(chess, start, n):
    global result

    if start == n:
        result += 1
    else:
        for i in range(n):
            chess[start] = i
            if check(chess, start):
                dfs(chess, start + 1, n)
    return


def solution(n):
    global result
    # 하나의 열이 index, 값이 행을 나타냄.
    chess = [0] * n
    dfs(chess, 0, n)

    return result


N = int(input())
print(solution(N))
