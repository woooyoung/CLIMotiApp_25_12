package org.example;

import java.util.Scanner;

public class Container {
    private static Scanner sc;

    public static Scanner getSc() {
        return sc;
    }

    public static void setSc(Scanner sc) {
        Container.sc = sc;
    }

    // 공유 자원을 모아두는 공간을 초기화
    public static void init() {
        sc = new Scanner(System.in);
    }

    // 공유 자원을 모아두는 자원 해제
    public static void close() {
        sc.close();
    }
}
