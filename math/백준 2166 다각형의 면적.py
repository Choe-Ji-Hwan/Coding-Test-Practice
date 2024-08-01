import sys
input = lambda: sys.stdin.readline().strip()


# 각 점을 알 때, 넓이 구하기.
# 신발끈 공식.
# 0번 째, (y, x) 좌표를 기준으로 삼각형으로 나눠서 더한다.
def shoelace_formula(x, y, n):
    answer = 0.0
    for i in range(2, n):
        answer += (x[0]*y[i - 1] + x[i - 1]*y[i] + x[i]*y[0])
        answer -= (x[i - 1]*y[0] + x[i]*y[i - 1] + x[0]*y[i])

    # 외적을 이용, 넓이는 방향성 지우고 스칼라 형식 반환.
    return abs(answer / 2)


N = int(input())
x_p = []
y_p = []
for _ in range(N):
    t_x, t_y = map(int, input().split(" "))
    x_p.append(t_x)
    y_p.append(t_y)

print(round(shoelace_formula(x_p, y_p, N), 1))
