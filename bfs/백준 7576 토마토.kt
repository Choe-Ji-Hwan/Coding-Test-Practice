import java.util.StringTokenizer
import java.util.LinkedList

// 백준 7576번 토마토
// Gold 2

const val RIPE = 1
const val UNRIPE = 0
const val WALL = -1

data class TomatoStatus(val col: Int, val row: Int, val time: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val tokens = StringTokenizer(readLine())
    val (row, col) = Pair(tokens.nextToken().toInt(), tokens.nextToken().toInt())
    var ripeTomatoCnt = 0
    var unripeTomatoCnt = 0

    val tomatoBox = Array(col) { Array(row) { 0 } }
    val visited = Array(col) { Array(row) { false } }
    val queue = LinkedList<TomatoStatus>()

    for (i in 0 until col) {
        val tomatoTokens = StringTokenizer(readLine())
        for (j in 0 until row) {
            val status = tomatoTokens.nextToken().toInt()
            when (status) {
                RIPE -> {
                    ripeTomatoCnt += 1
                    queue.addFirst(TomatoStatus(i, j, 0))   // 이미 익은 토마토 큐에 담음.
                    visited[i][j] = true
                }
                UNRIPE -> {
                    unripeTomatoCnt += 1
                }
                WALL -> {
                    visited[i][j] = true
                }
            }
            tomatoBox[i][j] = status
        }
    }
    if (unripeTomatoCnt == 0) {
        print(0)
        return
    }
    if (ripeTomatoCnt == 0) {
        print(-1)
        return
    }
    print(getRipeDaysBfs(tomatoBox, visited, col, row, queue))
}

fun getRipeDaysBfs(
    tomatoBox: Array<Array<Int>>,
    visited: Array<Array<Boolean>>,
    maxCol: Int,
    maxRow: Int,
    queue: LinkedList<TomatoStatus>
): Int {
    var days = 0
    val dy = listOf(-1, 1, 0, 0)
    val dx = listOf(0, 0, -1, 1)
    while (queue.isNotEmpty()) {
        val (col, row, times) = queue.pollLast()
        days = times
        for (i in 0 until 4) {
            val newCol = col + dy[i]
            val newRow = row + dx[i]
            if ((newCol < 0 || newCol >= maxCol) || (newRow < 0 || newRow >= maxRow)) {
                continue
            }
            if (visited[newCol][newRow]) {
                continue
            }
            visited[newCol][newRow] = true
            queue.addFirst(TomatoStatus(newCol, newRow, times + 1))
        }
    }
    for (i in 0 until maxCol) {
        if (visited[i].contains(false)) {
            return -1
        }
    }
    return days
}
