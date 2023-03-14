// 백준 1967 트리의 지름
// Gold 4

data class Node(val vertex: Int, val cost: Int)

var max = -1

fun main() = with(System.`in`.bufferedReader()) {
    val nodeNum = readLine().toInt()
    if (nodeNum == 1) {
        print(0)
        return
    }
    val graph = Array(nodeNum) { ArrayList<Node>() }
    repeat(nodeNum - 1) {
        val (start, end, cost) = readLine().split(" ").map { it.toInt() }.let { Triple(it[0], it[1], it[2]) }
        graph[start - 1].add(Node(end - 1, cost))
    }
    val totalCost = dfs(graph, 0, 0)
    print(if (max > totalCost) max else totalCost)
}

fun dfs(graph: Array<ArrayList<Node>>, nextVertex: Int, cost: Int): Int {
    if (graph[nextVertex].size == 0) {
        return cost
    }
    val costs = mutableListOf<Int>()
    for (next in graph[nextVertex]) {
        costs.add(dfs(graph, next.vertex, next.cost))
    }
    return if (costs.size == 1) {
        cost + costs[0]
    } else {
        costs.sortDescending()
        if (max < costs[0] + costs[1]) {
            max = costs[0] + costs[1]
        }
        cost + costs[0]
    }
}
