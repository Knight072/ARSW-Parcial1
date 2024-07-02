package edu.escuelaing.arsw.concurrent;

import java.util.ArrayList;

public class ConcurrentServer {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        String[] empresas = {"IBM", "SHOP.TRT", "GPV.TRV", "fb"};
        for (int i = 0; i <= 3; i++) {
            threads.add(new Hilo(i, empresas[i]));
            threads.get(i).start();
        }
        for (int i = 0; i <= 3; i++) {
            threads.get(i).join();
        }
    }
}
