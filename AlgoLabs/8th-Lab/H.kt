import java.io.File
import java.io.FileWriter
import java.util.*
import kotlin.collections.HashSet

private var n = 0;
private val inputFile: File = File("avia.in")
private val outputFile: File = File("avia.out")
private val sc: Scanner = Scanner(inputFile)
private val writer: FileWriter = FileWriter(outputFile)
private lateinit var G: Array<HashSet<Pair<Int, Int>>>
private lateinit var H: Array<HashSet<Pair<Int, Int>>>
private lateinit var visited: Array<Boolean>
private lateinit var weights: Array<Int>
private val tmp: HashSet<Int> = hashSetOf()

private fun main() {
    n = sc.nextInt()
    G = Array(n) { hashSetOf<Pair<Int, Int>>() }
    H = Array(n) { hashSetOf<Pair<Int, Int>>() }
    for (i in 0 until n) {
        for (j in 0 until n) {
            val w = sc.nextInt()
            if (i == j) continue
            tmp.add(w)
            G[i].add(Pair(j, w))
            H[j].add(Pair(i, w))
        }
    }
    if (n == 1) {
        writer.write(0)
        writer.flush()
        writer.close()
        return
    }
    weights = Array(tmp.size) { -1 }
    tmp.toArray(weights)
    weights.sort()
    var l: Int = -1
    var r = n
    while (r - l > 1) {
        visited = Array(n) { false }
        val m = (r + l) / 2
        val w = weights[m]
        val countG = dfs(0, w, G)
        if (countG == n) {
            visited = Array(n) { false }
            val countH = dfs(0, w, H)
            if (countH == n)
                r = m
            else
                l = m
        } else l = m
    }
    writer.write(weights[r].toString())
    writer.flush()
    writer.close()
}

private fun dfs(v: Int, w: Int, A: Array<HashSet<Pair<Int, Int>>>): Int {
    visited[v] = true
    var count = 1;
    for (u in A[v]) {
        if (!visited[u.first]) {
            if (u.second <= w) count += dfs(u.first, w, A)
        }
    }
    return count
}