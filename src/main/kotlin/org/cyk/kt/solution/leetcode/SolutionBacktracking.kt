package org.cyk.solution.question



//1，组合
class SolutionBacktracking1 {

    private val listRes = mutableListOf<List<Int>>()
    private val list = mutableListOf<Int>()
    fun combine(n: Int, k: Int): List<List<Int>> {
        dfs(1, n, k)
        return listRes
    }

    private fun dfs(start: Int, end: Int, k: Int) {
        if(list.size == k) {
            listRes.add(ArrayList(list))
            return
        }
        for(n in start..end) {
            list.add(n)
            dfs(n + 1, end, k)
            list.removeLast()
        }
    }

}

//2.组合总和 III
class SolutionBacktracking2 {

    private val listRes = mutableListOf<List<Int>>()
    private val list = mutableListOf<Int>()
    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        dfs(1, 9, k, n)
        return listRes
    }

    private fun dfs(start: Int, end: Int, k: Int, sum: Int) {
        if(list.size == k && sum != 0) {
            return
        } else if (list.size == k) {
            listRes.add(ArrayList(list))
            return
        }
        for(i in start .. end) {
            list.add(i)
            dfs(i + 1, end, k, sum - i)
            list.removeLast()
        }
    }

}

//3.电话号码的字母组合
class SolutionBacktracking3 {

    private val map = arrayOf(
        "", // 0
        "", // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs", // 7
        "tuv", // 8
        "wxyz", // 9
    )

    private val listRes = mutableListOf<String>()
    fun letterCombinations(digits: String): List<String> {
        if(digits.isBlank()) return listRes
        val sb = StringBuilder()
        dfs(digits, sb, 0)
        return listRes
    }

    private fun dfs(digits: String, sb: StringBuilder, start: Int) {
        if(sb.length == digits.length) {
            listRes.add(sb.toString())
            return
        }

        for(i in start until digits.length) {
            val str = map[digits[i].digitToInt()]
            for(j in str.indices) {
                sb.append(str[j])
                dfs(digits, sb, i + 1)
                sb.deleteCharAt(sb.lastIndex)
            }
        }
    }

}

//4. 组合总和
class SolutionBacktracking4 {

    //未优化
//    private val listRes = mutableListOf<List<Int>>()
//    private val list = mutableListOf<Int>()
//    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
//        dfs(candidates, target, 0)
//        return listRes
//    }
//
//    private fun dfs(candidates: IntArray, target: Int, start: Int) {
//        if(target == 0) {
//            listRes.add(ArrayList(list))
//            return
//        }
//
//        for(i in start until candidates.size) {
//            if(target - candidates[i] < 0) continue //剪枝优化
//            list.add(candidates[i])
//            dfs(candidates, target - candidates[i], i)
//            list.removeLast()
//        }
//    }

    //剪枝优化版本
    private val listRes = mutableListOf<List<Int>>()
    private val list = mutableListOf<Int>()
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        candidates.sort() //剪枝优化
        dfs(candidates, target, 0)
        return listRes
    }

    private fun dfs(candidates: IntArray, target: Int, start: Int) {
        if(target == 0) {
            listRes.add(ArrayList(list))
            return
        }

        for(i in start until candidates.size) {
            if(target - candidates[i] < 0) break //剪枝优化
            list.add(candidates[i])
            dfs(candidates, target - candidates[i], i)
            list.removeLast()
        }
    }

}

//组合总和2
class SolutionBacktracking5 {

    private val listRes  = mutableListOf<List<Int>>()
    private val list = mutableListOf<Int>()
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        val used = Array(candidates.size) { false }
        candidates.sort()
        dfs(candidates, target, 0, used)
        return listRes
    }

    private fun dfs(candidates: IntArray, target: Int, start: Int, used: Array<Boolean>) {
        if(target == 0) {
            listRes.add(ArrayList(list))
            return
        }

        for(i in start until candidates.size) {
            if(target - candidates[i] < 0) break
            if(i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) continue //!used[i - 1] 表示是从同一个树层回溯回来的
            list.add(candidates[i])
            used[i] = true
            dfs(candidates, target - candidates[i], i + 1, used)
            used[i] = false
            list.removeLast()
        }
    }

}

//复原 IP 地址
class SolutionBacktracking6 {

    private val listRes = ArrayList<String>()
    fun restoreIpAddresses(s: String): List<String> {
        if(s.length > 12) return listRes
        val sb = StringBuilder(s)
        dfs(sb, 0, 0)
        return listRes
    }

    private fun dfs(sb: StringBuilder, start: Int, pointCnt: Int) {
        if(pointCnt == 3) {
            if(isValid(sb.substring(start, sb.length))) {
                listRes.add(sb.toString())
            }
            return
        }
        for(i in start until sb.length) {
            if(!isValid(sb.substring(start, i + 1))) {
                break
            }
            sb.insert(i + 1, '.')
            dfs(sb, i + 2, pointCnt + 1)
            sb.deleteCharAt(i + 1)
        }

    }

    private fun isValid(str: String): Boolean {
        if(str.isEmpty()) {
            return false
        }
        if(str.length > 1 && str[0] == '0') {
            return false
        }
        val num = str.toInt()
        if(num > 255 || num < 0) {
            return false
        }
        return true
    }

}

//分割回文串
class SolutionBacktracking7 {

    private val listRes = mutableListOf<List<String>>()
    private val list = mutableListOf<String>()
    fun partition(s: String): List<List<String>> {
        dfs(s, 0)
        return listRes
    }

    private fun dfs(s: String, start: Int) {
        if(start >= s.length) {
            listRes.add(ArrayList(list))
            return
        }
        for(i in start until s.length) {
            if(!isValid(s.substring(start, i + 1))) continue
            list.add(s.substring(start, i + 1))
            dfs(s, i + 1)
            list.removeLast()
        }
    }

    private fun isValid(s: String): Boolean {
        var left = 0
        var right = s.lastIndex
        while(left < right) {
            if(s[left] != s[right]) return false
            left++
            right--
        }
        return true
    }

}

//子集
class SolutionBacktracking8 {

    private val listRes = mutableListOf<List<Int>>()
    private val list = mutableListOf<Int>()
    fun subsets(nums: IntArray): List<List<Int>> {
        dfs(nums, 0)
        return listRes
    }

    private fun dfs(nums: IntArray, start: Int) {
        if(start >= nums.size) {
            listRes.add(ArrayList(list))
            return
        }

        listRes.add(ArrayList(list))
        for(i in start until nums.size) {
            list.add(nums[i])
            dfs(nums, i + 1)
            list.removeLast()
        }
    }

}

//子集 II
class SolutionBacktracking9 {

    private val listRes = mutableListOf<List<Int>>()
    private val list = mutableListOf<Int>()
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()
        val used = Array(nums.size) { false }
        dfs(nums, 0, used)
        return listRes
    }

    private fun dfs(nums: IntArray, start: Int, used: Array<Boolean>) {
        listRes.add(ArrayList(list))
        if(start >= nums.size) return

        for(i in start until nums.size) {
            if(i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue
            used[i] = true
            list.add(nums[i])
            dfs(nums, i + 1, used)
            used[i] = false
            list.removeLast()
        }
    }

}

//非递减子序列
class SolutionBacktracking10 {

    private val listRes = mutableSetOf<List<Int>>()
    private val list = mutableListOf<Int>()
    fun findSubsequences(nums: IntArray): List<List<Int>> {
        val used = Array(nums.size) { false }
        dfs(nums, 0, used)
        return listRes.toList()
    }

    private fun dfs(nums: IntArray, start: Int, used: Array<Boolean>) {
        if(isValid(list)) listRes.add(ArrayList(list))
        if(list.size > 2 && !isValid(list)) return
        if(start >= nums.size) return

        for(i in start until nums.size) {
            if(i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue
            used[i] = true
            list.add(nums[i])
            dfs(nums, i + 1, used)
            used[i] = false
            list.removeLast()
        }
    }

    private fun isValid(l: MutableList<Int>): Boolean {
        if(l.size < 2) {
            return false
        }
        for(i in 0 until l.size) {
            if(i > 0 && l[i - 1] > l[i]) {
                return false
            }
        }
        return true
    }

}

//全排列
class SolutionBacktracking11 {

    private val listRes = mutableListOf<List<Int>>()
    private val list = mutableListOf<Int>()
    fun permute(nums: IntArray): List<List<Int>> {
        val used = Array(nums.size) { false }
        dfs(nums, used)
        return listRes
    }

    private fun dfs(nums: IntArray, used: Array<Boolean>) {
        if(nums.size == list.size) {
            listRes.add(ArrayList(list))
            return
        }

        for(i in nums.indices) {
            if(used[i]) continue
            used[i] = true
            list.add(nums[i])
            dfs(nums, used)
            used[i] = false
            list.removeLast()
        }
    }

}

//全排列II
class SolutionBacktracking12 {

    private val listRes = mutableListOf<List<Int>>()
    private val list = mutableListOf<Int>()
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        nums.sort()
        val used = Array(nums.size) { false }
        dfs(nums, used)
        return listRes
    }

    fun dfs(nums: IntArray, used: Array<Boolean>) {
        if(nums.size == list.size) {
            listRes.add(ArrayList(list))
            return
        }

        for(i in nums.indices) {
            if(i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) break
            if(used[i]) continue
            used[i] = true
            list.add(nums[i])
            dfs(nums, used)
            used[i] = false
            list.removeLast()
        }
    }

}

fun main() {
}