import java.util.StringTokenizer
import java.util.LinkedList

// 백준 11723 집합
// Silver 2

fun main() = with(System.`in`.bufferedReader()) {
    val tokens = StringTokenizer(readLine())
    val (nodeNum, edgeNum) = Pair(tokens.nextToken().toInt(), tokens.nextToken().toInt())

    val graph = Array(nodeNum) { BooleanArray(nodeNum) { false } }

    repeat(edgeNum) {
        val edgeTokens = StringTokenizer(readLine())
        val node1 = edgeTokens.nextToken().toInt() - 1
        val node2 = edgeTokens.nextToken().toInt() - 1
        graph[node1][node2] = true
        graph[node2][node1] = true
    }
    var count = 0

    val visited = BooleanArray(nodeNum) { false }
    val queue = LinkedList<Int>()
    for (i in 0 until nodeNum) {
        if (visited[i]) {
            continue
        }
        queue.addFirst(i)
        visited[i] = true
        while (queue.isNotEmpty()) {
            val nextNode = queue.pollLast()
            for(i in 0 until nodeNum) {
                if (visited[i]) continue
                if (graph[nextNode][i]) {
                    queue.addFirst(i)
                    visited[i] = true
                }
            }
        }
        count++
    }
    print(count)
}
