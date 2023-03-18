// 백준 2206 벽 부수고 이동하기
// Gold 3
import java.util.LinkedList

data class Point(val y: Int, val x: Int, val isBreak: Int)

fun main() = with(System.`in`.bufferedReader()) {
    // init
    val (n, m) = readLine().split(" ").map { it.toInt() }.let { Pair(it[0], it[1]) }
    val roads = Array(n) { IntArray(m) }
    val visited = Array(n) { Array(m) { IntArray(2) } }
    for (i in 0 until n) {
        val line = readLine().toList().map { it.code - 48 }
        for (j in 0 until m) {
            roads[i][j] = line[j]
        }
    }
    // bfs
    print(bfs(roads, n, m, visited))
}

fun bfs(roads: Array<IntArray>, n: Int, m: Int, visited: Array<Array<IntArray>>): Int {
    val dy = listOf(0, 0, 1, -1)
    val dx = listOf(1, -1, 0, 0)

    val queue = LinkedList<Point>()
    queue.add(Point(0, 0, 0))
    visited[0][0][0] = 1

    while (queue.isNotEmpty()) {
        val (curY, curX, curIsBreak) = queue.poll()
        if (curY == n - 1 && curX == m - 1) {
            return visited[curY][curX][curIsBreak]
        }
        for (i in 0 until 4) {
            val newY = curY + dy[i]
            val newX = curX + dx[i]
            if (newY in 0 until n && newX in 0 until m ) {
                if (roads[newY][newX] == 0 && visited[newY][newX][curIsBreak] == 0) {
                    visited[newY][newX][curIsBreak] = visited[curY][curX][curIsBreak] + 1
                    queue.add(Point(newY, newX, curIsBreak))
                } else if (roads[newY][newX] == 1 && curIsBreak == 0) {
                    visited[newY][newX][1] = visited[curY][curX][curIsBreak] + 1
                    queue.add(Point(newY, newX, 1))
                }
            }
        }
    }
    return -1
}
