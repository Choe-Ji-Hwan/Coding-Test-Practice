// 백준 15650 N과 M
// Silver 3
import java.util.StringTokenizer
import java.lang.StringBuilder

fun main() = with(System.`in`.bufferedReader()) {
    val tokens = StringTokenizer(readLine())
    val (n, m) = Pair(tokens.nextToken().toInt(), tokens.nextToken().toInt())
    val array = IntArray(m)
    val sb = StringBuilder()
    combination(array, 0, n, m, 1, sb)
    print(sb)
}

fun combination(arr: IntArray, index: Int, n: Int, r: Int, target: Int, sb: StringBuilder) {
    when {
        r == 0 -> printCompleted(arr, index, sb)
        target == n + 1 -> return
        else -> {
            arr[index] = target
            combination(arr, index + 1, n, r - 1, target + 1, sb)
            combination(arr, index, n, r, target + 1, sb)
        }
    }
}

fun printCompleted(arr: IntArray, length: Int, sb: StringBuilder) {
    for (i in 0 until length) {
        sb.append("${arr[i]} ")
    }
    sb.append("\n")
}
