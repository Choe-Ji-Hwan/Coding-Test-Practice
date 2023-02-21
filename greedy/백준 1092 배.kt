// 백준 1092 배
// Gold 5

fun main() = with(System.`in`.bufferedReader()) {
    val craneNum = readLine().toInt()
    val weightLimits = readLine().split(" ").map { it.toInt() }
    val boxNum = readLine().toInt()
    val boxWeights = readLine().split(" ").map { it.toInt() }

    print(solution(craneNum, weightLimits, boxNum, boxWeights))
}

fun solution(craneNum: Int, weightLimits: List<Int>, boxNum: Int, boxWeights: List<Int>): Int {
    var answer = 0
    val sortedWeightLimits = weightLimits.sortedDescending()
    val sortedBoxWeights = boxWeights.sortedDescending().toMutableList()

    if (sortedBoxWeights[0] > sortedWeightLimits[0]) {
        return -1
    }
    var newCraneNum = craneNum
    for (ele in sortedWeightLimits) {
        if (sortedBoxWeights[sortedBoxWeights.size - 1] > ele) {
            newCraneNum--
        }
    }

    var craneIndex = 0
    var boxIndex = 0

    while (sortedBoxWeights.isNotEmpty()) {
        if (craneIndex >= newCraneNum || boxIndex >= sortedBoxWeights.size) {
            answer++
            craneIndex = 0
            boxIndex = 0
        }
        if (sortedWeightLimits[craneIndex] >= sortedBoxWeights[boxIndex]) {
            craneIndex++
            sortedBoxWeights.removeAt(boxIndex)
        } else {
            boxIndex++
            continue
        }
    }
    return answer + 1
}
