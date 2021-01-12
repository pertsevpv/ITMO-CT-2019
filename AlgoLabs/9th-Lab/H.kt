import java.io.File
import java.io.FileWriter
import java.util.*
import kotlin.collections.ArrayList

private val inp: File = File("game.in")
private val outp: File = File("game.out")
private val writer: FileWriter = FileWriter(outp)
private var n: Int = 0
private var m: Int = 0
private var s: Int = 0
private val sc: Scanner = Scanner(inp)
private lateinit var visited: Array<Boolean>
private lateinit var G: Array<HashSet<Int>>
private val ans: ArrayList<Int> = arrayListOf()
private lateinit var game: Array<Int>

private fun main() {
    n = sc.nextInt()
    m = sc.nextInt()
    s = sc.nextInt() - 1
    G = Array(n) { hashSetOf<Int>() }
    visited = Array(n) { false }

    for (i in 0 until m) {
        val u = sc.nextInt() - 1
        val v = sc.nextInt() - 1
        G[u].add(v)
    }
    topsort()
    game = Array(n) { -1 }
    for (u in ans.reversed()) {
        if (G[u].isEmpty()) {
            game[u] = 0;
        } else {
            for (v in G[u]) {
                if (game[v] == 0) {
                    game[u] = 1
                    break
                }
            }
            if (game[u] == -1) game[u] = 0;
        }
    }
    if (game[s] == 0) writer.write("Second player wins")
    else writer.write("First player wins")
    writer.flush()
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