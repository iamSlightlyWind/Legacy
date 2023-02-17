#include <stdio.h>

void printMinMaxDigits(int myInt) {
    int digit;
    int min, max;
    digit = myInt % 10;
    myInt = myInt / 10;
    min = max = digit;
    while (myInt > 0) {
        digit = myInt % 10;
        myInt = myInt / 10;
        if (min > digit)
            min = digit;
        if (max < digit)
            max = digit;
    }
    printf("\nMinimum digit: %d", min);
    printf("\nMaximum digit: %d", max);
}

int main() {
    int myInt;
    do {
        printf("Enter a non-negative integer: ");
        scanf("%d", &myInt);
        if (myInt >= 0) {
            printMinMaxDigits(myInt);
        }
    } while (myInt < 0);
    return 0;
}
