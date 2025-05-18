public class Platform {
    private boolean gateOpen = false;

    public synchronized void enterPlatform(String name) throws InterruptedException {
        System.out.println("🧍 " + name + " is waiting to enter platform...");

        while (!gateOpen){
            wait();//passengers are waiting for notify
        }
        System.out.println("🚉 " + name + " is entering the platform.");

        gateOpen = false;
    }
    public synchronized void allowNext(){
        gateOpen = true;
        notify();//allow one passenger
    }
    /**Each passenger is waiting to be notified.

     Only one passenger can enter at a time (not simultaneously).*/
}
