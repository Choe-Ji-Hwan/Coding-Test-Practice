// 백준 1613 역사
// Gold 3

fun main() = with(System.`in`.bufferedReader()) {
    // 입력
    val vertexEdge = readLine().split(" ").map { it.toInt() }
    val vertexNum = vertexEdge[0]
    val edgeNum = vertexEdge[1]

    val graph = Array(vertexNum) { BooleanArray(vertexNum) }

    repeat(edgeNum) {
        val startTarget = readLine().split(" ").map { it.toInt() }
        val start = startTarget[0] - 1
        val target = startTarget[1] - 1

        graph[start][target] = true
    }

    // 플로이드 와샬
    for (k in 0 until vertexNum) {
        for (i in 0 until vertexNum) {
            for (j in 0 until vertexNum) {
                if (!graph[i][k] || !graph[k][j]) {
                    continue
                }
                if (graph[i][k] && graph[k][j]) {
                    graph[i][j] = true
                }
            }
        }
    }

    // 결과
    val answerNum = readLine().toInt()
    repeat(answerNum) {
        val startTarget = readLine().split(" ").map { it.toInt() }
        val start = startTarget[0] - 1
        val target = startTarget[1] - 1

        println("${if (graph[start][target]) -1 else if (graph[target][start]) 1 else 0}")
    }
}
