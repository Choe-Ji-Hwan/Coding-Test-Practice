// 백준 1956 운동
// Gold 4

fun main() = with(System.`in`.bufferedReader()) {
    val veList = readLine().split(" ").map { it.toInt() }
    val v = veList[0]
    val e = veList[1]

    val graph = Array(v) { IntArray(v) { Int.MAX_VALUE } }
    repeat(e) {
        val edgeList = readLine().split(" ").map { it.toInt() }
        val (start, end, cost) = Triple(edgeList[0] - 1, edgeList[1] - 1, edgeList[2])
        graph[start][end] = cost
    }
    // 플로이드 마샬
    for (k in 0 until v) {
        for (i in 0 until v) {
            for (j in 0 until v) {
                if (graph[i][k] == Int.MAX_VALUE || graph[k][j] == Int.MAX_VALUE) {
                    continue
                }
                if (graph[i][j] > graph[i][k] + graph[k][j]) {
                    graph[i][j] = graph[i][k] + graph[k][j]
                }
            }
        }
    }
    var min = Int.MAX_VALUE
    for (i in 0 until v) {
        for (j in 0 until v) {
            if (i == j || graph[i][j] == Int.MAX_VALUE || graph[j][i] == Int.MAX_VALUE) {
                continue
            }
            val totalCost = graph[i][j] + graph[j][i]
            min = Math.min(min, totalCost)
        }
    }
    print("${if (min == Int.MAX_VALUE) -1 else min}")
}
