// 백준 1167 트리의 지름
// Gold 2

import java.util.LinkedList
import java.util.StringTokenizer

const val INPUT_END = -2
const val INITIAL_VALUE = -1

data class Node(val vertex: Int, val cost: Int)
data class Max(var max: Int, var targetVertex: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val vertexNum = readLine().toInt()

    val graph = Array(vertexNum) { LinkedList<Node>() }
    var visited = BooleanArray(vertexNum)
    for (v in 0 until vertexNum) {
        val tokens = StringTokenizer(readLine())
        val startVertex = tokens.nextToken().toInt() - 1
        while (true) {
            val endVertex = tokens.nextToken().toInt() - 1
            if (endVertex == INPUT_END) break
            val cost = tokens.nextToken().toInt()
            graph[startVertex].add(Node(endVertex, cost))
        }
    }
    val max = Max(INITIAL_VALUE, INITIAL_VALUE)
    dfs(graph, visited, 0, 0, max)
    visited = BooleanArray(vertexNum)
    dfs(graph, visited, max.targetVertex, 0, max)
    print(max.max)
}

fun dfs(graph: Array<LinkedList<Node>>, visited: BooleanArray, startVertex: Int, sumCost: Int, max: Max) {
    visited[startVertex] = true
    if (sumCost > max.max) {
        max.max = sumCost
        max.targetVertex = startVertex
    }
    for(nextVertex in graph[startVertex]) {
        if(!visited[nextVertex.vertex]) {
            dfs(graph, visited, nextVertex.vertex, sumCost + nextVertex.cost, max)
        }
    }
}
