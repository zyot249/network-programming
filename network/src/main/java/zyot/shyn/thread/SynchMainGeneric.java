package zyot.shyn.thread;

public class SynchMainGeneric {
    public static void main(String[] args) {
        SynchQueue<Integer> q = new SynchQueue<>();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);
        p.start();
        c.start();
    }
}
