// 백준 1446 지름길
// Silver 2

data class Node(val start: Int, val end: Int, val cost: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, d) = readLine().split(" ").map { it.toInt() }.let { Pair(it[0], it[1]) }
    val dp = Array(d + 1) { it }
    val nodes = mutableListOf<Node>()
    repeat(n) {
        nodes.add(readLine().split(" ").map { it.toInt() }.let { Node(it[0], it[1], it[2]) })
    }
    for (i in 0..d) {
        if (i > 0) {
            dp[i] = Math.min(dp[i], dp[i - 1] + 1)
        }
        for((s, e, c) in nodes) {
            if (i == s && e <= d && dp[e] > dp[s] + c) {
                dp[e] = dp[s] + c
            }
        }
    }
    println(dp[d])
}
