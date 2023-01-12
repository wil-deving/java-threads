public class InterfaceThread implements Runnable {

    public void run() {
        System.out.println("Hello from a thread by implementing runnable!");
        System.out.println("This thread has 10 steps");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
    }

    /*
    public static void main(String args[]) {
        (new Thread(new InterfaceThread())).start();
    }
     */

}
