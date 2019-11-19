package com.company;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    private boolean zero =true;
    private boolean odd =true;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i <n ; i++) {
            while (!zero)
                wait();
            printNumber.accept(0);
            zero =false;
            notifyAll();
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <n ; i+=2) {
            while ( zero || !odd)
                wait();
            printNumber.accept(i);
            zero =true;
            odd =true;
            notifyAll();
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {

        for (int i = 1; i <n ; i+=2) {
            while (zero || odd)
                wait();
            printNumber.accept(i);
            zero =true;
            odd =false;

        }


    }
}

public class orde3ring_semaphores {

    public static void main(String[] args) {

    }
}
