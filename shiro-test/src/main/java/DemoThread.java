class DemoThread extends Thread {
    public static void main(String args[]) {
        DemoThread threadOne = new DemoThread();
        DemoThread threadTwo = new DemoThread();
        threadOne.start();
        System.out.print("one ");
        threadTwo.start();
        System.out.print("two ");
    }
    public void run() {
        System.out.print("Thread ");
    }
}