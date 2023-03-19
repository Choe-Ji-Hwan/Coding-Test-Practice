// 백준 2263 트리의 순회
// Gold 2

fun main() = with(System.`in`.bufferedReader()) {
    val nodeNum = readLine().toInt()
    val inOrder = readLine().split(" ").map { it.toInt() }
    val postOrder = readLine().split(" ").map { it.toInt() }
    val position = IntArray(nodeNum + 1)
    for (i in 0 until nodeNum) {
        position[inOrder[i]] = i
    }
    val sb = StringBuilder()
    getPreOrder(sb, position, inOrder, 0, nodeNum - 1, postOrder, 0, nodeNum - 1)
    print(sb)
}

fun getPreOrder(
    sb: StringBuilder, position: IntArray,
    inOrder: List<Int>, inStart: Int, inEnd: Int,
    postOrder: List<Int>, postStart: Int, postEnd: Int
) {
    if (postStart > postEnd || inStart > inEnd) {
        return
    }
    val root = postOrder[postEnd]
    val rootIndex = position[root]
    val left = rootIndex - inStart
    sb.append("$root ")
    getPreOrder(sb, position, inOrder, inStart, rootIndex - 1, postOrder, postStart, postStart + left - 1)
    getPreOrder(sb, position, inOrder, rootIndex + 1, inEnd, postOrder, postStart + left, postEnd - 1)
}
