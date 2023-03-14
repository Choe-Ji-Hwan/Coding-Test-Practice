// 백준 1195 킥다운
// Gold 5

var minSize = Int.MAX_VALUE

fun main() = with(System.`in`.bufferedReader()) {
    val firstGear = readLine().toList().map { it.code - 48 }
    val secondGear = readLine().toList().map { it.code - 48 }
    minSize = firstGear.size + secondGear.size
    val (longGear, shortGear) = if (firstGear.size > secondGear.size)
        Pair(firstGear, secondGear)
    else Pair(secondGear, firstGear)
    solution(longGear, shortGear)
    print(minSize)
}

fun solution(longGear: List<Int>, shortGear: List<Int>) {
    compareOutSide(shortGear, longGear)    // 외부에서 들어올 때
    // 중간에서 비교
    for (i in 0 until longGear.size - shortGear.size) {
        var isPossible = true
        for (j in shortGear.indices) {
            if (shortGear[j] + longGear[j + i] > 3) {
                isPossible = false
                break
            }
        }
        if (isPossible) {
            if (minSize > longGear.size) {
                minSize = longGear.size
                return
            }
        }
    }
    compareOutSide(longGear, shortGear)    // 외부에서 나갈 때
}

fun compareOutSide(targetGear: List<Int>, movingGear: List<Int>) {
    for (i in targetGear.indices) {
        var isPossible = true
        if (i >= movingGear.size) break
        for (j in 0..i) {
            if (targetGear[j] + movingGear[movingGear.size - 1 - i + j] > 3) {
                isPossible = false
                break
            }
        }
        if (isPossible) {
            if (minSize > movingGear.size + targetGear.size - (i + 1)) {
                minSize = movingGear.size + targetGear.size - (i + 1)
            }
        }
    }
}
