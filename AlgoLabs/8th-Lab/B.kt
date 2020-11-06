import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

private var n: Int = 0
private var m: Int = 0
private val sc: Scanner = Scanner(System.`in`)
private lateinit var G: Array<HashSet<Int>>
private val edgeMap: HashMap<Pair<Int, Int>, Int> = hashMapOf()
private lateinit var visited: Array<Boolean>
private var time = 0
private lateinit var In: Array<Int>
private lateinit var Up: Array<Int>
private val bridges: TreeSet<Int?> = TreeSet()

private fun main() {
    n = sc.nextInt()
    m = sc.nextInt()
    G = Array(n) { hashSetOf<Int>() }
    for (i in 0 until m) {
        val u = sc.nextInt()-1
        val v = sc.nextInt()-1
        G[u].add(v)
        G[v].add(u)
        edgeMap[Pair(min(u, v), max(u, v))] = i + 1
    }
    findBridges()
    println(bridges.size)

    for (u in bridges) print("$u ")
}

private fun findBridges() {
    visited = Array(n) { false }
    In = Array(n) { 0 }
    Up = Array(n) { 0 }
    for (u in 0 until n) {
        if (!visited[u]) dfs(u)
    }
}

private fun dfs(u: Int, par: Int = -1) {
    visited[u] = true
    In[u] = time
    Up[u] = time++
    for (v in G[u]) {
        if (v == par)continue
        if (visited[v])
            Up[u] = min(Up[u], In[v])
        else {
            dfs(v, u)
            Up[u] = min(Up[u], Up[v])
            if (Up[v] > In[u])
                bridges.add(edgeMap[Pair(min(u, v), max(u, v))])
        }

    }
}