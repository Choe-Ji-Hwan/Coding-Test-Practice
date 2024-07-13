import sys
input = lambda: sys.stdin.readline().strip()


# ccw algorithm, 한 선분, 한 점에서 한 점이 한 선분의 어느 방향에 있는지 구함.
def ccw(x1, y1, x2, y2, x3, y3):
    return x1*y2 + x2*y3 + x3*y1 - x2*y1 - x3*y2 - x1*y3


x1, y1, x2, y2 = map(int, input().split())
x3, y3, x4, y4 = map(int, input().split())

a = ccw(x1, y1, x2, y2, x3, y3)
b = ccw(x1, y1, x2, y2, x4, y4)
c = ccw(x3, y3, x4, y4, x1, y1)
d = ccw(x3, y3, x4, y4, x2, y2)

# 1-2 선분 기준에 (x3, y3)와 (x4, y4) 점 체크, 서로 반대로 a*b < 0
# 3-4 선분 기준에 (x1, y1)와 (x2, y2) 점 체크, 서로 반대로 c*d < 0
# 둘 다 만족 해야, 크로스 모양 대로 함. 겹치지 않을 때 a*b < 0 일 때, c*d > 0 일 수 있음.
# 세 점이 일직선 위에 있지 않으므로, a*b == 0 or c*d == 0이 될 수 없음.
print(1 if a*b < 0 and c*d < 0 else 0)
