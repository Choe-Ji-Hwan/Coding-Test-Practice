// 백준 1753 최단 경로
// Gold 4

import java.util.PriorityQueue

data class Node(val vertex: Int, val cost: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = this.cost - other.cost
}

fun main() = with(System.`in`.bufferedReader()) {
    val ve = readLine().split(" ").map { it.toInt() }
    val (v, e) = Pair(ve[0], ve[1])

    val startNode = readLine().toInt() - 1
    val graph = Array(v) { PriorityQueue<Node>() }
    repeat(e) {
        val line = readLine().split(" ").map { it.toInt() }
        val (vertex, target, cost) = Triple(line[0] - 1, line[1] - 1, line[2])
        graph[vertex].add(Node(target, cost))
    }
    val startDistance = dijkstra(graph, startNode, v)
    startDistance.forEach {
        println("${if (it == Int.MAX_VALUE) "INF" else it}")
    }
}

fun dijkstra(graph: Array<PriorityQueue<Node>>, startNode: Int, vNum: Int): Array<Int> {
    val pq = PriorityQueue<Node>().apply {
        this.add(Node(startNode, 0))
    }
    val distance = Array(vNum) { Int.MAX_VALUE }.apply {
        this[startNode] = 0
    }
    val visited = BooleanArray(vNum)

    while(pq.isNotEmpty()) {
        val curNode = pq.poll()
        val curVertex = curNode.vertex
        if(visited[curVertex]) continue
        visited[curVertex] = true
        for (next in graph[curVertex]) {
            if (distance[next.vertex] > distance[curVertex] + next.cost) {
                distance[next.vertex] = distance[curVertex] + next.cost
                pq.add(Node(next.vertex, distance[next.vertex]))
            }
        }
    }
    return distance
}
