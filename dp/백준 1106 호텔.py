# 백준 1106 호텔
# Gold 5
import sys

c, n = map(int, input().split(" "))
city = list()
maxi = -1

for i in range(n):
    cost, number = map(int, input().split(" "))
    city.append((number, cost))
    maxi = max(maxi, number)

dp = [sys.maxsize for i in range(c + 1 + maxi)]
dp[0] = 0

for i in range(0, c + 1):
    for ele in city:
        number, cost = ele[0], ele[1]
        if dp[i + number] > dp[i] + cost:
            dp[i + number] = dp[i] + cost

print(min(dp[c:-1]))
