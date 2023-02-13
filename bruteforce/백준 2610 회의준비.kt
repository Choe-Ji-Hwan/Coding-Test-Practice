// 백준 2610 회의준비
// Gold 2

fun main() = with(System.`in`.bufferedReader()) {
    val peopleNum = readLine().toInt()
    val edgeNum = readLine().toInt()
    val graph = Array(peopleNum) { IntArray(peopleNum) { Int.MAX_VALUE } }

    repeat(edgeNum) {
        val index = readLine().split(" ").map { it.toInt() }
        graph[index[0] - 1][index[1] - 1] = 1
        graph[index[1] - 1][index[0] - 1] = 1
        graph[index[0] - 1][index[0] - 1] = 1
        graph[index[1] - 1][index[1] - 1] = 1
    }
    // 플로이드 와샬
    for (k in 0 until peopleNum) {
        for (i in 0 until peopleNum) {
            for (j in 0 until peopleNum) {
                if (graph[i][k] == Int.MAX_VALUE || graph[k][j] == Int.MAX_VALUE) {
                    continue
                }
                if (graph[i][j] > graph[i][k] + graph[k][j]) {
                    graph[i][j] = graph[i][k] + graph[k][j]
                }
            }
        }
    }
    // 몇개로 나눠지는지
    val awareList = mutableListOf<MutableList<Int>>()
    val visited = BooleanArray(peopleNum)
    for (i in 0 until peopleNum) {
        if (!visited[i]) {
            visited[i] = true
            val aware = mutableListOf<Int>()
            aware.add(i)
            for (j in i + 1 until peopleNum) {
                if (graph[i][j] != Int.MAX_VALUE) {
                    aware.add(j)
                    visited[j] = true
                }
            }
            awareList.add(aware)
        }
    }
    println("${awareList.size}")

    val answer = mutableListOf<Int>()
    // 플로이드 와샬 결과의 합이 가장 작은 index 가 대표 index
    for (aware in awareList) {
        var minSum = Int.MAX_VALUE
        var rep = aware[0]
        for (r in aware) {
            var tempSum = Int.MIN_VALUE
            for (m in aware) {
                if (r != m && graph[r][m] != Int.MAX_VALUE) {
                    tempSum = Math.max(tempSum, graph[r][m])
                }
            }
            if (minSum > tempSum) {
                minSum = tempSum
                rep = r
            }
        }
        answer.add(rep + 1)
    }
    answer.sort()
    print(answer.joinToString("\n"))
}
