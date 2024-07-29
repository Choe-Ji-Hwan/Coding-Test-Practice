import java.util.StringTokenizer
import java.util.TreeMap

// 백준 7662번 이중순위 큐
// Gold 4

const val MAX = 1
const val MIN = -1

fun main() = with(System.`in`.bufferedReader()) {
    val repeatNum = readLine().toInt()
    val sb = StringBuilder()

    repeat(repeatNum) {
        val testCaseNum = readLine().toInt()
        val treeMap = TreeMap<Int, Int>() // TreeMap은 red-black tree에 key에 맞춰 정렬. 트리로 정렬되어 저장됨
        repeat(testCaseNum) {
            val st = StringTokenizer(readLine())
            val (cmd, value) = Pair(st.nextToken(), st.nextToken().toInt())
            when (cmd) {
                "I" -> {
                    treeMap[value] = treeMap.getOrDefault(value, 0) + 1
                }
                "D" -> {
                    if (treeMap.isNotEmpty()) {
                        val key = if (value == MAX) treeMap.lastKey() else if (value == MIN) treeMap.firstKey() else 0
                        val keyCount = treeMap[key]
                        if (keyCount == 1) {
                            treeMap.remove(key)
                        } else {
                            treeMap[key] = (keyCount ?: 0) - 1
                        }
                    }
                }
            }
        }

        if (treeMap.isEmpty()) {
            sb.append("EMPTY\n")
        } else {
            sb.append("${treeMap.lastKey()} ${treeMap.firstKey()}\n")
        }
    }
    print(sb)
}
