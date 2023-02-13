// 백준 15666 N과 M (12)
// Silver 2

fun main() = with(System.`in`.bufferedReader()) {
    val nmList = readLine().split(" ").map { it.toInt() }
    val n = nmList[0]
    val m = nmList[1]

    val integers = readLine().split(" ").map { it.toInt() }.sorted()
    val indexList = mutableListOf<Int>()
    val tempSet = mutableSetOf<String>()
    back(integers, m, indexList, tempSet)
    tempSet.forEach {
        println(it)
    }
}

fun back(integers: List<Int>, m: Int, indexList: MutableList<Int>, tempSet: MutableSet<String>) {
    if (indexList.size == m) {
        tempSet.add(indexList.map { integers[it] }.joinToString(" "))
        return
    }
    for (i in integers.indices) {
        if(indexList.size == 0 || indexList[indexList.size - 1] <= i) {
            indexList.add(i)
            back(integers, m, indexList, tempSet)
            indexList.removeLast()
        }
    }
}
