import java.util.StringTokenizer

// 백준 1780 종이의 개수
// Silver 2

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val paper = Array(n) { IntArray(n) { 0 } }
    repeat(n) { i ->
        val tokens = StringTokenizer(readLine())
        repeat(n) { j ->
            paper[i][j] = tokens.nextToken().toInt()
        }
    }
    println(solution(paper, n))
}

fun solution(paper: Array<IntArray>, size: Int): String {
    val result = IntArray(3) { 0 }
    dfs(paper, size, 0, 0, result)
    return result.joinToString("\n")
}

fun dfs(paper: Array<IntArray>, size: Int, col: Int, row: Int, result: IntArray) {
    if (size == 1) {
        when (paper[col][row]) {
            -1 -> result[0] += 1
            0 -> result[1] += 1
            1 -> result[2] += 1
        }
        return
    }
    val first = paper[col][row]
    for (y in col until col + size) {
        for (x in row until row + size) {
            if (first != paper[y][x]) {
                val slice = size / 3
                dfs(paper, slice, col, row, result)
                dfs(paper, slice, col, row + slice, result)
                dfs(paper, slice, col, row + 2 * slice, result)
                dfs(paper, slice, col + slice, row, result)
                dfs(paper, slice, col + slice, row + slice, result)
                dfs(paper, slice, col + slice, row + 2 * slice, result)
                dfs(paper, slice, col + 2 * slice, row, result)
                dfs(paper, slice, col + 2 * slice, row + slice, result)
                dfs(paper, slice, col + 2 * slice, row + 2 * slice, result)
                return
            }
        }
    }
    when (first) {
        -1 -> result[0] += 1
        0 -> result[1] += 1
        1 -> result[2] += 1
    }
}
