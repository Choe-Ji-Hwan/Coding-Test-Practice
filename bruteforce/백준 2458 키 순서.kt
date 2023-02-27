// 백준 2458 키 순서
// Gold 4

fun main() = with(System.`in`.bufferedReader()) {
    val nm = readLine().split(" ").map { it.toInt() }
    val (n, m) = Pair(nm[0], nm[1])
    val graph = Array(n) { IntArray(n) { 0 } }
    repeat(m) {
        val line = readLine().split(" ").map { it.toInt() }
        val (start, end) = Pair(line[0] - 1, line[1] - 1)
        graph[start][end] = 1
    }
    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (graph[i][k] == 1 && graph[k][j] == 1) {
                    graph[i][j] = 1
                }
            }
        }
    }
    graph.forEach {
        it.forEach { t -> print("$t ") }
        println()
    }
    var count = 0
    for (i in 0 until n) {
        var temp = 0
        for (j in 0 until n) {
            if (graph[i][j] != 0 || graph[j][i] != 0) {
                temp++
            }
        }
        if (temp == n - 1) {
            count++
        }
    }
    print(count)
}
