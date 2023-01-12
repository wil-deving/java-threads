public class InheritanceThread extends Thread{

    public void run() {
        System.out.println("Hello from a thread by inheritance!");
        System.out.println("This thread has 10 steps");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
    }
    /*
    public static void main(String args[]) {
        (new InheritanceThread()).start();
    }

     */

}
