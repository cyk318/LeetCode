package org.cyk.kt.solution.leetcode.demo1

import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.text.StringBuilder

class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}
class SolutionTree {

  //1.水平输出二叉树
  fun levelOrder(root: TreeNode?): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    root ?: return result
    val queue: Queue<TreeNode> = LinkedList()
    queue.offer(root)
    while(queue.isNotEmpty()) {
      var size = queue.size
      val list = mutableListOf<Int>()
      while(size > 0) {
        val node = queue.poll()
        list.add(node.`val`)
        node.left?.let { queue.offer(it) }
        node.right?.let { queue.offer(it) }
        size--
      }
      result.add(list)
    }
    return result
  }

  //2.反转二叉树
  fun invertTree(root: TreeNode?): TreeNode? {
    root ?: return null
    val tmp = root.left
    root.left = root.right
    root.right = tmp
    invertTree(root.left)
    invertTree(root.right)
    return root
  }

  //3.二叉树镜像
  fun isSymmetric(root: TreeNode?): Boolean {
    if(root == null) return true
    return dfs1(root.left, root.right)
  }

  private fun dfs1(left: TreeNode?, right: TreeNode?): Boolean {
    if(left == null && right != null) {
      return false
    }
    if(left != null && right == null) {
      return false
    }
    if(left == null && right == null) {
      return true
    }
    if(left!!.`val` != right!!.`val`) {
      return false
    }
    return dfs1(left.left, right.right) && dfs1(left.right, right.left)
  }

  //4.二叉树的最大深度
  fun maxDepth(root: TreeNode?): Int {
    if(root == null) return 0
    val left = maxDepth(root.left) + 1
    val right = maxDepth(root.right) + 1
    return max(left, right)
  }

  //5.二叉树的最小深度
  fun minDepth(root: TreeNode?): Int {
    if(root == null) return 0
    if(root.left != null && root.right == null) {
      return minDepth(root.left) + 1
    }
    if(root.left == null && root.right != null) {
      return minDepth(root.right) + 1
    }
    return min(minDepth(root.left), minDepth(root.right)) + 1
  }

  //6.二叉树的节点个数
  fun countNodes(root: TreeNode?): Int {
    if(root == null) return 0
    if(root.left == null) return 1
    if(root.right == null) return 2
    return countNodes(root.left) + countNodes(root.right) + 1
  }

  private fun maxDep(root: TreeNode?): Int {
    if(root == null) return 0
    return max(maxDep(root.left), maxDep(root.right)) + 1
  }

  //7.平衡二叉树
  fun isBalanced(root: TreeNode?): Boolean {
    if (root == null) return true
    val left = maxDep(root.left)
    val right = maxDep(root.right)
    if(abs(left - right) > 1) {
      return false
    }
    return isBalanced(root.left) && isBalanced(root.right)
  }

  //8.二叉树的所有路径
  fun binaryTreePaths(root: TreeNode?): List<String> {
    val list = mutableListOf<String>()
    val strBuild = StringBuilder()
    dfs2(root, strBuild, list)
    return list
  }

  private fun dfs2(
      root: TreeNode?,
      strBuild: StringBuilder,
      list: MutableList<String>
  ) {
    if(root == null) return
    if(root.left == null && root.right == null) {
      strBuild.append(root.`val`)
      list.add(strBuild.toString())
      val size = root.`val`.toString().length
      strBuild.deleteRange(strBuild.length - size, strBuild.length)
      return
    }
    strBuild.append(root.`val`)
    strBuild.append("->")
    val size = root.`val`.toString().length + 2
    dfs2(root.left, strBuild, list)
    dfs2(root.right, strBuild, list)
    strBuild.deleteRange(strBuild.length - size, strBuild.length)
  }


  //9.二叉树的左叶子之和
  fun sumOfLeftLeaves(root: TreeNode?): Int {
    return dfs3(root, null)
  }

  private fun dfs3(
      root: TreeNode?,
      prev: TreeNode?
  ): Int {
    if(root == null) {
      return 0
    }
    if(root.left == null && root.right == null && prev?.left == root) {
      return root.`val`
    }
    return dfs3(root.left, root) + dfs3(root.right, root)
  }

  //10.找树左下角的值
  fun findBottomLeftValue(root: TreeNode?): Int {
    val queue: Queue<TreeNode> = LinkedList()
    queue.offer(root)
    var result = -1
    while(queue.isNotEmpty()) {
      val size = queue.size
      for(i in 0 until size) {
        val node = queue.poll()
        if(i == 0) result = node.`val`
        node.left?.let { queue.offer(it) }
        node.right?.let { queue.offer(it) }
      }
    }
    return result
  }

  //11.路径总和
  fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if(root == null) {
      return false
    }
    if(root.left == null && root.right == null) {
      return targetSum - root.`val` == 0
    }
    return hasPathSum(root.left, targetSum - root.`val`) ||
            hasPathSum(root.right, targetSum - root.`val`)
  }

  //12.从中序与后序遍历序列构造二叉树
  private var postIndex: Int = -1;
  fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
    postIndex = postorder.size - 1
    return build(inorder, postorder, 0, postorder.size - 1)
  }

  private fun build(inorder: IntArray, postorder: IntArray, left: Int, right: Int): TreeNode? {
    if(left > right) return null
    val root = TreeNode(postorder[postIndex--])
    val rootIndex = findRootByInOrder(inorder, root.`val`, left, right)
    root.right = build(inorder, postorder, rootIndex + 1, right)
    root.left = build(inorder, postorder, left, rootIndex - 1)
    return root
  }

  private fun findRootByInOrder(inorder: IntArray, target: Int, left: Int, right: Int): Int {
    for(i in left until  right + 1) {
      if(inorder[i] == target) return i
    }
    return -1
  }

  //13.最大二叉树
  fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
    if(nums.isEmpty()) {
      return null
    }
    if(nums.size == 1) {
      return TreeNode(nums[0])
    }
    val maxIndex = findMaxIndex(nums)
    val root = TreeNode(nums[maxIndex])
    root.left = constructMaximumBinaryTree(nums.copyOfRange(0, maxIndex))
    root.right = constructMaximumBinaryTree(nums.copyOfRange(maxIndex + 1, nums.size))
    return root
  }

  private fun findMaxIndex(nums: IntArray): Int {
    var max = nums[0]
    var maxIndex = 0
    for(i in nums.indices) {
      if(nums[i] > max) {
        max = nums[i]
        maxIndex = i
      }
    }
    return maxIndex
  }

  //14.合并二叉树
  fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
    if(root1 == null && root2 == null) {
      return null
    }
    if(root1 == null && root2 != null) {
      return root2
    }
    if(root1 != null && root2 == null) {
      return root1
    }
    val root = TreeNode(root1!!.`val` + root2!!.`val`)
    root.left = mergeTrees(root1.left, root2.left)
    root.right = mergeTrees(root1.right, root2.right)
    return root
  }

  fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    if(root == null) return null
    val left = searchBST(root.left, `val`)
    if(root.`val` == `val`) return root
    if(left != null) return left
    if(root.`val` > `val`) return null
    val right = searchBST(root.right, `val`)
    return left ?: right
  }

  //15.验证二叉搜索树
  private var prev: TreeNode? = null
  fun isValidBST(root: TreeNode?): Boolean {
    return dfs4(root)
  }

  private fun dfs4(root: TreeNode?): Boolean {
    if(root == null) {
      return true
    }
    val left = dfs4(root.left)
    if(prev != null && root.`val` <= prev!!.`val`) {
      return false
    }
    prev = root
    val right = dfs4(root.right)
    return left && right
  }

  //16.二叉搜索树的最小绝对差
  fun getMinimumDifference(root: TreeNode?): Int {
    var result1: Int = Int.MAX_VALUE
    var prev2: TreeNode? = null

     fun dfs(root: TreeNode?) {
      if(root == null) return
      dfs(root.left)
      prev2?.let {
        val ans = abs(it.`val` - root.`val`)
        if(ans < result1) {
          result1 = ans
        }
      }
      prev2 = root
      dfs(root.right)
    }

    dfs(root)
    return result1
  }


  //17.二叉搜索树中的众数
  fun findMode(root: TreeNode?): IntArray {
    val set = mutableSetOf<Int>()
    var prevValue: Int? = null
    var cur = 0
    var max = 0

    fun dfs(root: TreeNode?) {
      if(root == null) return
      dfs(root.left)
      if(prevValue != null) {
        if(prevValue == root.`val`) {
          cur++
          if(cur == max) {
            set.add(root.`val`)
          } else if(cur > max) {
            set.clear()
            set.add(root.`val`)
            max = cur
          } else { }
        } else {
          cur = 1
          if(cur == max) {
            set.add(root.`val`)
          }
        }
      } else {
        set.add(root.`val`)
        cur++
        max = cur
      }
      prevValue = root.`val`
      dfs(root.right)
    }

    dfs(root)
    return set.toIntArray()
  }

  //18.二叉树的最近公共祖先
  fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if(root == null) return null
    if(root == p || root == q) return root
    val left = lowestCommonAncestor(root.left, p, q)
    val right = lowestCommonAncestor(root.right, p, q)
//    if(left != null && right != null) {
//      return root
//    } else if(left == null && right != null) {
//      return right
//    } else if(left != null && right == null) {
//      return left
//    } else {
//      return null
//    }
    if(left == null) return right
    if(right == null) return left
    return root
  }

  //19.修剪二叉搜索树
  fun trimBST(root: TreeNode?, low: Int, high: Int): TreeNode? {
    if(root == null) return null
    if(root.`val` < low) return trimBST(root.right, low, high)
    if(root.`val` > high) return trimBST(root.left, low, high)
    root.left = trimBST(root.left, low, high)
    root.right = trimBST(root.right, low, high)
    return root
  }

  //20.将有序数组转换为二叉搜索树
  fun sortedArrayToBST(nums: IntArray): TreeNode? {
    if(nums.isEmpty()) return null
    val mid = nums.size / 2
    val root = TreeNode(nums[mid])
    root.left = sortedArrayToBST(nums.copyOfRange(0, mid))
    root.right = sortedArrayToBST(nums.copyOfRange(mid + 1, nums.size))
    return root
  }

  //21.把二叉搜索树转换为累加树
  private var sum = 0
  fun convertBST(root: TreeNode?): TreeNode? {
    if(root == null) return null
    convertBST(root.right)
    val tmp = root.`val`
    root.`val` += sum
    sum += tmp
    convertBST(root.left)
    return root
  }

}

fun main() {
  val s = SolutionTree()
  val n4 = TreeNode(4)
  val n2 = TreeNode(2)
  val n7 = TreeNode(7)
  val n1 = TreeNode(1)
  val n3 = TreeNode(3)
  n4.left = n2
  n4.right = n7
  n2.left = n1
  n2.right = n3
  s.searchBST(n4, 2)
}

