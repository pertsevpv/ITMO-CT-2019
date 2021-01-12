import java.util.*
import kotlin.collections.ArrayList

private var n: Int = 0
private var m: Int = 0
private val sc: Scanner = Scanner(System.`in`)
private lateinit var visited: Array<Boolean>
private lateinit var G: Array<HashSet<Int>>
private val ans: ArrayList<Int> = arrayListOf()
private lateinit var grandi: Array<Int>

private fun main() {
    n = sc.nextInt()
    m = sc.nextInt()
    G = Array(n) { hashSetOf<Int>() }
    visited = Array(n) { false }

    for (i in 0 until m) {
        val u = sc.nextInt() - 1
        val v = sc.nextInt() - 1
        G[u].add(v)
    }
    topsort()
    grandi = Array(n) { -1 }
    for (u in ans.reversed()) {

            val list: TreeSet<Int> = TreeSet()
            for (v in G[u]) list.add(grandi[v])
            grandi[u] = mex(list)
    }
    for (g in grandi){
        println(g)
    }
}

private fun topsort() {
    for (u in 0 until n) {
        if (!visited[u]) {
            dfs(u)
        }
    }
    ans.reverse()
}

private fun dfs(u: Int) {
    visited[u] = true
    for (v in G[u]) {
        if (!visited[v]) {
            dfs(v)
        }
    }
    ans.add(u)
}

private fun mex(list: TreeSet<Int>): Int {
    var min = 0
    for (c in list) {
        if (min == c) min++
        else break
    }
    return min;
}