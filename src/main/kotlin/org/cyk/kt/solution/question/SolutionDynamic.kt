package org.cyk.solution.question

import kotlin.contracts.contract
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

    //9.目标和
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        //假设所有加法对应的总和为: x
        //那么所有减法对应的总和为: sum - x
        //要求就是: x - (sum - x) = target
        //因此 x = (target + sum) / 2
        //问题就变成了装满容量为 x 的背包，有多少种不同的装法
        //dp[j]: 装满容量为 j 的背包有多少中不同的装法
        var sum = 0
        for (num in nums) sum += num
        if ((target + sum) % 2 == 1) return 0
        if (abs(target) > sum) return 0

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

    //10.零钱兑换 II
    fun change(amount: Int, coins: IntArray): Int {
        //dp[j]: 装满容量为 j 的背包有 dp[j] 种方法
        val dp = IntArray(amount + 1)
        dp[0] = 1
        for (i in coins.indices) {
            for (j in coins[i] .. amount) {
                dp[j] += dp[j - coins[i]]
            }
        }
        return dp[amount]
    }

    //11.组合总和 Ⅳ
    fun combinationSum4(nums: IntArray, target: Int): Int {
        val dp = IntArray(target + 1)
        dp[0] = 1
        for (j in 0 .. target) {
            for (i in nums.indices) {
                if(j >= nums[i]) {
                    dp[j] += dp[j - nums[i]]
                }
            }
        }
        return dp[target]
    }

    //12.零钱兑换
    fun coinChange(coins: IntArray, amount: Int): Int {
        //dp[j]: 装满容量为 j 的背包，最少需要的 dp[j] 个硬币
        val dp = IntArray(amount + 1) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in coins.indices) {
            for (j in coins[i] .. amount) {
                if(Int.MAX_VALUE == dp[j - coins[i]]) continue
                dp[j] = min(dp[j], dp[j - coins[i]] + 1)
            }
        }
        return if(dp[amount] == Int.MAX_VALUE) -1 else dp[amount]
    }

    //13.完全平方数
    fun numSquares(n: Int): Int {
        if(n == 1) return 1
        val dp = IntArray(n + 1) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in 1 .. n / 2) {
            for (j in i * i .. n) {
                if(dp[j - i * i] == Int.MAX_VALUE) continue
                dp[j] = min(dp[j], dp[j - i * i] + 1)
            }
        }
        return dp[n]
    }

}

fun main() {
    val arr = intArrayOf(1, 2, 5)
    val s = SolutionDynamic()
    s.coinChange(arr, 11)
}