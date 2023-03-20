// 백준 11660 구간 합 구하기 5
// Silver 1

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val grid = Array(n + 1) { IntArray(n + 1) }
    val sb = StringBuilder()
    repeat(n) { i ->
        val line = readLine().split(" ").map { it.toInt() }
        for (j in 0 until n) {
            grid[i + 1][j + 1] = line[j]
        }
    }
    for (i in 0 until n) {  // (1, 1) 부터 (i, j)까지의 누적합 그래프로 변경
        for (j in 0 until n) {
            grid[i + 1][j + 1] = grid[i][j + 1] + grid[i + 1][j] - grid[i][j] + grid[i + 1][j + 1]
        }
    }
    repeat(m) {
        val line = readLine().split(" ").map { it.toInt() }
        val (x1, y1) = Pair(line[0], line[1])
        val (x2, y2) = Pair(line[2], line[3])
        sb.append("${grid[y2][x2] - grid[y2][x1 - 1] - grid[y1 - 1][x2] + grid[y1 - 1][x1 - 1]}\n")
    }
    print(sb)
}
