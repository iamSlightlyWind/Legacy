#include <stdio.h>

int getFunc() {
    printf("1. Process primes");
    printf("\n2. Print min, max digit in an integer");
    printf("\n3. Quit");
    printf("\nChoice: ");

    int choice;
    scanf("%d", &choice);

    return choice;
}

int getInt() {
    printf("\nEnter a positive int: ");
    int myInt;
    scanf("%d", &myInt);
    return myInt;
}

void getDigit() {
    for (int myInt = getInt(), current = myInt % 10, min = current, max = current;; myInt /= 10) {
        current = myInt % 10;
        current > max ? max = current : NULL;
        current < min ? min = current : NULL;

        if (myInt < 10) {
            printf("Max digit is: %d\nMin digit is: %d", max, min);
            break;
        }
    }
}

int isPrime() {
    for (int n = 2, myInt = getInt(); n < myInt; n++) {
        if (myInt % n == 0) {
            return 0;
        }
    }
    return 1;
}

int main() {
    do {
        switch (getFunc()) {
            case 1:
                switch (isPrime()) {
                    case 1:
                        printf("Given number is a prime number!");
                        break;
                    case 0:
                        printf("Given number is not a prime number!");
                        break;
                }
                break;

            case 2:
                getDigit();
                break;
            case 3: return 0;
        }

        printf("\n\n");
    } while (1);
}