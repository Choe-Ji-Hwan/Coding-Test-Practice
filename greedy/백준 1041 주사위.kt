// 백준 1041 주사위
// Gold 5

// 4개의 꼭짓점 -> 최소 3개의 면
// 8개의 가장자리(꼭짓점 제외) -> 최소 2개의 면
// 5개의 나머지 면 -> 최소 1개의 면

// n -> 1, (A, B, C, D, E)
// n -> 2, 4 * (A, B, C) + 4 * 1 * (A, B) + 4 * 0 * (A, B) + 0*1*4*(A) + 0*0*(A)
// n -> 3, 4 * (A, B, C) + 4 * 2 * (A, B) + 4 * 1 * (A, B) + 1*2*4*(A) + 1*1*(A)
// n -> 4, 4 * (A, B, C) + 4 * 3 * (A, B) + 4 * 2 * (A, B) + 2*3*4*(A) + 2*2*(A)
// n -> 5, 4 * (A, B, C) + 4 * 4 * (A, B) + 4 * 3 * (A, B) + 3*4*4*(A) + 3*3*(A)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toLong()
    val numbers = readLine().split(" ").map { it.toLong() }

    val minList = mutableListOf<Long>()
    minList.add(Math.min(numbers[0], numbers[5]))
    minList.add(Math.min(numbers[1], numbers[4]))
    minList.add(Math.min(numbers[2], numbers[3]))
    minList.sort()
    val (a, b, c) = Triple(minList[0], minList[1], minList[2])

    if (n == 1L) {
        print(a + b + c + numbers[3] + numbers[4])
        return
    } else {
        val sum1 = 4L * (a + b + c)
        val sum2 = (8L * n - 12L) * (a + b)
        val sum3 = (4L * (n - 2L) * (n - 1L) * a) + ((n - 2L) * (n - 2L) * a)
        print(sum1 + sum2 + sum3)
    }
}
