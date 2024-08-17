import sys
import math
input = lambda: sys.stdin.readline().rstrip()
# 누적 합을 사용하는 어려운 방법을 굳이 생각할 필요가 없다. 시간 복잡도가 같다면 사실상 쉬운 방법을 선택.


number = int(input())
if number == 1:
    print(0)
else:
    prime = [True for _ in range(number + 1)]
    prime[1] = False
    for i in range(2, int(math.sqrt(number)) + 1):
        if prime[i]:
            j = 2
            while i * j <= number:
                prime[i*j] = False
                j += 1

    primes = []
    for i in range(2, number + 1):
        if prime[i]:
            primes.append(i)

    answer = 0
    left = 0
    right = 0
    temp_sum = primes[0]     # 초반 0, 0 경우, sum = 2.

    while left <= right or right < len(primes):
        if temp_sum <= number:
            if temp_sum == number:
                answer += 1
            if right >= len(primes) - 1:
                break
            right += 1
            temp_sum += primes[right]
        elif temp_sum > number:
            temp_sum -= primes[left]
            left += 1

    print(answer)
