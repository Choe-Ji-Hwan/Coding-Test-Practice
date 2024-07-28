import sys
from collections import deque
input = lambda: sys.stdin.readline().strip()


def check():
    if len(stack) >= len(bomb):
        for i in range(len(bomb)):
            if bomb[len(bomb) - i - 1] != stack[len(stack) - i - 1]:
                return False
        return True
    else:
        return False


target = list(input())
bomb = list(input())
stack = deque([])

for s in target:
    stack.append(s)
    while check():
        for _ in range(len(bomb)):
            stack.pop()

print("".join(stack) if len(stack) != 0 else "FRULA")
