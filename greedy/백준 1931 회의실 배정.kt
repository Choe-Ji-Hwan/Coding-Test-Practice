import java.util.StringTokenizer

// 백준 1931번 회의실 배정
// Silver 1

data class Meeting(val start: Int, val end: Int)

fun main() = with(System.`in`.bufferedReader()) {

    val meetingNum = readLine().toInt()
    val meetings = mutableListOf<Meeting>()

    repeat(meetingNum) {
        val tokens = StringTokenizer(readLine())
        meetings.add(Meeting(tokens.nextToken().toInt(), tokens.nextToken().toInt()))
    }
    meetings.sortWith(compareBy(Meeting::end).thenBy(Meeting::start))

    var count = 1
    var targetIndex = 0
    // 조건 체크할 때,
    //5
    //4 4
    //4 4
    //1 4
    //3
    // 와 같이 나오도록, (1 4) -> (4 4) -(동시에)> (4 4) 이렇게 3개가 가능하다고 생각해야 됨
    // 시작시간과 종료 시간이 같은 회의들이 중복되어도 회의실 사용 가능, 이상하지만 이게 답이 맞음
    for (i in 1 until meetings.size) {
        if (meetings[targetIndex].end <= meetings[i].start) {
            targetIndex = i
            count++
        }
    }
    print(count)
}
