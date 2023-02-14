// 백준 1026 보물
// Silver 4

fun main() = with(System.`in`.bufferedReader()) {
    val listSize = readLine().toInt()
    val aList = readLine().split(" ").map { it.toInt() }.sorted()
    val bList = readLine().split(" ").map { it.toInt() }.sortedDescending()

    var sum = 0
    for (i in 0 until listSize) {
        sum += aList[i] * bList[i]
    }
    print(sum)
}
