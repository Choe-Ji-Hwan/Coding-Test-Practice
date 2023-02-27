// 백준 1865 웜홀
// Gold 3

data class Edge(val start: Int, val end: Int, val cost: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val tc = readLine().toInt()
    repeat(tc) {
        val nmw = readLine().split(" ").map { it.toInt() }
        val (n, m, w) = Triple(nmw[0], nmw[1], nmw[2])
        val edges = mutableListOf<Edge>()
        repeat(m) {
            val set = readLine().split(" ").map { it.toInt() }
            val (s, e, t) = Triple(set[0] - 1, set[1] - 1, set[2])
            edges.add(Edge(s, e, t))
            edges.add(Edge(e, s, t))
        }
        repeat(w) {
            val set = readLine().split(" ").map { it.toInt() }
            val (s, e, t) = Triple(set[0] - 1, set[1] - 1, set[2])
            edges.add(Edge(s, e, -t))
        }
        println(if (bellmanFord(edges, n, 0)) "YES" else "NO")
    }
}

fun bellmanFord(edges: MutableList<Edge>, vertexNum: Int, startNode: Int): Boolean {
    val distance = Array(vertexNum) { 100001 }.apply {
        this[startNode] = 0
    }
    for (i in 0 until vertexNum) {
        for (j in edges.indices) {
            val (start, end, cost) = edges[j]
            if (distance[end] > distance[start] + cost) {
                distance[end] = distance[start] + cost
                if (i == vertexNum - 1) {
                    return true
                }
            }
        }
    }
    return false
}
