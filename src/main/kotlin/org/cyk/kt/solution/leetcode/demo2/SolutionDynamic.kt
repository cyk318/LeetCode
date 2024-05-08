package org.cyk.kt.solution.leetcode.demo2

import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min

class SolutionDynamic {

    //1.斐波那契
    fun fib(n: Int): Int {
        if (n == 0 || n == 1) return n
        val dp = IntArray(n + 1)
        dp[0] = 0
        dp[1] = 1
        for (i in 2 .. n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    //2.爬楼梯
    fun climbStairs(n: Int): Int {
        val dp = IntArray(n + 1)
        dp[0] = 1
        dp[1] = 1
        for (i in 2 .. n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    //3.使用最小花费爬楼梯
    fun minCostClimbingStairs(cost: IntArray): Int {
        //dp定义：爬上第 n 个台阶的最小花费
        //dp[0] = cost[0]
        //dp[1] = cost[1]
        val len = cost.size
        val dp = IntArray(len + 1)
        dp[0] = 0
        dp[1] = 0
        for (i in 2 .. len) {
            dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
        }
        return dp.last()
    }

    //4.不同路径
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(m) { IntArray(n) }
        for (i in 0 ..< m) dp[i][0] = 1
        for (i in 0 ..< n) dp[0][i] = 1
        for (i in 1 ..< m) {
            for (j in 1 ..< n) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
        return dp[m - 1][n - 1]
    }

}