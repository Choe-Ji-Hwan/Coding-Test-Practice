// 백준 1043 거짓말
// Gold 4

import java.util.StringTokenizer
import java.util.LinkedList
import kotlin.collections.ArrayDeque

fun main() = with(System.`in`.bufferedReader()) {
    // 입력
    val tokens = StringTokenizer(readLine())
    val (peopleNum, partyNum) = Pair(tokens.nextToken().toInt(), tokens.nextToken().toInt())

    val knownTokens = StringTokenizer(readLine())
    val knownPeopleNum = knownTokens.nextToken().toInt()

    val queue = ArrayDeque<Int>()   // bfs 를 위한 queue
    val known = BooleanArray(peopleNum) // bfs 에서 쓰는 visited 역할
    repeat(knownPeopleNum) {
        val knowPeople = knownTokens.nextToken().toInt() - 1    // 공간 효율을 위해 index 0부터
        queue.addFirst(knowPeople)
        known[knowPeople] = true
    }
    // 체크. 아무도 모른다면, 파티의 개수 그대로 출력
    if (queue.isEmpty()) {
        print(partyNum)
        return
    }
    val graph = Array(peopleNum) { BooleanArray(peopleNum) }    // 2차원 그래프에 link 되어 있으면 true
    val parties = LinkedList<IntArray>()    // 마지막에 결과를 판별하기 위해 파티의 정보 저장
    repeat(partyNum) {
        val partyTokens = StringTokenizer(readLine())
        val partyPeopleNum = partyTokens.nextToken().toInt()
        val partyJoin = IntArray(partyPeopleNum)
        for (i in 0 until partyPeopleNum) {
            partyJoin[i] = partyTokens.nextToken().toInt() - 1
        }
        parties.add(partyJoin)
        for (i in partyJoin) {
            for (j in partyJoin) {
                if (i == j) continue
                graph[i][j] = true
            }
        }
    }
    val resultKnown = bfs(queue, known, graph, peopleNum)
    print(getCountingUndetectedParty(parties, resultKnown))
}

fun bfs(queue: ArrayDeque<Int>, known: BooleanArray, graph: Array<BooleanArray>, peopleNum: Int): BooleanArray {
    while (queue.isNotEmpty()) {
        val people = queue.removeLast()
        for (i in 0 until peopleNum) {
            if (graph[people][i] && !known[i]) {
                queue.addFirst(i)
                known[i] = true
            }
        }
    }
    return known
}

fun getCountingUndetectedParty(parties: LinkedList<IntArray>, resultKnown: BooleanArray): Int {
    var resultCnt = 0
    for (party in parties) {
        var flag = true
        for (people in party) {
            if (resultKnown[people]) {
                flag = false
                break
            }
        }
        if (flag) {
            resultCnt++
        }
    }
    return resultCnt
}
