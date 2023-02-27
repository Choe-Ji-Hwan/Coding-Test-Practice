// 백준 1135 뉴스 전하기
// Gold 2
import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val peoples = readLine().split(" ").map { it.toInt() }
    print(solution(n, peoples))
}

fun solution(n: Int, peoples: List<Int>): Int {
    val graph = Array(n) { LinkedList<Int>() }
    for (i in 1 until peoples.size) {
        graph[peoples[i]].add(i)
    }
    return dfs(graph, 0)
}

fun dfs(graph: Array<LinkedList<Int>>, node: Int): Int {
    if (graph[node].isEmpty()) {
        return 0
    }
    val sortedResult = mutableListOf<Int>()
    for (ele in graph[node]) {
        sortedResult.add(dfs(graph, ele))
    }
    sortedResult.sortDescending()
    var max = sortedResult[0] + 1
    for (i in 1 until sortedResult.size) {
        max = Math.max(max, sortedResult[i] + i + 1)
    }
    return max
}
