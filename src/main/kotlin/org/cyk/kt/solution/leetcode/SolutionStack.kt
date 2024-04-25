package org.cyk.solution.question

import java.lang.RuntimeException
import java.lang.StringBuilder
import java.util.Deque
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Properties
import java.util.Queue
import java.util.Stack

class SolutionStack {

    //3.有效的括号
    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        for(i in s.indices) {
            val ch = s[i]
            if(stack.isEmpty()) {
                stack.push(ch)
            } else {
                val chp = stack.peek()
                if((ch == ')' && chp == '(') ||
                    (ch == '}' && chp == '{') ||
                    (ch == ']' && chp == '[')) {
                    stack.pop()
                } else {
                    stack.push(ch)
                }
            }
        }
        return stack.isEmpty()
    }

//    4.删除字符串中的所有相邻重复项
//    fun removeDuplicates(s: String): String {
//        val stack = Stack<Char>()
//        for(i in s.indices) {
//            if(stack.isEmpty()) {
//                stack.push(s[i])
//            } else if(stack.peek() == s[i]) {
//                stack.pop()
//            } else {
//                stack.push(s[i])
//            }
//        }
//        val str = StringBuilder()
//        while(stack.isNotEmpty()) {
//           str.append(stack.pop())
//        }
//        return str.reverse().toString()
//    }

    //4.删除字符串中的所有相邻重复项
    fun removeDuplicates(s: String): String {
        val sb = StringBuilder()
        for(i in s.indices) {
            if(sb.isEmpty()) {
                sb.append(s[i])
            } else if (sb.last() == s[i]) {
                sb.deleteCharAt(sb.lastIndex)
            } else {
                sb.append(s[i])
            }
        }
        return sb.toString()
    }

    //5.逆波兰表达式求值
    fun evalRPN(tokens: Array<String>): Int {
        val set = setOf("+", "-", "*", "/")
        val stack = Stack<Int>()
        for(token in tokens) {
            if(!set.contains(token)) {
                stack.push(token.toInt())
            } else {
                val right = stack.pop()
                val left = stack.pop()
                val result = when(token) {
                    "+" -> left + right
                    "-" -> left - right
                    "*" -> left * right
                    "/" -> left / right
                    else -> throw RuntimeException("错误类型")
                }
                stack.push(result)
            }
        }
        return stack.pop()
    }

//    //6.滑动窗口的最大值(超时)
//    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
//        val queue = PriorityQueue<Int> { o1, o2 -> o2 - o1 }
//        val list = mutableListOf<Int>()
//        for(i in nums.indices) {
//            if(queue.size < k) {
//                queue.offer(nums[i])
//            } else {
//                queue.remove(nums[i - k])
//                queue.offer(nums[i])
//            }
//            if(queue.size % k == 0) {
//                list.add(queue.peek())
//            }
//        }
//        return list.toIntArray()
//    }

    //7.滑动窗口的最大值
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val result = IntArray(nums.size - k + 1)
        var index = 0
        //维护一个单调递减队列
        val deque: Deque<Int> = LinkedList()
        for(i in nums.indices) {
            while(deque.isNotEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast()
            }
            deque.offerLast(nums[i])
            if(i >= k && deque.peekFirst() == nums[i - k]) {
                deque.pollFirst()
            }
            if(i >= k - 1) {
                result[index++] = deque.peekFirst()
            }
        }
        return result
    }

    //8.前 K 个高频元素
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = HashMap<Int, Int>()
        for(num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }
        val queue: PriorityQueue<Int> = PriorityQueue { o1, o2 -> map[o1]!! - map[o2]!! }
        var index = 0
        for(key in map.keys) {
            if(index < k) {
                queue.offer(key)
            } else {
                if(map[queue.peek()]!! < map[key]!!) {
                    queue.poll()
                    queue.offer(key)
                }
            }
            index++
        }
        return queue.toIntArray()
    }

}

fun main() {
    val arr = arrayOf(1,1,1,2,2,3)
    val s = SolutionStack()
    s.topKFrequent(arr.toIntArray(), 2)
}

//1.用栈实现队列
class MyQueue() {

    private val stack1 = Stack<Int>()
    private val stack2 = Stack<Int>()

    fun push(x: Int) {
        while(stack2.isNotEmpty()) {
            stack1.push(stack2.pop())
        }
        stack1.push(x)
    }

    fun pop(): Int {
        while(stack1.isNotEmpty()) {
            stack2.push(stack1.pop())
        }
        return stack2.pop()
    }

    fun peek(): Int {
        while(stack1.isNotEmpty()) {
            stack2.push(stack1.pop())
        }
        return stack2.peek()
    }

    fun empty(): Boolean {
        return stack1.isEmpty() && stack2.isEmpty()
    }

}

//2.用队列实现栈
class MyStack() {

    private val queue: Queue<Int> = LinkedList()

    fun push(x: Int) {
        var size = queue.size
        queue.offer(x)
        while(size > 0) {
            queue.offer(queue.poll())
            size--
        }
    }

    fun pop(): Int {
        return queue.poll()
    }

    fun top(): Int {
        return queue.peek()
    }

    fun empty(): Boolean {
        return queue.isEmpty()
    }

}