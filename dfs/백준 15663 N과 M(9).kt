// 백준 15663 N과 M(9)
// Silver 2

fun main() = with(System.`in`.bufferedReader()) {
    val nmList = readLine().split(" ").map { it.toInt() }
    val n = nmList[0]
    val m = nmList[1]

    val integers = readLine().split(" ").map { it.toInt() }.sorted()
    val indexList = mutableListOf<Int>()
    val tempSet = mutableSetOf<String>()
    back(integers, indexList, n, m, tempSet)
    tempSet.forEach {
        println(it)
    }
}

fun back(integers: List<Int>, indexList: MutableList<Int>, n: Int, m: Int, tempSet: MutableSet<String>) {
    if (indexList.size == m) {
        tempSet.add(indexList.map { integers[it] }.joinToString(" "))
        return
    }
    for (i in integers.indices) {
        if (i !in indexList) {
            indexList.add(i)
            back(integers, indexList, n, m, tempSet)
            indexList.removeLast()
        }
    }
}
