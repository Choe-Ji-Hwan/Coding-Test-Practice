import sys
input = lambda: sys.stdin.readline().strip()


n = int(input())
a = list(map(int, input().split(" ")))
m = int(input())
b = list(map(int, input().split(" ")))
answer = []

while len(a) != 0 and len(b) != 0:
    max_a, max_a_idx = max(a), a.index(max(a))
    max_b, max_b_idx = max(b), b.index(max(b))

    if max_a == max_b:
        answer.append(max_a)
        a = a[max_a_idx + 1:]
        b = b[max_b_idx + 1:]

    elif max_a > max_b:
        a.pop(max_a_idx)
    else:
        b.pop(max_b_idx)

print(len(answer))
print(" ".join(map(str, answer)))
