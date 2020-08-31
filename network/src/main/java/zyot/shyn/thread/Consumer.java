package zyot.shyn.thread;

public class Consumer extends Thread {
    SynchQueue<Integer> q;

    Consumer (SynchQueue<Integer> q) {
        this.q = q;
    }

    public void run() {
        for (;;) {
            Integer i;
            try {
                i = q.Remove();
                if (i != null) {
                    System.out.println(i + " ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
