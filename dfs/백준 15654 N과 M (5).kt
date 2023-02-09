// 백준 15654 N과 M (5)
// Silver 3

import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val nmToken = StringTokenizer(readLine())
    val (n, m) = Pair(nmToken.nextToken().toInt(), nmToken.nextToken().toInt())

    val nArray = readLine().split(" ").map { it.toInt() }.sorted()
    val tempArray = mutableListOf<Int>()
    back(nArray, tempArray, n, m, 0)
}

fun back(nArray: List<Int>, tempArray: MutableList<Int>, n: Int, m: Int, start: Int) {
    if (start == m) {
        print(tempArray.joinToString(" "))
        println()
    } else {
        for (ele in nArray) {
            if (ele !in tempArray) {
                tempArray.add(ele)
                back(nArray, tempArray, n, m, start + 1)
                tempArray.removeLast()
            }
        }
    }
}
