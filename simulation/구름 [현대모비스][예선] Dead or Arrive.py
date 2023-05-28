import sys
input = sys.stdin.readline

n = int(input())
dic = {}
for i in range(n) :
	v, w = map(int, input().split(" "))
	if dic.get(v) == None:
		dic[v] = (w, i + 1)
	else:
		if dic[v][0] <= w:
			dic[v] = (w, i + 1)
			
result = 0
for key in dic:
	result += dic[key][1]
	
print(result)
