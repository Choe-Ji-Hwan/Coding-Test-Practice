import sys
input = sys.stdin.readline


def solution(n, numbers):
    for i in range(1, n):
        numbers[i] = max(numbers[i], numbers[i] + numbers[i - 1])
    return max(numbers)


N = int(input())
nums = list(map(int, input().split()))
print(solution(N, nums))
