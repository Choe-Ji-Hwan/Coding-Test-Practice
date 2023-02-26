// 백준 2407 조합
// Sliver 3

import java.math.BigInteger

fun main() = with(System.`in`.bufferedReader()) {
    val nm = readLine().split(" ").map { it.toLong() }
    val (n, m) = Pair(nm[0], if (nm[0] - nm[1] > nm[1]) nm[1] else nm[0] - nm[1])
    var result = BigInteger.ONE
    for (i in 1..m) {
        result = result.times(BigInteger.valueOf(n- i + 1))
    }
    for (i in 1..m) {
        result = result.div(BigInteger.valueOf(i))
    }
    print(result)
}
