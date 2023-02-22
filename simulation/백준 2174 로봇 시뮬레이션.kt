// 백준 2174 로봇 시뮬레이션
// Gold 5

fun main() = with(System.`in`.bufferedReader()) {
    val rowCol = readLine().split(" ").map { it.toInt() }
    val totalRow = rowCol[0]
    val totalCol = rowCol[1]

    val robotCmd = readLine().split(" ").map { it.toInt() }
    val robotNum = robotCmd[0]
    val cmdNum = robotCmd[1]

    val locations = Array(robotNum) { arrayOf<String>() }
    repeat(robotNum) {
        val location = readLine().split(" ").toTypedArray()
        locations[it] = arrayOf(location[0], location[1], location[2])
    }
    val commands = Array(cmdNum) { arrayOf<String>() }
    repeat(cmdNum) {
        val command = readLine().split(" ").toTypedArray()
        commands[it] = arrayOf(command[0], command[1], command[2])
    }
    print(solution(totalRow, totalCol, robotNum, cmdNum, locations, commands))
}

const val NOT_EXIST = -1

data class Robot(var x: Int, var y: Int, var direction: Int)

fun solution(
    a: Int, b: Int, n: Int, m: Int,
    locations: Array<Array<String>>, commands: Array<Array<String>>
): String {

    val directions = mapOf('S' to 0, 'W' to 1, 'E' to 2, 'N' to 3)
    val leftDirections = arrayOf('E', 'S', 'N', 'W')
    val rightDirections = arrayOf('W', 'N', 'S', 'E')

    val maps = Array(b) { IntArray(a) { NOT_EXIST } }
    val robots = arrayOfNulls<Robot>(n)
    for (i in locations.indices) {
        val x = locations[i][0].toInt() - 1
        val y = locations[i][1].toInt() - 1
        robots[i] = Robot(x, y, directions[locations[i][2][0]]!!)
        maps[y][x] = i
    }
    var flag = ""
    for (command in commands) {
        val robotNumber = command[0].toInt() - 1
        val cmd = command[1][0]
        var repeat = command[2].toInt()

        val curRobot = robots[robotNumber]!!
        when (cmd) {
            'L' -> {
                repeat %= 4
                repeat(repeat) {
                    curRobot.direction = directions[leftDirections[curRobot.direction]]!!
                }
            }
            'R' -> {
                repeat %= 4
                repeat(repeat) {
                    curRobot.direction = directions[rightDirections[curRobot.direction]]!!
                }
            }
            'F' -> {
                val curRow = curRobot.x
                val curCol = curRobot.y
                when (curRobot.direction) {
                    0 -> {
                        if (curCol - repeat < 0) {
                            flag = "Robot ${robotNumber + 1} crashes into the wall"
                        }
                        for (i in curCol - 1 downTo if (curCol - repeat < 0) 0 else curCol - repeat) {
                            if (maps[i][curRow] != NOT_EXIST) {
                                for (j in robots.indices) {
                                    if (j != robotNumber && robots[j]!!.y == i && robots[j]!!.x == curRow) {
                                        flag = "Robot ${robotNumber + 1} crashes into robot ${j + 1}"
                                        break
                                    }
                                }
                                if (flag != "") break
                            }
                        }
                        if (flag != "") break
                        curRobot.y = curCol - repeat
                        maps[curCol][curRow] = NOT_EXIST
                        maps[curCol - repeat][curRow] = robotNumber
                    }
                    1 -> {
                        if (curRow - repeat < 0) {
                            flag = "Robot ${robotNumber + 1} crashes into the wall"
                        }
                        for (i in curRow - 1 downTo if (curRow - repeat < 0) 0 else curRow - repeat) {
                            if (maps[curCol][i] != NOT_EXIST) {
                                for (j in robots.indices) {
                                    if (j != robotNumber && robots[j]!!.x == i && robots[j]!!.y == curCol) {
                                        flag = "Robot ${robotNumber + 1} crashes into robot ${j + 1}"
                                        break
                                    }
                                }
                                if (flag != "") break
                            }
                        }
                        if (flag != "") break
                        curRobot.x = curRow - repeat
                        maps[curCol][curRow] = NOT_EXIST
                        maps[curCol][curRow - repeat] = robotNumber
                    }
                    2 -> {
                        if (curRow + repeat >= a) {
                            flag = "Robot ${robotNumber + 1} crashes into the wall"
                        }
                        for (i in curRow + 1..if (curRow + repeat > a) a - 1 else curRow + repeat) {
                            if (maps[curCol][i] != NOT_EXIST) {
                                for (j in robots.indices) {
                                    if (j != robotNumber && robots[j]!!.x == i && robots[j]!!.y == curCol) {
                                        flag = "Robot ${robotNumber + 1} crashes into robot ${j + 1}"
                                        break
                                    }
                                }
                                if (flag != "") break
                            }
                        }
                        if (flag != "") break
                        curRobot.x = curRow + repeat
                        maps[curCol][curRow] = NOT_EXIST
                        maps[curCol][curRow + repeat] = robotNumber
                    }
                    3 -> {
                        if (curCol + repeat >= b) {
                            flag = "Robot ${robotNumber + 1} crashes into the wall"
                        }
                        for (i in curCol + 1..if (curCol + repeat > b) b - 1 else curCol + repeat) {
                            if (maps[i][curRow] != NOT_EXIST) {
                                for (j in robots.indices) {
                                    if (j != robotNumber && robots[j]!!.y == i && robots[j]!!.x == curRow) {
                                        flag = "Robot ${robotNumber + 1} crashes into robot ${j + 1}"
                                        break
                                    }
                                }
                                if (flag != "") break
                            }
                        }
                        if (flag != "") break
                        curRobot.y = curCol + repeat
                        maps[curCol][curRow] = NOT_EXIST
                        maps[curCol + repeat][curRow] = robotNumber
                    }
                }
            }
        }
    }
    return if (flag == "") "OK" else flag
}
