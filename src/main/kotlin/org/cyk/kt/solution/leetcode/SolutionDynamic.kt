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

    //14.单词拆分
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        //dp[i]: 为 ture，表示长度为 i 的字符串可以被拆分一个或多个在 字典 中出现的单词
        //j < i，if(wordDict.contains(s.substring(j, i) && dp[j]))
        val len = s.length
        val dp = BooleanArray(len + 1) { false }
        dp[0] = true
        for (i in 1 .. len) {
            for (j in 0 ..< i) {
                if(wordDict.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true
                }
            }
        }
        return dp[len]
    }

    //15.打家劫舍
    fun rob1(nums: IntArray): Int {
        val len = nums.size
        val dp = IntArray(len + 1)
        dp[1] = nums[0]
        for(i in 2 .. len) {
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i - 1])
        }
        return dp[len]
    }

    //16.打家劫舍 II
    fun rob2(nums: IntArray): Int {
        val r1 = rob1(nums.copyOfRange(0, nums.size - 1))
        val r2 = rob1(nums.copyOfRange(1, nums.size))
        return max(r1, r2)
    }

    //17.打家劫舍 III
    fun rob(root: TreeNode?): Int {
        val (left, right) = dfs1(root)
        return max(left, right)
    }

    private fun dfs1(root: TreeNode?): Pair<Int, Int> {
        if(root == null) return 0 to 0
        val left = dfs1(root.left)
        val right = dfs1(root.right)
        val cur = root.`val` + left.second + right.second
        val unCur = max(left.first, left.second) + max(right.first, right.second)
        return cur to unCur
    }

    //18.买卖股票的最佳时机
    fun maxProfit1(prices: IntArray): Int {
        //dp[i][0] 表示第 i 天不持有股，dp[i][1] 表示第 i 天持有股票
        if(prices.size <= 1) return 0
        val len = prices.size
        val dp = Array(len) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]
        for(i in 1 ..< len) {
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = max(dp[i - 1][1], -prices[i])
        }
        return dp[len - 1][0]
    }

    //19.买卖股票的最佳时机 II
    fun maxProfit2(prices: IntArray): Int {
        if(prices.size <= 1) return 0
        val len = prices.size
        val dp = Array(len) {IntArray(2)}
        dp[0][0] = 0
        dp[0][1] = -prices[0]
        for(i in 1 ..< len) {
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])
        }
        return dp[len - 1][0]
    }

    //20.买卖股票的最佳时机 III
    fun maxProfit3(prices: IntArray): Int {
        if(prices.size <= 1) return 0
        val len = prices.size
        val dp = Array(len) { IntArray(4)}
        dp[0][0] = -prices[0] //第一次持有
        dp[0][1] = 0 //第一次不持有
        dp[0][2] = -prices[0] //第二次持有
        dp[0][3] = 0 //第二次不持有
        for (i in 1 ..< prices.size) {
            dp[i][0] = max(dp[i - 1][0], -prices[i])
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] + prices[i])
            dp[i][2] = max(dp[i - 1][2], dp[i - 1][1] - prices[i])
            dp[i][3] = max(dp[i - 1][3], dp[i - 1][2] + prices[i])
        }
        return max(dp[len - 1][1], dp[len - 1][3])
    }

    //21.买卖股票的最佳时机 IV
    fun maxProfit4(k: Int, prices: IntArray): Int {
        if(prices.size <= 1) return 0
        val len = prices.size
        val dp = Array(len) { IntArray(k * 2) }
        for (i in 0 ..< k * 2) {
            if(i % 2 == 0) dp[0][i] = -prices[0]
        }
        for (i in 1 ..< prices.size) {
            for(j in 0 ..< k * 2) {
                if(j == 0) {
                    dp[i][j] = max(dp[i - 1][j], -prices[i])
                } else if (j % 2 == 1) {
                    dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i])
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i])
                }
            }
        }
        return dp[len - 1][k * 2 - 1]
    }

    //22.买卖股票的最佳时机含冷冻期
    fun maxProfit5(prices: IntArray): Int {
        //dp[i][0]: 第 i 天持股
        //dp[i][1]: 第 i 天冷冻期后不持股
        //dp[i][2]: 第 i 天冷冻期
        //dp[i][3]: 第 i 天当天卖出
        val len = prices.size
        val dp = Array(len) { IntArray(4) }
        dp[0][0] = -prices[0]
        dp[0][1] = 0
        dp[0][2] = 0;
        dp[0][3] = 0
        for (i in 1 ..< len) {
            dp[i][0] = max(dp[i - 1][0], max(dp[i - 1][1] - prices[i], dp[i - 1][2] - prices[i]))
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][2])
            dp[i][2] = dp[i - 1][3]
            dp[i][3] = dp[i - 1][0] + prices[i]
        }
        return max(dp[len - 1][1], max(dp[len - 1][2], dp[len - 1][3]))
    }

    //23.买卖股票的最佳时机含手续费
    fun maxProfit6(prices: IntArray, fee: Int): Int {
        //dp[i][0]: 第 i 天持有股票
        //dp[i][1]: 第 i 卖出，不持有股票
        //dp[i][2]: 第 i 天不持有股票(当天没有交易)
        val len = prices.size
        val dp = Array(len) { IntArray(3) }
        dp[0][0] = -prices[0]
        dp[0][1] = -fee
        dp[0][2] = 0
        for (i in 1 ..< len) {
            dp[i][0] = max(dp[i - 1][0], max(dp[i - 1][2] - prices[i], dp[i - 1][1] - prices[i]) )
            dp[i][1] = dp[i - 1][0] + prices[i] - fee
            dp[i][2] = max(dp[i - 1][2], dp[i - 1][1])
        }
        return max(dp[len - 1][1], dp[len - 1][2])
    }


    //24.最长递增子序列
    fun lengthOfLIS(nums: IntArray): Int {
        //dp[i]: 以 i 为结尾的最长递增子序列长度
        val len = nums.size
        val dp = IntArray(len) { 1 }
        var result = 1
        for (i in 1 ..< len) {
            for (j in 0 ..< i) {
                if (nums[j] < nums[i]) {
                    dp[i] = max(dp[i], dp[j] + 1)
                }
            }
            result = max(result, dp[i])
        }
        return result
    }

    //25.最长连续递增序列
//    fun findLengthOfLCIS(nums: IntArray): Int {
//        //dp[i]: 以 i 为结尾的最长连续递增子序列
//        //if(nums[j] < nums[i] && i - 1 == j) dp[i] = max(dp[i], dp[j] + 1)
//        val len = nums.size
//        val dp = IntArray(len) { 1 }
//        var result = 1
//        for (i in 1 ..< len) {
//            for (j in 0 ..< i) {
//                if(nums[j] < nums[i] && i - 1 == j) {
//                    dp[i] = max(dp[i], dp[j] + 1)
//                }
//            }
//            result = max(dp[i], result)
//        }
//        return result
//    }
    //25.优化
    fun findLengthOfLCIS(nums: IntArray): Int {
        //dp[i]: 以 i 为结尾的最长连续递增子序列
        //if(nums[i - 1] < nums[i]) dp[i] = dp[i - 1] + 1
        val len = nums.size
        val dp = IntArray(len) { 1 }
        var result = 1
        for (i in 1 ..< len) {
            if(nums[i - 1] < nums[i]) {
                dp[i] = dp[i - 1] + 1
            }
            result = max(dp[i], result)
        }
        return result
    }

    //26.最长重复子数组
    fun findLength(nums1: IntArray, nums2: IntArray): Int {
        //dp[i][j]: nums1 以 i - 1 为结尾，nums2 以 j - 1 为结尾的最长重复子数组
        val len1 = nums1.size
        val len2 = nums2.size
        val dp = Array(len1 + 1) { IntArray(len2 + 1) }
        var result = 0
        for (i in 1 .. len1) {
            for (j in 1 .. len2) {
                if(nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                    result = max(dp[i][j], result)
                }
            }
        }
        return result
    }

    //27.最长公共子序列
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        //dp[i][j]: text1 以第 i - 1 个字符为结尾， text2 以 j - 1 字符为结尾的最长公共子序列
        val len1 = text1.length
        val len2 = text2.length
        val dp = Array(len1 + 1) { IntArray(len2 + 1) }
        for (i in 1 .. len1) {
            for (j in 1 .. len2) {
                if (text1[i - 1] == text2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[len1][len2]
    }

    //28.不相交的线
    fun maxUncrossedLines(nums1: IntArray, nums2: IntArray): Int {
        val len1 = nums1.size
        val len2 = nums2.size
        val dp = Array(len1 + 1) { IntArray(len2 + 1) }
        for (i in 1 .. len1) {
            for (j in 1 .. len2) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[len1][len2]
    }

    //29.最大子数组和
    fun maxSubArray(nums: IntArray): Int {
        //dp[i]: 以第 i 个(下标)数字为结尾的最大连续子数组
        //dp[i]: max(nums[i], dp[i - 1] + nums[i])
        val len = nums.size
        val dp = IntArray(len)
        var result = nums[0]
        dp[0] = nums[0]
        for (i in 1 ..< len) {
            dp[i] = max(nums[i], dp[i - 1] + nums[i])
            if (dp[i] > result) result = dp[i]
        }
        return result
    }

    //30.回文子串
    fun countSubstrings(s: String): Int {
        //dp[i][j]: 区间 [i, j] 中是回文字串
        val len = s.length
        val dp = Array(len) { BooleanArray(len) }
        var result = 0
        for (i in len - 1 downTo 0) {
            for (j in i until len) {
                if (s[i] == s[j]) {
                    if (j - i <= 1) {
                        dp[i][j] = true
                        result++
                    } else if (dp[i + 1][j - 1]) {
                        dp[i][j] = true
                        result++
                    }
                }
            }
        }
        return result
    }

}

fun main() {
    val arr = intArrayOf(1,2)
    val s = SolutionDynamic()
}