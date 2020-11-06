import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

private val sc = Scanner(System.`in`)
private var n = 0
private var m = 0
private lateinit var G: ArrayList<Pair<Pair<Int, Int>, Int>>

private val comparator: (Pair<Pair<Int, Int>, Int>, Pair<Pair<Int, Int>, Int>) -> Int =
    { p1: Pair<Pair<Int, Int>, Int>, p2: Pair<Pair<Int, Int>, Int> -> p1.second.compareTo(p2.second) }

private lateinit var p: ArrayList<Int>
private fun get(v: Int): Int {
    return if (v == p[v])
        v
    else {
        p[v] = get(p[v])
        p[v]
    }
}

private fun unite(a1: Int, b1: Int) {
    val a = get(a1)
    val b = get(b1)
    if (a != b) p[a] = b
}

private fun edge(u: Int, v: Int): Pair<Int, Int> = Pair(min(u, v), max(u, v))

private fun main() {
    n = sc.nextInt()
    m = sc.nextInt()
    G = ArrayList()
    for (i in 0 until m) {
        val u = sc.nextInt() - 1
        val v = sc.nextInt() - 1
        val w = sc.nextInt()
        G.add(Pair(edge(u, v), w))
    }
    print(findMST())
}

private fun findMST(): Long {
    p = ArrayList()
    for (i in 0 until n) p.add(i)
    var cost = 0L
    Collections.sort(G, comparator)
    for (i in 0 until m) {
        val a = G[i].first.first
        val b = G[i].first.second
        val l = G[i].second
        if (get(a) != get(b)) {
            cost += l
            unite(a, b)
        }
    }
    return cost
}