// 백준 4485 녹색 옷 입은 애가 젤다지?
// Gold 4
import java.util.LinkedList
import java.util.PriorityQueue

data class Node(val vertex: Int, val cost: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = this.cost - other.cost
}

fun main() = with(System.`in`.bufferedReader()) {
    var problem = 0
    while (true) {
        problem++
        var startCost = -1
        val n = readLine().toInt()
        if (n == 0) break
        val graph = Array(n * n) { LinkedList<Node>() }
        var beforeLine = listOf<Int>()
        for (i in 0 until n) {
            val line = readLine().split(" ").map { it.toInt() }
            if (i == 0) startCost = line[0]
            val step = n * i
            for (j in line.indices) {
                if (step != 0) {
                    graph[step + j - n].add(Node(step + j, line[j]))
                    graph[step + j].add(Node(step + j - n, beforeLine[j]))
                }
                if (j != line.size - 1) {
                    graph[step + j].add(Node(step + j + 1, line[j + 1]))
                    graph[step + j + 1].add(Node(step + j, line[j]))
                }
            }
            beforeLine = line
        }
        println("Problem ${problem}: ${dijkstraLast(graph, 0, n * n) + startCost}")
    }
}

fun dijkstraLast(graph: Array<LinkedList<Node>>, start: Int, size: Int): Int {
    val pq = PriorityQueue<Node>(size).apply {
        this.add(Node(start, 0))
    }
    val distance = MutableList(size) { Int.MAX_VALUE }.apply {
        this[start] = 0
    }
    val visited = BooleanArray(size)

    while (pq.isNotEmpty()) {
        val curNode = pq.poll()
        val curVertex = curNode.vertex
        if (visited[curVertex]) continue
        visited[curVertex] = true

        for (next in graph[curVertex]) {
            if (distance[next.vertex] > distance[curVertex] + next.cost) {
                distance[next.vertex] = distance[curVertex] + next.cost
                pq.add(Node(next.vertex, distance[next.vertex]))
            }
        }
    }
    return distance[size - 1]
}
