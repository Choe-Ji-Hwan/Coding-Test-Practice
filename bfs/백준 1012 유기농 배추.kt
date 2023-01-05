// 백준 1012번 유기농 배추
// 배추가 상하좌우 네 방향
// 

import java.util.LinkedList
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()  // 테스트 케이스
    val sb = StringBuilder()

    repeat(t) {
        val tokens = StringTokenizer(readLine())
        val (m, n, k) = Triple(
            tokens.nextToken().toInt(),
            tokens.nextToken().toInt(),
            tokens.nextToken().toInt()
        )   // m: 가로 길이, n: 세로 길이, k: 위치의 개수

        val map = Array(n) { BooleanArray(m) { false } }
        val visited = Array(n) { BooleanArray(m) { false } }

        repeat(k) {
            val mapTokens = StringTokenizer(readLine())
            val (x, y) = Pair(mapTokens.nextToken().toInt(), mapTokens.nextToken().toInt())
            map[y][x] = true
        }

        val count = bfsCount(map, visited, m, n)
        sb.append("$count\n")
    }
    print(sb)
}

fun bfsCount(map: Array<BooleanArray>, visited: Array<BooleanArray>, m: Int, n: Int): Int {
    val dy = listOf(-1, 1, 0, 0)
    val dx = listOf(0, 0, -1, 1)
    var count = 0

    for (x in 0 until m) {
        for (y in 0 until n) {
            if (map[y][x] && visited[y][x].not()) {
                val queue = LinkedList<Pair<Int, Int>>()
                queue.add(Pair(y, x))
                while (queue.isEmpty().not()) {
                    val (col, row) = queue.poll()
                    map[col][row] = false
                    for (i in 0 until 4) {
                        val newCol = col + dy[i]
                        val newRow = row + dx[i]
                        if (newCol in 0 until n && newRow in 0 until m &&
                            map[newCol][newRow] && visited[newCol][newRow].not()
                        ) {
                            queue.add(Pair(newCol, newRow))
                            visited[newCol][newRow] = true
                        }
                    }
                }
                count++
            }
        }
    }
    return count
}
