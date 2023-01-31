// 백준 1149 RGB 거리
// Silver 1
import java.lang.Integer.min
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val houseNum = readLine().toInt()
    val coloredHouses = Array(houseNum) { IntArray(3) }
    for (i in 0 until houseNum) {
        val tokens = StringTokenizer(readLine())
        for (j in 0 until 3) {
            coloredHouses[i][j] = tokens.nextToken().toInt()
        }
    }
    for (i in 1 until houseNum) {
        coloredHouses[i][0] = min(
            coloredHouses[i][0] + coloredHouses[i - 1][1], coloredHouses[i][0] + coloredHouses[i - 1][2]
        )
        coloredHouses[i][1] = min(
            coloredHouses[i][1] + coloredHouses[i - 1][0], coloredHouses[i][1] + coloredHouses[i - 1][2]
        )
        coloredHouses[i][2] = min(
            coloredHouses[i][2] + coloredHouses[i - 1][0], coloredHouses[i][2] + coloredHouses[i - 1][1]
        )
    }
    print(min(min(coloredHouses[houseNum-1][0], coloredHouses[houseNum-1][1]), coloredHouses[houseNum-1][2]))
}
