#include <stdio.h>

void printArray(int myInt[], int size, int toFree) {
    for (int i = 0; i < size; i++) {
        printf("\n[%d]: %d", i, myInt[i]);
    }
    
    if (toFree) free(myInt);
}

void findInRange(int myInt[], int *size) {
    int range[2];
    printf("Enter min and max value of range: ");
    scanf("%d %d", range[0], range[1]);
}

void findInArray(int myInt[], int *size) {
    printf("Enter a value to search for: ");
    int value;
    scanf("%d", &value);

    for (int i = 0; i < *size; i++) {
        if (myInt[i] == value) {
            printf("\n[%d]: %d", i, myInt[i]);
        }
    }
}

void addValue(int myInt[], int *size) {
    printf("Enter a value to add: ");
    int newValue;
    scanf("%d", &newValue);

    myInt[*size] = newValue;
    *size += 1;
}

int printMenu() {
    printf("\n-------------------------------------------");
    printf("\n1. Add a value");
    printf("\n2. Search a value");
    printf("\n3. Print out the array");
    printf("\n4. Print out values in a range");
    printf("\n5. Print out the array in ascending order");
    printf("\n0. Quit");
    printf("\n\nEnter choice: ");

    int choice;
    scanf("%d", &choice);

    return choice;
}

int *sort(int myInt[], int size) {
    for (int i = 0; i < size; i++) {
        for (int x = i + 1, temp; x < size; x++) {
            if (myInt[x] < myInt[i]) {
                temp = myInt[i];
                myInt[i] = myInt[x];
                myInt[x] = temp;
            }
        }
    }
    return &myInt[0];
}

int *getClone(int myInt[], int size) {
    int *clone = malloc(sizeof(int) * size);
    if (clone == NULL) {
        return -256;
    }

    for (int i = 0; i < size; i++) {
        clone[i] = myInt[i];
    }
    return &clone[0];
}

int main() {
    int myInt[100] = {0, 2, 5, 7, 3, 1, 8, 0, 4, 3};  // maximum of 100 elements
    int size = 10;

    // int *pointer = getClone(myInt,size);

    while (1) {
        switch (printMenu()) {
            case 1:
                addValue(myInt, &size);
                break;
            case 2:
                findInArray(myInt, &size);
                break;
            case 3:
                printArray(myInt, size, 0);
                break;
            case 4:
                break;
            case 5:
                printArray(sort(getClone(myInt, size), size), size, 1);
                break;

            default:
                return 0;
        }
    }
}