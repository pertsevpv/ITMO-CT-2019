import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


private val sc = Scanner(System.`in`)
private var n = 0
private var m = 0
private var col = 0
private lateinit var G: Array<HashSet<Int>>
private lateinit var H: Array<HashSet<Int>>
private lateinit var nG: Array<HashSet<Int>>
private lateinit var visited: Array<Boolean>
private lateinit var component: Array<Int>
private val ord: ArrayList<Int> = arrayListOf()

private fun main() {
    n = sc.nextInt()
    m = sc.nextInt()
    G = Array(n) { hashSetOf<Int>() }
    H = Array(n) { hashSetOf<Int>() }
    for (i in 0 until m) {
        val u = sc.nextInt() - 1
        val v = sc.nextInt() - 1
        if (u == v)continue
        G[u].add(v)
        H[v].add(u)
    }
    visited = Array(n) { false }
    component = Array(n) { -1 }
    for (u in 0 until n) {
        if (visited[u].not())
            dfs1(u)
    }
    for (u in ord.size - 1 downTo 0) {
        val to = ord[u]
        if (component[to] == -1) {
            col++
            dfs2(to)
        }
    }
    nG = Array(col) { hashSetOf<Int>() }
    for (i in 0 until n) {
        for (u in G[i]) {
            if (component[i] == component[u]) continue
            nG[component[i]].add(component[u])
        }
    }
    var count = 0
    for (set in nG) count += set.size
    print(count)
}

private fun dfs1(v: Int) {
    visited[v] = true
    for (u in G[v]) {
        if (visited[u].not())
            dfs1(u)
    }
    ord.add(v)
}

private fun dfs2(v: Int) {
    component[v] = col - 1
    for (u in H[v]) {
        if (component[u] == -1)
            dfs2(u)
    }
}