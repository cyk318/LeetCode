package org.cyk.solution.question

import kotlin.math.cos
import kotlin.math.min

class SolutionDynamic {

    //1.斐波那契数
    fun fib(n: Int): Int {
        if(n == 0 || n == 1) return n
        val dp = Array(n + 1) { 0 }
        dp[0] = 0
        dp[1] = 1
        for(i in 2 .. n) {
            dp[i] += dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    //2.爬楼梯
    fun climbStairs(n: Int): Int {
        //爬上 n 阶楼梯有 dp[n] 种方法
        //dp[1] = 1   dp[2] = 2  dp[3] = 3  dp[4] = 5
        if(n == 1 || n ==2) return n
        val dp = Array(n + 1) { 0 }
        dp[1] = 1
        dp[2] = 2
        for(i in 3 .. n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    //3.使用最小花费爬楼梯
    fun minCostClimbingStairs(cost: IntArray): Int {
        //dp[i] 爬上 i - 1 个台阶的最小花费
        val dp = Array(cost.size + 1) { 1000 }
        dp[0] = 0
        dp[1] = 0
        for(i in 2 .. cost.size) {
            dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
        }
        return dp.last()
    }

    //4.不同路径
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(m) { Array(n) { 0 } }
        for(i in 0 until m) dp[i][0] = 1
        for(j in 0 until n) dp[0][j] = 1
        for(i in 1 until m) {
            for(j in 1 until n) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
        return dp.last().last()
    }

    //5.不同路径 II
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size
        val dp = Array(m) { Array(n) { 0 } }
        for(i in 0 until m) {
            if(obstacleGrid[i][0] == 1) break
            dp[i][0] = 1
        }
        for(j in 0 until n) {
            if(obstacleGrid[0][j] == 1) break
            dp[0][j] = 1
        }
        for(i in 1 until m) {
            for(j in 1 until n) {
                if(obstacleGrid[i][j] == 1) continue
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
        return dp.last().last()
    }


}
