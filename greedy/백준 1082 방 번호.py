# 백준 1082 방 번호
# Gold 3
import sys

input = sys.stdin.readline


def solution(n, costs, m):
    cost_dic = dict()
    for i in range(len(costs)):
        cost_dic[i] = costs[i]
    increase_value_dic = sorted(cost_dic.items(), key=lambda x: x[0])
    first, first_cost = min(list(cost_dic.items())[1:], key=lambda x: x[1]) \
        if len(cost_dic) != 1 else min(cost_dic.items(), key=lambda x: x[1])
    second, second_cost = min(cost_dic.items(), key=lambda x: x[1])

    total_cost = m
    answer = []
    if 0 > total_cost - first_cost:
        return 0
    else:
        answer.append(first)
        total_cost -= first_cost

    while total_cost >= second_cost:
        answer.append(second)
        total_cost -= second_cost

    for k in range(len(answer)):
        temp = first_cost if k == 0 else second_cost
        for i in range(len(increase_value_dic) - 1, 0, -1):
            ele, remain_cost = increase_value_dic[i]
            if answer[k] <= ele and total_cost + temp >= remain_cost:
                answer[k] = ele
                total_cost += (temp - remain_cost)
                break

    return int(''.join(map(str, answer)))


a = int(input())
b = list(map(int, input().split(" ")))
c = int(input())

print(solution(a, b, c))
