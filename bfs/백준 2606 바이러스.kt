import java.util.StringTokenizer
import java.util.LinkedList

// 백준 2606번 바이러스
// Silver 3

fun main() = with(System.`in`.bufferedReader()) {
    val computerNum = readLine().toInt()    // <= 100
    val linkNum = readLine().toInt()
    val network = Array(computerNum) { mutableSetOf<Int>() }

    repeat(linkNum) {
        val tokens = StringTokenizer(readLine())
        val (point1, point2) = Pair(tokens.nextToken().toInt() - 1, tokens.nextToken().toInt() - 1)
        network[point1].add(point2)
        network[point2].add(point1)
    }

    val isVirus = Array(computerNum) { false }
    val queue = LinkedList<Int>()
    queue.addLast(0)    // 1번 넣기
    isVirus[0] = true   // 1번은 걸림
    var count = 0

    while (queue.isNotEmpty()) {
        val computer = queue.pollFirst()
        for (com in network[computer]) {
            if (!isVirus[com]) {
                queue.addLast(com)
                isVirus[com] = true
                count++
            }
        }
    }
    print(count)
}
