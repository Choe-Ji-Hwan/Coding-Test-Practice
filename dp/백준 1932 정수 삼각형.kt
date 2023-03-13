// 백준 1932 정수 삼각형
// Silver 1

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val line = ArrayList<IntArray>()
    for (i in 0 until n) {
        line.add(readLine().split(" ").map { it.toInt() }.toIntArray())
    }
    for (i in 1 until n) {
        for (j in 0..i) {
            when (j) {
                0 -> line[i][j] += line[i - 1][0]
                i -> line[i][j] += line[i - 1][j - 1]
                else -> line[i][j] += Math.max(line[i - 1][j - 1], line[i - 1][j])
            }
        }
    }
    print(line[n-1].maxOrNull())
}
