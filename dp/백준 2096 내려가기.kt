// 백준 2096 내려가기
// Gold 5
import java.util.Collections.max
import java.util.Collections.min

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val maxList = MutableList(3) { 0 }
    val minList = MutableList(3) { 0 }

    val previous = readLine().split(" ").map { it.toInt() }
    for (i in 0 until 3) {
        maxList[i] = previous[i]
        minList[i] = previous[i]
    }
    repeat(n - 1) {
        val line = readLine().split(" ").map { it.toInt() }
        maxList.apply {
            val firstLarge = if (this[0] > this[1]) this[0] else this[1]
            val secondLarge = max(this)
            val thirdLarge = if (this[1] > this[2]) this[1] else this[2]
            this[0] = firstLarge + line[0]
            this[1] = secondLarge + line[1]
            this[2] = thirdLarge + line[2]
        }
        minList.apply {
            val firstSmall = if (this[0] < this[1]) this[0] else this[1]
            val secondSmall = min(this)
            val thirdSmall = if (this[1] < this[2]) this[1] else this[2]
            this[0] = firstSmall + line[0]
            this[1] = secondSmall + line[1]
            this[2] = thirdSmall + line[2]
        }
    }
    print("${max(maxList)} ${min(minList)}")
}
