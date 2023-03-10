// 백준 2447 별 찍기 - 10
// Gold 5

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    print(solution(n))
}

fun solution(n: Int): String {
    val pattern = Array(n) { Array(n) { " " } }
    divided(pattern, 0, 0, n)
    val sb = StringBuilder()
    for (ele in pattern) {
        sb.append("${ele.joinToString("")}\n")
    }
    return sb.toString()
}

fun divided(pattern: Array<Array<String>>, col: Int, row: Int, divideCnt: Int) {
    if (divideCnt == 1) {
        pattern[col][row] = "*"
        return
    }
    val step = divideCnt / 3
    for (i in 0 until divideCnt step step) {
        for (j in 0 until divideCnt step step) {
            if (i in step until 2 * step && j in step until 2 * step) {
                continue
            }
            divided(pattern, col + i, row + j, divideCnt / 3)
        }
    }
}
