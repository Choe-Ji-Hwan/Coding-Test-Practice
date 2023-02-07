// 백준 1629 곱셈
// Silver 1
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val tokens = StringTokenizer(readLine())
    val (a, b, c) = Triple(tokens.nextToken().toLong(), tokens.nextToken().toLong(), tokens.nextToken().toLong())

    print(powAndMod(a, b, c))
}

// a^n = a^(n/2) * a^(n/2) (단, n 이 짝수)
// a^n = a^(n/2) * a^(n/2) * a (단, n이 홀수)
// 모듈러 합동 공식: (a*b)%c = (a%c * b%c) % c

fun powAndMod(a: Long, b: Long, c: Long): Long {
    return if (b == 1L) {
        a % c
    } else {
        val temp = powAndMod(a, b / 2, c)
        if (b % 2 == 1L) {
            // (a * temp * temp) % c
            // (a%c * (temp * temp)%c) % c
            // (a * temp%c*temp%c)%c
            // (temp * temp % c * a)% c
            ((temp * temp % c) * a) % c
        } else {
            temp * temp % c
        }
    }
}
