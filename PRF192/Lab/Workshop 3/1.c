#include <stdio.h>

int isPrime(int myInt) {
    for (int n = 2; n < myInt; n++) {
        if (myInt % n == 0) {
            return 0;
        }
    }
    return 1;
}

int main() {
    int myInt = -1;

    while (myInt < 0) {
        printf("Enter a positive int: ");
        scanf("%d", &myInt);
    }

    switch (isPrime(myInt)) {
        case 1:
            printf("Given number is a prime number");
            break;
        case 0:
            printf("Given number is not a prime number");
            break;
    }
}