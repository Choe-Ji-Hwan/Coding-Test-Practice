# 백준 5639 이진 검색 트리
# Gold 5
import sys

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

nums = list()

while True:
    try:
        nums.append(int(input()))
    except:
        break


def dfs(start_idx, end_idx):
    if start_idx > end_idx:
        return
    if start_idx == end_idx:
        print(nums[start_idx])
        return
    idx = start_idx + 1
    while idx <= end_idx:
        if nums[start_idx] < nums[idx]:
            break
        idx += 1

    dfs(start_idx + 1, idx - 1)
    dfs(idx, end_idx)
    print(nums[start_idx])


dfs(0, len(nums) - 1)
