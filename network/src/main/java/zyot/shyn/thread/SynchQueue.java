package zyot.shyn.thread;

import java.util.LinkedList;

public class SynchQueue<DataType> {
    public static final int LIMIT_SIZE = 4;
    public LinkedList<DataType> l;

    SynchQueue () {
        l = new LinkedList<>();
    }

    public synchronized void Add(DataType elem) throws InterruptedException {
        while (l.size() == LIMIT_SIZE) {
            wait();
        }
        l.addLast(elem);
        notify();
    }

    public synchronized DataType Remove() throws InterruptedException {
        while (l.size() == 0)
            wait();
        DataType item = l.removeFirst();
        if (l.size() == LIMIT_SIZE/2)
            notify();
        return item;
    }
}
