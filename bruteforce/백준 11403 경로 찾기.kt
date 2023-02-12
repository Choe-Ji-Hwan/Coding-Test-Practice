// 백준 11403 경로 찾기
// Silver 1

fun main() = with(System.`in`.bufferedReader()) {
    // 입력
    val vertexNum = readLine().toInt()

    val graph = Array(vertexNum) { IntArray(vertexNum) { Int.MAX_VALUE } }
    repeat(vertexNum) { start ->
        val links = readLine().split(" ").map { it.toInt() }
        for (i in links.indices) {
            graph[start][i] = if (links[i] == 0) Int.MAX_VALUE else links[i]
        }
    }
    // 플로이드 와샬
    for (k in 0 until vertexNum) {
        for (i in 0 until vertexNum) {
            for (j in 0 until vertexNum) {
                if (graph[i][k] == Int.MAX_VALUE || graph[k][j] == Int.MAX_VALUE) {
                    continue
                }
                if (graph[i][j] > graph[i][k] + graph[k][j]) {
                    graph[i][j] = graph[i][k] + graph[k][j]
                }
            }
        }
    }
    // 출력
    graph.forEach { col ->
        col.forEach {
            print("${if (it == Int.MAX_VALUE) 0 else 1} ")
        }
        println()
    }
}
