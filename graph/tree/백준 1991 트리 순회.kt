// 백준 1991 트리 순회
// Silver 1
const val NOTHING = -19
const val ASCII_A = 65

fun main() = with(System.`in`.bufferedReader()) {
    val nodeNum = readLine().toInt()
    val nodes = Array(26) { IntArray(2) }
    repeat(nodeNum) {
        val (vertex, left, right) = readLine().split(" ").map { it[0].code - ASCII_A }
        nodes[vertex][0] = left
        nodes[vertex][1] = right
    }
    val sb = StringBuilder()
    preOrder(sb, nodes, 0)
    sb.append("\n")

    inOrder(sb, nodes, 0)
    sb.append("\n")

    postOrder(sb, nodes, 0)
    print(sb)
}

fun preOrder(sb: StringBuilder, nodes: Array<IntArray>, start: Int) {
    sb.append((start + ASCII_A).toChar())
    val left = nodes[start][0]
    val right = nodes[start][1]
    if (left != NOTHING) {
        preOrder(sb, nodes, left)
    }
    if (right != NOTHING) {
        preOrder(sb, nodes, right)
    }
}

fun inOrder(sb: StringBuilder, nodes: Array<IntArray>, start: Int) {
    val left = nodes[start][0]
    val right = nodes[start][1]
    if (left != NOTHING) {
        inOrder(sb, nodes, left)
    }
    sb.append((start + ASCII_A).toChar())
    if (right != NOTHING) {
        inOrder(sb, nodes, right)
    }
}

fun postOrder(sb: StringBuilder, nodes: Array<IntArray>, start: Int) {
    val left = nodes[start][0]
    val right = nodes[start][1]
    if (left != NOTHING) {
        postOrder(sb, nodes, left)
    }
    if (right != NOTHING) {
        postOrder(sb, nodes, right)
    }
    sb.append((start + ASCII_A).toChar())
}
