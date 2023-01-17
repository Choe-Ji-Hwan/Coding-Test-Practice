import java.util.StringTokenizer

// 백준 11399 ATM
// Silver 4

fun main() = with(System.`in`.bufferedReader()) {
    val personNum = readLine().toInt()
    val timeSpends = IntArray(personNum) { 0 }

    val tokens = StringTokenizer(readLine())
    for(i in 0 until personNum) {
        timeSpends[i] = tokens.nextToken().toInt()
    }
    timeSpends.sort()
    var totalTime = 0
    for (i in timeSpends.indices) {
        totalTime += (timeSpends.size - i) * timeSpends[i]
    }
    println(totalTime)
}
