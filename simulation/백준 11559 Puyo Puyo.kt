import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.collections.ArrayDeque

const val COL = 12
const val ROW = 6

val dy = listOf(-1, 1, 0, 0)
val dx = listOf(0, 0, -1, 1)

fun getCollapsePos(startY: Int, startX: Int, board: List<List<Char>>, visited: Array<Array<Boolean>>): List<Pair<Int, Int>> {
    // bfs 이용.
    val queue = ArrayDeque<Pair<Int, Int>>()

    queue.add(Pair(startY, startX))
    val startType = board[startY][startX]
    val find = mutableListOf<Pair<Int, Int>>()

    while(queue.isNotEmpty()) {
        val (y, x) = queue.removeFirst()
        if (visited[y][x]) continue
        visited[y][x] = true
        // 없앨 목록에 추가.
        find.add(Pair(y, x))

        for(i in 0 until 4) {
            val (nY, nX) = Pair(y + dy[i], x + dx[i])
            if (nY in 0 until COL && nX in 0 until ROW
                && !visited[nY][nX] && board[nY][nX] != '.') {
                if (board[nY][nX] == startType) {
                    queue.add(Pair(nY, nX))
                }
            }
        }
    }
    return find
}

fun collapse(board: List<MutableList<Char>>, pos: List<Pair<Int, Int>>) {
    for(p in pos) {
        val (c, r) = p
        board[c][r] = '.'
    }
}

fun reArrange(board: List<MutableList<Char>>) {
    for (r in 0 until ROW) {
        val temp = mutableListOf<Char>()
        for (c in 0 until COL) {
            if (board[c][r] != '.') {
                temp.add(board[c][r])
            }
        }
        var curY = COL - 1
        for (i in temp.size - 1 downTo 0) {
            board[curY][r] = temp[i]
            curY--
        }
        for (i in curY downTo 0) {
            board[i][r] = '.'
        }
    }
}

fun solution(board: List<MutableList<Char>>): Int {
    var repeat = true
    var result = 0
    while (repeat) {
        repeat = false
        val visited = Array(COL) { Array(ROW) { false } }

        for (c in 0 until COL) {
            for (r in 0 until ROW) {
                if (board[c][r] != '.') {
                    val positions = getCollapsePos(c, r, board, visited)
                    if (positions.size >= 4) {
                        collapse(board, positions)
                        // 붕괴시켰으니, 다시 반복 해야 됨.
                        repeat = true
                    }
                }
            }
        }
        // 붕괴 된 적이 있다면, 연쇄 한 번 터트린거임.
        if (repeat) {
            result += 1
        }
        reArrange(board)
    }
    return result
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val board = mutableListOf<MutableList<Char>>()
    for (i in 0 until COL) {
        val line = readLine().toMutableList()
        board.add(line)
    }
    print(solution(board))
}
