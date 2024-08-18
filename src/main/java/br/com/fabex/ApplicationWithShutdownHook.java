package br.com.fabex;

import br.com.fabex.hook.CleanDataApplicationSHook;
import br.com.fabex.hook.CloseResourcesApplicationHook;

public class ApplicationWithShutdownHook {
    public static void main(String[] args) {
        System.out.println("Initializing application...");

        // Adding a Shutdown Hook
        Runtime.getRuntime().addShutdownHook(new CleanDataApplicationSHook());
        Runtime.getRuntime().addShutdownHook(new CloseResourcesApplicationHook());

        // Loop
        System.out.println("Press command Ctrl+C to finish application...");
        while (true);
    }

}