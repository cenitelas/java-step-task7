package org.step.util;

public class ThreadSupportUtil {

    private int cycle = 8;

    public void cycleThreadRunner(String active, int sleep) {
        if (cycle == 0) {
            cycle = 8;
        }
        for (int i = 0; i < cycle; i++) {
            System.out.println(active);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
}
