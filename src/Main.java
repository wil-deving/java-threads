public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        (new InheritanceThread()).start();
        System.out.println("At this point call t1");
        (new Thread(new InterfaceThread())).start();
        System.out.println("At this point call t2");

    }


}