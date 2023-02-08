// 백준 15652 N과 M (4)
// Silver 3
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val tokens = StringTokenizer(readLine())
    val (n, m) = Pair(tokens.nextToken().toInt(), tokens.nextToken().toInt())

    val nArray = mutableListOf<Int>()
    back(nArray, n, m, 1)
}

fun back(nArray: MutableList<Int>, n: Int, m: Int, start: Int) {
    if (nArray.size == m) {
        println(nArray.joinToString(" "))
        return
    }
    for (i in start until n+1) {
        nArray.add(i)
        back(nArray, n, m, i)
        nArray.removeLast()
    }
}
