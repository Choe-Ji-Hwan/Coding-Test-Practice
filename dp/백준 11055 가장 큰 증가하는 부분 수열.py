import sys
input = sys.stdin.readline


def solution(n, sequence):
    # 누적합 dp
    dp = [0] * n
    dp[0] = sequence[0]

    for i in range(1, n):
        # 2중 포문을 쓰는 이유: 연속된 수가 아니라, 하나씩 체크해야 한다.
        # O(n^2)인데, 최대 10^6 반복 -> 1초 이하.
        for j in range(i):
            if sequence[j] < sequence[i]:
                # dp[i]나, [~j까지 더한 누적 합 + 현재 값]으로 갱신.
                dp[i] = max(dp[i], dp[j] + sequence[i])
            else:
                # 본인 자체가 가장 큰 합일 수 있다(단일)
                dp[i] = max(dp[i], sequence[i])

    return max(dp)


N = int(input())
num_list = list(map(int, input().split()))
print(solution(N, num_list))
