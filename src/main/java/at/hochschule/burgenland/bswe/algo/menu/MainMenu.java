package at.hochschule.burgenland.bswe.algo.menu;

import at.hochschule.burgenland.bswe.algo.util.InputUtils;

import java.util.List;

public class MainMenu {
    public static void runMenu() {
        do {
            System.out.println("-------------------------MAIN MENU--------------------------");
            int option = InputUtils.getValidMenuOption(List.of("Route Calculation", "Sorting", "Searching", "Exit"));
            switch (option) {
                case 1 -> RouteMenu.runMenu();
                case 4 -> System.exit(0);
            }
        } while (true);
    }
}