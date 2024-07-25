import java.io.BufferedReader
import java.io.InputStreamReader

fun solution(n: Int, wine: MutableList<Int>): Int {
    val dp = Array(n) { 0 }
    dp[0] = wine[0]
    if (n == 1) {
        return dp[0]
    }
    dp[1] = wine[0] + wine[1]
    if (n == 2) {
        return dp[1]
    }
    dp[2] = Math.max(dp[1], Math.max(wine[0] + wine[2], wine[1] + wine[2]))
    if (n == 3) {
        return dp[2]
    }
    // 3가지 케이스
    // (선택x pass) 마시지 않는 경우, (나, i-1, i-3), (나, i-2)
    for (i in 3 until n) {
        dp[i] = Math.max(
            dp[i - 1],
            Math.max(
                dp[i - 2] + wine[i],
                dp[i - 3] + wine[i - 1] + wine[i]
            )
        )
    }
    return dp[n - 1]
}


fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val wine = mutableListOf<Int>()
    for (i in 0 until n) {
        wine.add(readLine().toInt())
    }
    print(solution(n, wine))
}
