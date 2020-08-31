package zyot.shyn.thread;

public class Producer extends Thread {
    SynchQueue<Integer> q;
    int curr;

    Producer (SynchQueue<Integer> q) {
        this.q = q;
        curr = 1;
    }

    public void run() {
        for (;;) {
            Integer i = curr;
            try {
                q.Add(i);
                curr++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
