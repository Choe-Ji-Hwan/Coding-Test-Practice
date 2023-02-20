// 백준 1083 소트
// Gold 5

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val a = readLine().split(" ").map { it.toInt() }.toMutableList()
    val s = readLine().toInt()
    println(solution(n, a, s))
}

fun solution(n: Int, a: MutableList<Int>, s: Int): String {
    var count = s
    for (i in 0 until a.size) {
        val lastIndex = if (i + count > a.size - 1) a.size - 1 else i + count
        val maxIndex = findMaxIndex(a, i, lastIndex)
        for (j in maxIndex downTo i + 1) {
            swap(a, j, j - 1)
            count--
        }
    }
    return a.joinToString(" ")
}

fun swap(a: MutableList<Int>, aIndex: Int, bIndex: Int) {
    val temp = a[aIndex]
    a[aIndex] = a[bIndex]
    a[bIndex] = temp
}

fun findMaxIndex(a: MutableList<Int>, startIndex: Int, lastIndex: Int): Int {
    var maxValue = a[startIndex]
    var maxIndex = startIndex
    for (i in startIndex + 1..lastIndex) {
        if (maxValue < a[i]) {
            maxValue = a[i]
            maxIndex = i
        }
    }
    return maxIndex
}
