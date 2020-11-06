import java.util.*
import kotlin.collections.HashSet
import kotlin.math.min

private val sc = Scanner(System.`in`)
private var n = 0
private var m = 0
private var time = 0
private lateinit var G: Array<HashSet<Int>>
private lateinit var visited: Array<Boolean>
private lateinit var Up: Array<Int>
private lateinit var In: Array<Int>
private val cutPoints: TreeSet<Int> = TreeSet()

private fun main() {
    n = sc.nextInt()
    m = sc.nextInt()
    G = Array(n) { hashSetOf<Int>() }
    for (i in 0 until m) {
        val u = sc.nextInt() - 1
        val v = sc.nextInt() - 1
        G[u].add(v)
        G[v].add(u)
    }
    findCutPoints()
    println(cutPoints.size)
    for (i in cutPoints)
        print("${i + 1} ")
}

private fun findCutPoints() {
    visited = Array(n) { false }
    In = Array(n) { 0 }
    Up = Array(n) { 0 }
    for (u in 0 until n)
        if (visited[u].not()) dfs(u)
}

private fun dfs(v: Int, p: Int = -1) {
    time++
    In[v] = time
    Up[v] = time
    visited[v] = true
    var count = 0
    for (u in G[v]) {
        if (u == p) continue
        if (visited[u]) Up[v] = min(Up[v], In[u])
        else {
            dfs(u, v)
            count++
            Up[v] = min(Up[v], Up[u])
            if (p != -1 && Up[u] >= In[v]) cutPoints.add(v)
        }
    }
    if (p == -1 && count >= 2) cutPoints.add(v)
}