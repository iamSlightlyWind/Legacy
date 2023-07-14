public class Tester {
    public static void main(String[] args) {
        Car c = new Car();
        c.pressStartButton();
        c.pressAcceleratorButton();
        c.output();

        Car c2 = new Car("red", 100, true, true);
        c2.pressAcceleratorButton();
        c2.setColour("black");
        System.out.println("Colour of c2:" + c2.getColour());
        c2.output();
    }
}

/* Part 2: Find classes [3 points] and use UML to draw class structure (optional)
Problem: Mr. Hung is the owner of the shop that sells guitars. He wants you to build him a shop 
management app. This app is used for keeping track of guitars. Each guitar contains 
serialNumber, price, builder, model, back Wood, top Wood. The guitar can create a melodious 
sound. The shop will keep guitars in the inventory. The owner can add a new guitar to it, he 
search also the guitar by the serialNumber. 
Requirement: Write down classes in the problem and use UML draw class structure.
Note: show members of a class: fields and methods

Guideline: 
Apply design guideline : 
Main nouns: Guitar
Auxiliary nouns describe details of the guitar:serialNumber, price, builder, model, back 
Wood, top Wood.
Verbs describe behaviors of the guitar: create Sound

Continue finding other nouns
Main nouns: Inventory 
Auxiliary nouns describe details of the inventory: the list(array) of guitars
Verbs describe behaviors of the inventory: add a new guitar, search the guitar by serialNumber.

Using UML to draw a class diagram */