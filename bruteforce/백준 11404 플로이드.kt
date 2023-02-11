// 백준 11404 플로이드
// Gold 4
import java.lang.Integer.min

fun main() = with(System.`in`.bufferedReader()) {
    val vertexNum = readLine().toInt()
    val edgeNum = readLine().toInt()

    val connect = Array(vertexNum) { IntArray(vertexNum) { Int.MAX_VALUE } }

    repeat(edgeNum) {
        val stc = readLine().split(" ").map { it.toInt() }
        val start = stc[0] - 1
        val target = stc[1] - 1
        val cost = stc[2]
        connect[start][target] = min(connect[start][target], cost)
    }
    repeat(vertexNum) {
        connect[it][it] = 0
    }

    // 플로이드 - 와샬
    for (k in 0 until vertexNum) { // 경유지
        for (i in 0 until vertexNum) {  // 시작점
            for (j in 0 until vertexNum) {  // 도착점
                if (connect[i][k] == Int.MAX_VALUE || connect[k][j] == Int.MAX_VALUE) {
                    continue
                }
                if (connect[i][j] > connect[i][k] + connect[k][j]) {
                    connect[i][j] = connect[i][k] + connect[k][j]
                }
            }
        }
    }

    // 출력
    connect.forEach { vertexLinks ->
        vertexLinks.forEach {
            print("${if (it == Int.MAX_VALUE) 0 else it} ")
        }
        println()
    }
}
