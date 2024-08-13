import sys
input = lambda: sys.stdin.readline().strip()

# 약수/배수가 나올 때 시간 초과가 난다면, 에라토스테네스의 체를 생각해서, 모든 element들로 생각을 바꾼다.
check = [0 for _ in range(1_000_001)]
N = int(input())    # 10^5
cards = []
max_num = -1
for ele in map(int, input().split(" ")):
    max_num = max(max_num, ele)
    cards.append(ele)
    check[ele] = 1


scores = [0 for _ in range(max_num + 1)]

# 10^5 * (10^5 - 1) / 2
for i in range(N):
    # 에라토스테네스의 체처럼, 배수가 있는지 체크 한다.
    for j in range(cards[i] * 2, max_num + 1, cards[i]):
        if check[j] == 1:
            scores[cards[i]] += 1
            scores[j] -= 1

for ele in range(N):
    print(scores[cards[ele]], end=" ")
