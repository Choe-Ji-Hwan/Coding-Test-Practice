import java.io.BufferedReader
import java.io.InputStreamReader

// status는 정렬되어 있음.
fun solution(n: Int, status: List<Int>): String {
    var result = Int.MAX_VALUE
    var resultStart = 0
    var resultEnd = 1

    if (n == 2) {
        return listOf(status[0], status[1]).joinToString(" ")
    }

    var start = 0
    var end = n - 1

    while(start < end) {
        val mix = status[start] + status[end]
        val mixAbs = Math.abs(mix)
        if (result >= mixAbs) {
            result = mixAbs
            resultStart = start
            resultEnd = end
        }

        if (mix > 0) {
            end--
        } else if (mix < 0) {
            start++
        } else {
            return listOf(status[start], status[end]).joinToString(" ")
        }
    }

    return listOf(status[resultStart], status[resultEnd]).joinToString(" ")
}

/**
 * 양의 정수: 산성, 음의 정수: 알칼리성.
 */
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val status = readLine().split(" ").map { it.toInt() }
    println(solution(n, status))
}
