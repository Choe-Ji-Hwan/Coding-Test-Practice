import java.util.StringTokenizer

// 백준 2630번 색종이 만들기
// Silver 2

fun main() = with(System.`in`.bufferedReader()) {
    val length = readLine().toInt()
    val paperStatus = Array(length) { mutableListOf<Int>() }
    repeat(length) { index ->
        val tokens = StringTokenizer(readLine())
        while (tokens.hasMoreTokens()) {
            paperStatus[index].add(tokens.nextToken().toInt())
        }
    }   // bufferedReader 를 사용할 때는 함수로 분리하면 에러 가능성이 높다.

    val counting = Array(2) { 0 }
    dfs(paperStatus, length, 0, 0, counting)
    println("${counting[0]}\n${counting[1]}")
}

fun dfs(paperStatus: Array<MutableList<Int>>, length: Int, col: Int, row: Int, counting: Array<Int>) {
    if (length == 1) {
        if (paperStatus[col][row] == 0) {
            counting[0] += 1
        } else {
            counting[1] += 1
        }
        return
    }
    val first = paperStatus[col][row]
    for (i in col until (col + length)) {
        for (j in row until (row + length)) {
            if (first != paperStatus[i][j]) {
                dfs(paperStatus, length / 2, col, row, counting)
                dfs(paperStatus, length / 2, col + length / 2, row, counting)
                dfs(paperStatus, length / 2, col, row + length / 2, counting)
                dfs(paperStatus, length / 2, col + length / 2, row + length / 2, counting)
                return
            }
        }
    }
    if (first == 0) {
        counting[0] += 1
    } else {
        counting[1] += 1
    }
}
