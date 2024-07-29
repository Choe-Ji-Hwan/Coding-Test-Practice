// 백준 1068 트리
// Gold 5
import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val nodes = readLine().split(" ").map { it.toInt() }
    val removeNode = readLine().toInt()
    print(solution(n, nodes, removeNode))
}

const val REMOVED = -1

fun solution(n: Int, nodes: List<Int>, removeNode: Int): Int {
    val graph = Array(n) { LinkedList<Int>() }
    for (startNode in nodes.indices) {
        if (nodes[startNode] != -1) {
            graph[nodes[startNode]].add(startNode)
        }
    }
    val leaves = mutableListOf<Int>()
    for (i in 0 until n) {
        if (graph[i].isEmpty()) {
            leaves.add(i)
        }
    }
    removeLinkedNode(graph, leaves, removeNode)
    var result = 0
    for (i in 0 until n) {
        if (graph[i].isEmpty()) {
            result++
        }
    }
    return result
}

fun removeLinkedNode(graph: Array<LinkedList<Int>>, leaves: MutableList<Int>, removeNode: Int) {
    for (node in graph) {
        node.remove(removeNode)
    }

    val queue = LinkedList<Int>()
    queue.add(removeNode)

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        if (graph[node].isEmpty()) {
            leaves.remove(node)
            graph[node].add(REMOVED)
            continue
        }
        queue.addAll(graph[node])
        graph[node].clear()
        graph[node].add(REMOVED)
    }
}
