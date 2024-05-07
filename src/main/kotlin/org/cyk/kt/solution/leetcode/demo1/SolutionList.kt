package org.cyk.kt.solution.leetcode.demo1

class SolutionList {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        head ?: return null
        val dummy = ListNode(0)
        dummy.next = head
        var cur = head
        var prev = dummy
        while(cur != null) {
            if(cur.`val` == `val`) {
                prev.next = cur.next
                cur = cur.next
            } else {
                prev = cur
                cur = cur.next
            }
        }
        return dummy.next
    }

    fun swapPairs(head: ListNode?): ListNode? {
        if(head?.next == null) {
            return head
        }
        val dummy = ListNode(0)
        dummy.next = head
        var prev = dummy
        var cur = head
        while(cur?.next != null) {
            val curNext = cur.next!!
            val curNextNext = curNext.next
            prev.next = curNext
            curNext.next = cur
            cur.next = curNextNext
            prev = cur
            cur = curNextNext
        }
        return dummy.next
    }

    fun reverseList(head: ListNode?): ListNode? {
        head ?: return head
        var cur = head.next
        head.next = null
        var mHead = head
        while(cur != null) {
            var curNext = cur.next
            cur.next = mHead
            mHead = cur
            cur = curNext
        }
        return mHead
    }

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if(head == null) return head
        val dummy = ListNode(0)
        dummy.next = head
        var m = n
        var fast = head
        while(m > 0) {
            fast = fast?.next
            m--
        }
        var slow: ListNode = head
        var prev: ListNode = dummy
        while(fast != null) {
            prev = slow
            slow = slow.next!!
            fast = fast.next
        }
        prev.next = slow.next
        slow.next = null
        return dummy.next
    }

    fun detectCycle(head: ListNode?): ListNode? {
        head ?: return null
        var fast = head.next?.next
        var slow = head.next
        while(fast != null && slow != null && fast != slow) {
            fast = fast.next?.next
            slow = slow.next
        }
        fast ?: return null
        var cur = head
        while(cur != slow) {
            cur = cur?.next
            slow = slow?.next
        }
        return cur
    }



}

fun main() {
    val node3 = SolutionList.ListNode(3)
    val node2 = SolutionList.ListNode(2)
    val node0 = SolutionList.ListNode(0)
    val node4 = SolutionList.ListNode(4)
    node3.next = node2
    node2.next = node0
    node0.next = node4
    node4.next = node2
    val s = SolutionList()
    s.detectCycle(node3)
}

//class MyLinkedList() {
//
//    class ListNode(val `val`: Int = 0) {
//        var next: ListNode? = null
//    }
//
//    var dummy: ListNode = ListNode()
//
//    fun get(index: Int): Int {
//        var idx = index
//        var cur = dummy.next
//        while(idx > 0 && cur != null) {
//            cur = cur.next
//            idx--
//        }
//        return cur?.`val` ?: -1
//    }
//
//    fun addAtHead(`val`: Int) {
//        val head = dummy.next
//        val cur = dummy
//        val newNode = ListNode(`val`)
//        cur.next = newNode
//        newNode.next = head
//    }
//
//    fun addAtTail(`val`: Int) {
//        var cur = dummy.next
//        var prev = dummy
//        while(cur != null) {
//            prev = cur
//            cur = cur.next
//        }
//        prev.next = ListNode(`val`)
//    }
//
//    fun addAtIndex(index: Int, `val`: Int) {
//        var prev = dummy
//        var cur = dummy.next
//        var idx = index
//        while(idx > 0 && cur != null) {
//            prev = cur
//            cur = cur.next
//            idx--
//        }
//        if(idx == 0 || cur != null) {
//            val newNode = ListNode(`val`)
//            prev.next = newNode
//            newNode.next = cur
//        }
//    }
//
//    fun deleteAtIndex(index: Int) {
//        var prev = dummy
//        var cur = dummy.next
//        var idx = index
//        while(idx > 0 && cur != null) {
//            prev = cur
//            cur = cur.next
//            idx--
//        }
//        prev.next = cur?.next
//        cur?.next = null
//    }
//
//}

