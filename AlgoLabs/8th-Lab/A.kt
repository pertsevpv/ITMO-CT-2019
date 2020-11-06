package lab1

import java.util.*
import kotlin.collections.ArrayList

private var n: Int = 0
private var m: Int = 0
private val sc: Scanner = Scanner(System.`in`)
private lateinit var visited: Array<Boolean>
private lateinit var G: Array<HashSet<Int>>
private val ans: ArrayList<Int> = arrayListOf()

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
    if (check())
        for (i in ans.indices) {
            print("${ans[i] + 1} ")
        }
    else
        print("-1")
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

private fun check(): Boolean {
    val prevs: Array<Boolean> = Array(n) { false }
    for (i in ans.indices) {
        prevs[ans[i]] = true
        for (v in G[ans[i]]) {
            if (prevs[v]) return false
        }
    }
    return true
}