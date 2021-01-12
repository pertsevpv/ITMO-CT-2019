import java.util.*
import kotlin.math.min

private val sc: Scanner = Scanner(System.`in`)
private var n: Int = 0
private lateinit var W: Array<Array<Int>>
private lateinit var d: Array<Array<Int>>

private fun main() {
    n = sc.nextInt()
    W = Array(n) { Array(n) { 0 } }
    d = Array(n) { Array(n) { 0 } }
    for (i in 0 until n) {
        for (j in 0 until n) {
            W[i][j] = sc.nextInt()
            d[i][j] = W[i][j]
        }
    }
    floyd()
    for (i in 0 until n){
        for (j in d[i]){
            print("$j ")
        }
        println()
    }
}

private fun floyd() {
    for (i in 0 until n) {
        for (u in 0 until n) {
            for (v in 0 until n) {
                d[u][v] = min(d[u][v], d[u][i] + d[i][v])
            }
        }
    }
}