## Workshop tree: 

`Verify`:
The class Verify is used to validate user input. It has 3 methods: getString, getInt, and getBoolean. Each method will ask the user to input a value of a specific type (String, int, boolean) and return that value. If the user input is invalid, the method will ask the user to input again until the input is valid.

`Statue`, `Vase` and `Painting`:
Subclasses of Item. Each class has its own unique attribute and method.

`AntiqueShop`:
The main class of the program. Allow the user to create one of the said items and view its attributes.


## Workshop order

- The program starts with the main function of the AntiqueShop class.
- It will ask the user to choose an option from the menu using Menu.getChoice().
- If the user chooses to create an item, the previous item will be overwritten if it exists. Then the program will ask the user to enter the item's attributes.
- When the item's attributes are entered, it will be verified by the Verify class. If the input is invalid, the user will be asked to input again until the input is valid.
- If the user chooses to view the item's attributes, the program will display the item's attributes.
- If the user chooses to exit (choice > 4, forced by step 4), the program will end.


## Workshop questions
`What is stored in the static heap, stack, dynamic heap?`
- Static heap: store static variables, constants, and class definitions.
- Stack: stores local variables, function parameters, and function return addresses.
- Dynamic heap: stores objects and arrays.

`What are objects in the program?`
- Objects are instances of classes. They are created in the dynamic heap.

`What is the item variable storing?`
- The item variable is storing the address of the object in the dynamic heap.

`Why must you cast to call the method inputVase()/outputVase()?`
- Because the item variable is declared as an Item type, it can only call methods of the Item class. To call methods of the Vase class, we must cast the item variable to Vase type.

`What is the error thrown when you cast it wrong?`
- java.lang.ClassCastException

`What methods can you call if you don’t cast the item variable?`
- Methods of the Item class (super.method)
