// 백준 1620번 나는야 포켓몬 마스터 이다솜
// Silver 4

import java.lang.StringBuilder
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val numberingDevice = mutableMapOf<Int, String>() // java.util의 HashMap 말고 기존 코틀린의 MutableMap을 이용해도 됨
    val namingDevice = mutableMapOf<String, Int>()
    val st = StringTokenizer(readLine())
    val sb = StringBuilder()
    val (n, m) = Pair(st.nextToken().toInt(), st.nextToken().toInt())

    repeat(n) { index ->
        val pocketMon = readLine()
        numberingDevice[index + 1] = pocketMon
        namingDevice[pocketMon] = index + 1
    }

    repeat(m) {
        val input = readLine()
        if (input[0].isDigit()) { // 정수인지 체크하는 과정이 이것으로 해결 됨. 그러나, 이 문제에서만 적용되는 빠른 경우임
            sb.append("${numberingDevice[input.toInt()]}\n")
        } else {
            sb.append("${namingDevice[input]}\n")
        }
    }

    print(sb)
}
