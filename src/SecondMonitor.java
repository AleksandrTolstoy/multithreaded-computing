class SecondMonitor {

    private int numberOfThreads;
    private int F1;
    private int F2;
    private int F3;
    private int[][] MR;

    SecondMonitor(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    synchronized void writeMR(int [][] MR) {
        this.MR = MR;
    }

    synchronized int[][] getMR() {
        return MR;
    }

    synchronized void signalInput() {
        F1++;
        notify();
    }

    synchronized void signalMA() {
        F2++;
        notify();
    }

    synchronized void signalRX() {
        F3++;
        notify();
    }

    synchronized void waitInput() {
        try {
            while (F1 != 4) // except Z and Q
                wait();
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void waitRX() {
        try {
            while (F3 != numberOfThreads)
                wait();
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void waitMA() {
        try {
            while (F2 != numberOfThreads - 1)
                wait();
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}