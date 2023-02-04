// 백준 1238 파티
// Gold 3
import java.util.StringTokenizer
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Collections.max

data class Node(val vertex: Int, val cost: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = cost - other.cost
}

fun main() = with(System.`in`.bufferedReader()) {
    val tokens = StringTokenizer(readLine())
    val (studentNum, edgeNum, targetNode) = Triple(
        tokens.nextToken().toInt(),
        tokens.nextToken().toInt(),
        tokens.nextToken().toInt() - 1
    )
    val graph = Array(studentNum) { LinkedList<Node>() }
    for (i in 0 until edgeNum) {
        val edgeTokens = StringTokenizer(readLine())
        val (startNode, endNode, cost) = Triple(
            edgeTokens.nextToken().toInt() - 1,
            edgeTokens.nextToken().toInt() - 1,
            edgeTokens.nextToken().toInt()
        )
        graph[startNode].add(Node(endNode, cost))
    }
    val totalCosts = MutableList(studentNum) { 0 }
    for (startNode in 0 until studentNum) {
        totalCosts[startNode] = dijkstra(graph, startNode, targetNode, studentNum)
        totalCosts[startNode] += dijkstra(graph, targetNode, startNode, studentNum)
    }
    print(max(totalCosts))
}

fun dijkstra(graph: Array<LinkedList<Node>>, startNode: Int, targetNode: Int, studentNum: Int): Int {
    val pq = PriorityQueue<Node>().apply {
        this.add(Node(startNode, 0))
    }
    val distance = IntArray(studentNum) { Int.MAX_VALUE }.apply {
        this[startNode] = 0
    }
    val visited = BooleanArray(studentNum)

    while (pq.isNotEmpty()) {
        val cur = pq.poll()
        val curNode = cur.vertex
        if(visited[curNode]) continue
        visited[curNode] = true

        for(next in graph[curNode]) {
            val nextNode = next.vertex
            val nextCost = next.cost
            if(distance[nextNode] > distance[curNode] + nextCost) {
                distance[nextNode] = distance[curNode] + nextCost
                pq.add(Node(nextNode, distance[nextNode]))
            }
        }
    }
    return distance[targetNode]
}
