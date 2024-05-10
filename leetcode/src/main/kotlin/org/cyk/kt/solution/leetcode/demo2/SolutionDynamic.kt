package org.cyk.kt.solution.leetcode.demo2

import kotlin.concurrent.fixedRateTimer
import kotlin.math.abs
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

    //5.整数拆分
    fun integerBreak(n: Int): Int {
        //dp[1] = 1
        //dp[2] = 1 * 1
        //dp[3] = 1 * 2
        //dp[4] = 2 * 2
        //dp[5] = 2 * 3
        //dp[i] = max(dp[i], dp[i - j] * j, (i - j) * j)
        val dp = IntArray(n + 1)
        dp[1] = 1
        for (i in 2 .. n) {
            for (j in 1 ..< i) {
                dp[i] = max(dp[i], max(dp[i - j] * j, (i - j) * j))
            }
        }
        return dp.last()
    }

    //6.分割等和子集
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 == 1) return false
        val cap = sum / 2
        val dp = IntArray(cap + 1)
        for (i in nums.indices) {
            for (j in cap downTo nums[i]) {
                dp[j] = max(dp[j], dp[j - nums[i]] + nums[i])
            }
        }
        return dp[cap] == cap
    }

    //7.最后一块石头的重量II
    fun lastStoneWeightII(stones: IntArray): Int {
        //2,2,3,2   = 9
        val sum = stones.sum()
        val cap = sum / 2
        val dp = IntArray(cap + 1)
        for (i in stones.indices) {
            for (j in cap downTo stones[i]) {
                dp[j] = max(dp[j], dp[j - stones[i]] + stones[i])
            }
        }
        return sum - 2 * dp[cap]
    }

    //8.目标和
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        //x - (sum - x) = target
        //2x = target + sum
        //x = (target + sum) / 2
        val sum = nums.sum()
        if (sum < abs(target)) return 0
        if ((sum + target) % 2 == 1) return 0
        val cap = (target + sum) / 2
        val dp = IntArray(cap + 1)
        dp[0] = 1
        for (i in nums.indices) {
            for (j in cap downTo nums[i]) {
                dp[j] += dp[j - nums[i]]
            }
        }
        return dp[cap]
    }

}