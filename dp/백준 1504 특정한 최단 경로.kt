// 백준 1504 특정한 최단 경로
// Gold 4
import java.lang.Integer.min
import java.util.StringTokenizer
import java.util.LinkedList
import java.util.PriorityQueue

data class Node(val vertex: Int, val cost: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = this.cost - other.cost
}

fun main() = with(System.`in`.bufferedReader()) {
    val tokens = StringTokenizer(readLine())
    val (vertexNum, edgeNum) = Pair(tokens.nextToken().toInt(), tokens.nextToken().toInt())

    val graph = Array(vertexNum) { LinkedList<Node>() }
    repeat(edgeNum) {
        val edgeTokens = StringTokenizer(readLine())
        val (start, target, cost) = Triple(
            edgeTokens.nextToken().toInt() - 1,
            edgeTokens.nextToken().toInt() - 1,
            edgeTokens.nextToken().toInt()
        )
        graph[start].add(Node(target, cost))
        graph[target].add(Node(start, cost))
    }
    val passTokens = StringTokenizer(readLine())
    val (v1, v2) = Pair(passTokens.nextToken().toInt() - 1, passTokens.nextToken().toInt() - 1)

    // dijkstra 총 3번
    var dijkstraTable: MutableList<Int> = dijkstra(graph, 0, vertexNum)
    val startToV1 = dijkstraTable[v1]
    val startToV2 = dijkstraTable[v2]

    dijkstraTable = dijkstra(graph, v1, vertexNum)
    val v1ToV2 = dijkstraTable[v2]
    val v1ToTarget = dijkstraTable[vertexNum - 1]

    dijkstraTable = dijkstra(graph, v2, vertexNum)
    val v2ToTarget = dijkstraTable[vertexNum - 1]

    // 거리의 결과가 못 찾아서, MAX_VALUE 인 경우
    if (v1ToV2 == Integer.MAX_VALUE) {
        print(-1)
    } else if (startToV1 == Integer.MAX_VALUE || v2ToTarget == Integer.MAX_VALUE) {
        if (startToV2 == Integer.MAX_VALUE || v1ToTarget == Integer.MAX_VALUE) {
            print(-1)
        } else {
            print(startToV2 + v1ToV2 + v1ToTarget)
        }
    } else if (startToV2 == Integer.MAX_VALUE || v1ToTarget == Integer.MAX_VALUE) {
        if (startToV1 == Integer.MAX_VALUE || v2ToTarget == Integer.MAX_VALUE) {
            print(-1)
        } else {
            print(startToV1 + v1ToV2 + v2ToTarget)
        }
    } else {
        print(
            min(startToV1 + v1ToV2 + v2ToTarget, startToV2 + v1ToV2 + v1ToTarget)
        )
    }
}

fun dijkstra(graph: Array<LinkedList<Node>>, start: Int, vertexNum: Int): MutableList<Int> {
    val pq = PriorityQueue<Node>().apply {
        this.add(Node(start, 0))
    }
    val dijkstraTable = MutableList(vertexNum) { Integer.MAX_VALUE }.apply {
        this[start] = 0
    }
    val visited = BooleanArray(vertexNum)

    while (pq.isNotEmpty()) {
        val node = pq.poll()
        val vertex = node.vertex
        if (visited[vertex]) continue

        visited[vertex] = true
        for (next in graph[vertex]) {
            val nextVertex = next.vertex
            val nextCost = next.cost
            if (dijkstraTable[nextVertex] > dijkstraTable[vertex] + nextCost) {
                dijkstraTable[nextVertex] = dijkstraTable[vertex] + nextCost
                pq.add(Node(nextVertex, dijkstraTable[nextVertex]))
            }
        }
    }
    return dijkstraTable
}
