package org.cyk.kt.solution.leetcode.demo2

import sun.jvm.hotspot.utilities.BitMap
import kotlin.concurrent.fixedRateTimer
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.max
import kotlin.math.min

 class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
}

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

    //9.零钱兑换 II
    fun change(amount: Int, coins: IntArray): Int {
        val dp = IntArray(amount + 1)
        dp[0] = 1
        for (i in coins.indices) {
            for (j in coins[i] .. amount) {
                dp[j] = dp[j] + dp[j - coins[i]]
            }
        }
        return dp[amount]
    }

    //10.组合总和 Ⅳ
    fun combinationSum4(nums: IntArray, target: Int): Int {
        val dp = IntArray(target + 1)
        dp[0] = 1
        for (i in 0 .. target) {
            for (j in nums.indices) {
                if (i - nums[j] >= 0) dp[i] = dp[i] + dp[i - nums[j]]
            }
        }
        return dp[target]
    }

    //11.零钱兑换
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in coins.indices) {
            for (j in coins[i] .. amount) {
                if (dp[j - coins[i]] == Int.MAX_VALUE) continue
                dp[j] = min(dp[j], dp[j - coins[i]] + 1)
            }
        }
        return if (dp[amount] == Int.MAX_VALUE) -1 else dp[amount]
    }

    //12.完全平方数
    fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in 1 .. n / 2) {
            for (j in i * i .. n) {
                if (dp[j - i * i] == Int.MAX_VALUE) continue
                dp[j] = min(dp[j], dp[j - i * i] + 1)
            }
        }
        return if (dp[n] == Int.MAX_VALUE) 1 else dp[n]
    }

    //13.单词拆分
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        //dp[i]: 拆分 s 得到的字符串在 wordDict 可以由一个或多个组成
        val len = s.length
        val dp = BooleanArray(len) { false }
        dp[0] = true
        for (i in 1 .. len) {
            for (j in 0 ..< i) {
                if (wordDict.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true
                }
            }
        }
        return dp[len]
    }

    //14.打家劫舍
    fun rob1(nums: IntArray): Int {
        val len = nums.size
        val dp = IntArray(len + 1)
        dp[1] = nums[0]
        for (i in 2 .. len) {
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i - 1])
        }
        return dp.last()
    }

    //15.打家劫舍 II
    fun rob2(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        val len = nums.size
        val robFirst = rob1(nums.copyOfRange(0, len - 1))
        val robLast = rob1(nums.copyOfRange(1, len))
        return max(robFirst, robLast)
    }

    //16.打家劫舍 III
    fun rob3(root: TreeNode?): Int {
        val result = dfs(root)
        return max(result.first, result.second)
    }

    private fun dfs(root: TreeNode?): Pair<Int, Int> {
        if (root == null) return 0 to 0
        val left = dfs(root.left)
        val right = dfs(root.right)
        //偷当前节点
        val v1 = root.`val` + left.second + right.second
        val v2 = max(left.first, left.second) + max(right.first, right.second)
        return v1 to v2
    }

    //17.买卖股票的最大利润I
    fun maxProfit1(prices: IntArray): Int {
        //不持有: dp[i][0]: max(dp[i - 1][0], dp[i - 1] + p[i])
        //持有: dp[i][1]: max(dp[i - 1][1], -p[i])
        val len = prices.size
        val dp = Array(len) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]
        for (i in 1 ..< len) {
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = max(dp[i - 1][1], -prices[i])
        }
        return dp[len - 1][0]
    }

    //18.买卖股票的最大利润II
    fun maxProfit2(prices: IntArray): Int {
        val len = prices.size
        val dp = Array(len) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]
        for (i in 1 ..< len) {
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])
        }
        return max(dp[len - 1][0], dp[len - 1][1])
    }

    //19.最长递增子序列
    fun lengthOfLIS(nums: IntArray): Int {
        //dp[i]: 以 i 为结尾的最长的递增子序列长度
        val len = nums.size
        val dp = IntArray(len) { 1 }
        var result = 1
        for (i in 1 ..< len) {
            for (j in 0 ..< i) {
                if (nums[j] < nums[i]) {
                    dp[i] = max(dp[i], dp[j] + 1)
                }
            }
            result = max(dp[i], result)
        }
        return result
    }

    //20.最长连续递增子序列
    fun findLengthOfLCIS(nums: IntArray): Int {
        val len = nums.size
        val dp = IntArray(len) { 1 }
        var result = 1
        for (i in 1 ..< len) {
            if (nums[i] > nums[i - 1]) dp[i] = dp[i - 1] + 1
            result = max(dp[i], result)
        }
        return result
    }

    //最后一块石头的重量
    fun lastStoneWeightII2(stones: IntArray): Int {
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

    //21.最长重复子数组
    fun findLength(nums1: IntArray, nums2: IntArray): Int {
        //TODO
        return 1
    }

}

fun main() {
}

