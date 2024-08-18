package br.com.fabex.hook;

public class CloseResourcesApplicationHook extends Thread {
    @Override
    public void run() {
        System.out.println("#CloseResourcesApplicationHook Closing application...");
        // Business
    }
}