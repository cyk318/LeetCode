package org.cyk.kt.solution.exam;


import java.util.Scanner;
public class Test {
}


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong(); // 小孩的数量
        long m = scanner.nextLong(); // 苹果的数量
        long k = scanner.nextLong(); // 小明的编号

        long left = 1;
        long right = m;
        long result = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long totalApples = mid * n; // mid 个苹果时，每个小孩平均分到的苹果数目

            // 如果小明分到 mid 个苹果，则编号为 1 到 k 的小孩共分到了 (mid - 1) * k 个苹果
            // 编号为 k+1 到 n 的小孩共分到了 mid * (n - k) 个苹果
            // 总共分到的苹果数目不能超过 m
            if ((mid - 1) * k + mid * (n - k) <= m) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
