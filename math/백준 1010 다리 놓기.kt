// 백준 1010 다리 놓기
// Silver 5
import java.math.BigInteger

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    repeat(t) {
        var result = BigInteger("1")
        val (west, east) = readLine().split(" ").map { it.toLong() }.let { Pair(it[0], it[1]) }
        val newWest = if (east - west > west) west else east - west
        for (i in 0 until newWest) {
            val target = (east - i).toBigInteger()
            result = result.times(target)
        }
        for (i in newWest downTo 2) {
            result = result.div(i.toBigInteger())
        }
        println(result)
    }
}
