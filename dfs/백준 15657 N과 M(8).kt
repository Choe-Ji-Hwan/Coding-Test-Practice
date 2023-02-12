// 백준 15657 N과 M(8)
// Silver 3

fun main() = with(System.`in`.bufferedReader()) {
    val nmList = readLine().split(" ").map { it.toInt() }
    val n = nmList[0] // <= 8
    val m = nmList[1] // <= 8
    val integers = readLine().split(" ").map { it.toInt() }.sorted()
    val tempList = mutableListOf<Int>()

    back(n, m, integers, tempList)
}

fun back(n: Int, m: Int, integers: List<Int>, tempList: MutableList<Int>) {
    if (tempList.size == m) {
        println(tempList.joinToString(" "))
        return
    }
    for (i in integers.indices) {
        if (tempList.size == 0 || tempList[tempList.size - 1] <= integers[i]) {
            tempList.add(integers[i])
            back(n, m, integers, tempList)
            tempList.removeLast()
        }
    }
}
