// 코드의 간편화를 위함, 공간을 더 많이 사용한다고 생각.
// 백준 11726 2xn 타일링
// Silver 3

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    println(solution(n))
}

fun solution(n: Int): Int {
    val fibonacci = mutableListOf<Int>()
    fibonacci.add(1)
    fibonacci.add(1)
    for (i in 2..n + 1) {
        fibonacci.add((fibonacci[i - 1] + fibonacci[i - 2])  % 10_007)
    }
    return fibonacci[n]
}

////////////////////////////////////////////////////////////////////////////////////
//            위 아래 코드 모두 시간과 공간의 차이는 별로 없었음.                  //
////////////////////////////////////////////////////////////////////////////////////

// 코드가 간단하지 않지만, 공간을 덜 사용.
// 백준 11726 2xn 타일링
// Silver 3

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    println(solution(n))
}

fun solution(n: Int): Int {
    if (n == 1) return 1
    val fibonacci = IntArray(3) { 1 }
    var index = 1
    while (true) {
        fibonacci[2] = (fibonacci[0] + fibonacci[1]) % 10_007
        index++
        if (index == n) break
        fibonacci[1] = (fibonacci[2] + fibonacci[0]) % 10_007
        index++
        if (index == n) break
        fibonacci[0] = (fibonacci[1] + fibonacci[2]) % 10_007
        index++
        if (index == n) break
    }
    return when (n % 3) {
        2 -> fibonacci[2]
        1 -> fibonacci[0]
        else -> fibonacci[1]
    }
}
