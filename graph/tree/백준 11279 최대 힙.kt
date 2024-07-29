// 백준 11279번 최대 힙
// Silver 2
// 라이브러리 사용보다는, 실제 원리를 구현해보는 것이 목표

fun main() = with(System.`in`.bufferedReader()) {
    val repeatNum = readLine().toInt()
    val sb = StringBuilder()

    val maxHeapWithArray = Array(100_001) { 0 }
    var size = 0

    repeat(repeatNum) {
        val x = readLine().toInt()
        if (x > 0) {
            size = maxHeapWithArray.add(x, size)
        } else {
            if (size <= 0) {
                sb.append("0\n")
            } else {
                sb.append("${maxHeapWithArray.getPeek()}\n")
                size = maxHeapWithArray.deleteMaxValue(size)
            }
        }
    }
    print(sb)
}

fun Array<Int>.add(value: Int, size: Int): Int {
    this[size + 1] = value
    heapifyWithArrayAfterAdd(this, size + 1)
    return size + 1
}

fun heapifyWithArrayAfterAdd(heap: Array<Int>, targetIndex: Int) {
    if (targetIndex == 1) {
        return
    }
    if (heap[targetIndex] > heap[targetIndex / 2]) {
        swap(heap, targetIndex, targetIndex / 2)
        heapifyWithArrayAfterAdd(heap, targetIndex / 2)
    }
}

fun Array<Int>.deleteMaxValue(size: Int): Int {
    this[1] = this[size]
    heapifyWithArrayAfterDelete(this, size - 1, 1)
    return size - 1
}

fun heapifyWithArrayAfterDelete(heap: Array<Int>, size: Int, targetIndex: Int) {
    if (2 * targetIndex > size) {
        return
    }
    val leftIndex = 2 * targetIndex
    val rightIndex = 2 * targetIndex + 1

    if (heap[targetIndex] >= heap[leftIndex] && heap[targetIndex] >= heap[rightIndex]) {
        return
    }
    if (heap[leftIndex] < heap[rightIndex]) {
        swap(heap, targetIndex, rightIndex)
        heapifyWithArrayAfterDelete(heap, size, rightIndex)
    } else {
        swap(heap, targetIndex, leftIndex)
        heapifyWithArrayAfterDelete(heap, size, leftIndex)
    }
}

fun swap(heap: Array<Int>, index1: Int, index2: Int) {
    val temp = heap[index1]
    heap[index1] = heap[index2]
    heap[index2] = temp
}


fun Array<Int>.getPeek() = this[1]
