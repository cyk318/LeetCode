package org.cyk.solution.question

import kotlin.math.abs
import kotlin.math.max
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

    //6.整数拆分
    fun integerBreak(n: Int): Int {
        //dp[i]: 拆分整数 i，得到的最大乘积
        //dp[i]: max(dp[i], max(j * (i - j), j * dp[i - j]))
        //dp[2] = 1 * 1
        //dp[3] = 1 * 2
        //dp[4] = 1 * 3 || 2 * 2
        //dp[5] = 1 * 4 || 2 * 3
        //dp[6] = 1 * 5 || 2 * 4 || 3 * 3
        val dp = IntArray(n + 1) { 0 }
        dp[1] = 1
        for(i in 2 .. n) {
            for(j in 1 until  i) {
                dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
            }
        }
        return dp.last()
    }

    //7.分割等和子集
    fun canPartition(nums: IntArray): Boolean {
        var sum = 0
        for(num in nums) sum += num
        if(sum % 2 == 1) return false
        val cap = sum / 2
        val dp = IntArray(cap + 1)
        for (i in nums.indices) {
            for (j in cap downTo nums[i]) {
                dp[j] = max(dp[j], dp[j - nums[i]] + nums[i])
            }
        }
        return dp[cap] == cap
    }

    //8.最后一块石头的重量 II
    fun lastStoneWeightII(stones: IntArray): Int {
        var sum = 0
        for (i in stones.indices) sum += stones[i]
        val cap = sum / 2
        val dp = IntArray(cap + 1)
        for (i in stones.indices) {
            for (j in cap downTo stones[i]) {
                dp[j] = max(dp[j], dp[j - stones[i]] + stones[i])
            }
        }
        return sum - 2 * dp[cap]
    }

}
