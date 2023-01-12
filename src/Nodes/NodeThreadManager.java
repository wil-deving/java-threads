package Nodes;

import java.util.ArrayList;
import java.util.List;

public class NodeThreadManager {

    List<RulesExecutionThread> threads;

    boolean areAllThreadsCompleted = false;

    public NodeThreadManager() {
        this.threads = new ArrayList<>();
    }

    public void addThread(RulesExecutionThread thread) {
        this.threads.add(thread);
    }

    public void startThreads() {
        for (RulesExecutionThread thread: this.threads) {
            thread.start();
        }
        /*
        while (!areAllThreadsCompleted) {
            try {
                Thread.sleep(1000);
                verifyThreadsStatus();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

         */
        joiningThreads();
    }
    public void joiningThreads() {
        for (RulesExecutionThread thread: this.threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void verifyThreadsStatus() {
        int counter = 0;
        for (RulesExecutionThread thread: this.threads) {
            if (!thread.isAlive()) {
                counter++;
            }
        }
        this.areAllThreadsCompleted = counter == this.threads.size();
    }

}
