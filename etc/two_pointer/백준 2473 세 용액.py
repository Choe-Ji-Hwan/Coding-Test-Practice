import sys
input = lambda: sys.stdin.readline().rstrip()
# 2개는 투포인터로 찾고, 투 포인터 안에 값 안에서 나머지 값을 찾는 식으로 한다면,
# O(5000 * 5000) 정도 나올 것 같다.

result = [0, 0, 0]
n = int(input())
liquid = list(map(int, input().split(" ")))

liquid.sort()
find = False
ret = sys.maxsize

# 총 O(n^2)으로 줄일 수 있음.
for i in range(n):
    another = liquid[i]

    l_i = i + 1
    r_i = n - 1
    while l_i < r_i:
        total = liquid[l_i] + liquid[r_i] + another
        # udpate.
        if abs(total) < ret:
            ret = abs(total)
            result = [liquid[l_i], liquid[r_i], another]

        # adjust.
        if total > 0:
            r_i -= 1
        elif total < 0:
            l_i += 1
        elif total == 0:
            result = [liquid[l_i], liquid[r_i], another]
            find = True
            break

    if find:
        break

result.sort()
print(" ".join(map(str, result)))
