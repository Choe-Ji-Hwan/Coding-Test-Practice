import sys
input = lambda: sys.stdin.readline().strip()
# 문제 해결에 오래 걸린 이유: "연속되는 부분합" <- 연속을 놓침.

N, M = map(int, input().split(" "))
nums = list(map(int, input().split(" ")))

left, right = 0, 0
summit = 0
min_length = sys.maxsize

if nums.count(M) != 0:
    print(1)    # <- 이 말도 놓침. 연속적인 건데, 1번 연속한 것도 연속한 거다라는 말 뜻이 있음.
else:
    # while 문의 탈출 조건을 True에서, summit < M 일 경우다.
    # True 대신 right <= len(nums)를 조건으로 종료한다면, 마지막에 min_length를 더하지 못할 것.
    while True:
        if summit < M:
            if right == len(nums):
                break
            summit += nums[right]
            right += 1
        elif summit >= M:
            min_length = min(min_length, right - left)
            summit -= nums[left]
            left += 1

    print(min_length if min_length != sys.maxsize else 0)
