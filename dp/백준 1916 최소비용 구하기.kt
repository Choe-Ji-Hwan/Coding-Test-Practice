// 백준 1916 최소비용 구하기
// Gold 5
import java.util.LinkedList
import java.util.PriorityQueue

data class Node(val vertex: Int, val cost: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = this.cost - other.cost
}

fun main() = with(System.`in`.bufferedReader()) {
    val cityNum = readLine().toInt()
    val busNum = readLine().toInt()
    val graph = Array(cityNum) { LinkedList<Node>() }
    repeat(busNum) {
        val (start, end, cost) = readLine().split(" ").map { it.toInt() }.let { Triple(it[0], it[1], it[2]) }
        graph[start - 1].add(Node(end - 1, cost))
    }
    val (start, destination) = readLine().split(" ").map { it.toInt() }.let { Pair(it[0], it[1]) }
    print(dijkstra(graph, cityNum, start - 1, destination - 1))
}

fun dijkstra(graph: Array<LinkedList<Node>>, cityNum: Int, start: Int, destination: Int): Int {
    val pq = PriorityQueue<Node>().apply { this.add(Node(start, 0)) }
    val distance = IntArray(cityNum) { Int.MAX_VALUE }.apply { this[start] = 0 }
    val visited = BooleanArray(cityNum)
    while (pq.isNotEmpty()) {
        val curVertex = pq.poll().vertex
        if (visited[curVertex]) continue
        visited[curVertex] = true
        for (next in graph[curVertex]) {
            val (nextVertex, nextCost) = Pair(next.vertex, next.cost)
            if (distance[nextVertex] > distance[curVertex] + nextCost) {
                distance[nextVertex] = distance[curVertex] + nextCost
                pq.add(Node(nextVertex, distance[nextVertex]))
            }
        }
    }
    return distance[destination]
}
