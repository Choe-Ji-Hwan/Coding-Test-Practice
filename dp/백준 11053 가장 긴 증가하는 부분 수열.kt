// 백준 11053 가장 긴 증가하는 부분 수열
// Silver 2

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val a = readLine().split(" ").map { it.toInt() }

    val dp = IntArray(n) { 1 }
    for (i in 1 until n) {
        for (j in 0 until i) {
            if (a[j] < a[i]) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
    }
    print(dp.maxOrNull())
}
