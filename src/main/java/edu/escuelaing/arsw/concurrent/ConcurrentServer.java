package edu.escuelaing.arsw.concurrent;

import java.util.ArrayList;

public class ConcurrentServer {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        threads.add(new Hilo(0, "IBM"));
        threads.add(new Hilo(1, "SHOP.TRT"));
        threads.add(new Hilo(2, "GPV.TRV"));
        threads.get(0).start();
        threads.get(1).start();
        threads.get(2).start();

        for (int i = 0; i <= 2; i++) {
            threads.get(i).join();
        }
    }
}
