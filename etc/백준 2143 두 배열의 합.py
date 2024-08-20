import sys
from bisect import bisect_left, bisect_right
input = lambda: sys.stdin.readline().rstrip()
# 탐색을 위한 이분 탐색을 이용한다면 빠를 것이라는 판단 필요.
# 누적한 합을 모두 체크 하는 것은 n^2는 무조건 할 수 밖에 없는 구조. dp로 생각하면서 시간이 걸렸음.

# 입력 부
T = int(input())
n = int(input())
a = list(map(int, input().split(" ")))
m = int(input())
b = list(map(int, input().split(" ")))


def find_num(nums, target):
    left = bisect_left(nums, target)
    right = bisect_right(nums, target)
    return right - left


# a의 누적합
a_sum = []
for i in range(n):
    temp = a[i]
    a_sum.append(temp)
    for j in range(i + 1, n):
        temp += a[j]
        a_sum.append(temp)

# b의 누적합
temp = 0
b_sum = []
for i in range(m):
    temp = b[i]
    b_sum.append(temp)
    for j in range(i + 1, m):
        temp += b[j]
        b_sum.append(temp)

# 이분 탐색을 위한 정렬.
a_sum.sort()
b_sum.sort()
count = 0
for i in range(len(a_sum)):
    count += find_num(b_sum, T - a_sum[i])
print(count)
