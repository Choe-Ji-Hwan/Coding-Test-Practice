// 백준 15650 N과 M (2)
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

////////////////////// 풀이 2 ////////////////////////

import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val tokens = StringTokenizer(readLine())
    val (n, m) = Pair(tokens.nextToken().toInt(), tokens.nextToken().toInt())

    // nCr = (n-1)C(r-1) * (n-1)Cr
    val nArray = IntArray(n) { it + 1 }
    val visited = BooleanArray(n)
    combination(nArray, visited, n, m, 0)
}

fun combination(nArray: IntArray, visited: BooleanArray, n: Int, m: Int, depth: Int) {
    if (m == 0) {
        for (i in 0 until n) {
            if(visited[i]) {
                print("${nArray[i]} ")
            }
        }
        println()
    } else if (nArray.size == depth) {
        return
    } else {
        visited[depth] = true
        combination(nArray, visited, n, m-1, depth + 1)
        visited[depth] = false
        combination(nArray, visited, n, m, depth + 1)
    }
}
