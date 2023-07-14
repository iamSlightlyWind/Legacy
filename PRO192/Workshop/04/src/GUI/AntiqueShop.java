package GUI;

import java.util.Scanner;

import DTO.*;

public class AntiqueShop {
    public static void main(String[] args) {
        String[] options = { " Create a Vase ", " Create a Statue", " Create a Statue", " Display the item" };
        Item item = null;
        int choice;
        do {
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    item = new Vase();
                    ((Vase) item).inputVase();
                    break;
                case 2:
                    item = new Statue();
                    ((Statue) item).inputStatue();
                    break;
                case 3:
                    item = new Painting();
                    ((Painting) item).inputPainting();
                    break;
                case 4:
                    if (item != null) {
                        item.output();
                    } else {
                        System.out.println("Please create an item first!");
                    }
                    break;
            }
        } while (choice != 0);
    }
}

class Menu {
    public static int getChoice(Object[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + "." + options[i]);
        }
        System.out.print("Choose an option: ");
        Scanner scan = new Scanner(System.in);
        int choice = Integer.parseInt(scan.nextLine());
        return choice;
    }
}
