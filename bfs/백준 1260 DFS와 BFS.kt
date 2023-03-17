// 백준 1260 DFS와 BFS
import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (nodeNum, edgeNum, start) = readLine().split(" ").map { it.toInt() }.let { Triple(it[0], it[1], it[2] - 1) }
    val graph = Array(nodeNum) { LinkedList<Int>() }
    repeat(edgeNum) {
        val (node, target) = readLine().split(" ").map { it.toInt() }.let { Pair(it[0] - 1, it[1] - 1) }
        graph[node].add(target)
        graph[target].add(node)
    }
    dfs(graph, start, nodeNum)
    println()
    bfs(graph, start, nodeNum)
}

fun dfs(graph: Array<LinkedList<Int>>, start: Int, nodeNum: Int) {
    val stack = LinkedList<Int>()
    val visited = BooleanArray(nodeNum)
    stack.add(start)
    while(stack.isNotEmpty()) {
        val node = stack.removeLast()
        if (visited[node]) continue
        visited[node] = true
        print("${node + 1} ")
        val temp = mutableListOf<Int>()
        for (ele in graph[node]) {
            temp.add(ele)
        }
        stack.addAll(temp.sortedDescending())
    }
}

fun bfs(graph: Array<LinkedList<Int>>, start: Int, nodeNum: Int) {
    val queue = LinkedList<Int>()
    val visited = BooleanArray(nodeNum)
    queue.add(start)
    while(queue.isNotEmpty()) {
        val node = queue.removeFirst()
        if (visited[node]) continue
        visited[node] = true
        print("${node + 1} ")
        val temp = mutableListOf<Int>()
        for (ele in graph[node]) {
            temp.add(ele)
        }
        queue.addAll(temp.sorted())
    }
}
