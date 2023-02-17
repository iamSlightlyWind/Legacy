#include <stdio.h>

int sumDigits(int myInt) {
    int sum = 0;
    while (myInt > 0) {
        sum += myInt % 10;
        myInt /= 10;
    }
    return sum;
}

int main() {
    int myInt, sum = 0;
    do {
        printf("Enter a non-negative integer: ");
        scanf("%d", &myInt);
        if (myInt >= 0) {
            sum = sumDigits(myInt);
            printf("The sum of its decimal digits is: %d\n", sum);
        }
    } while (myInt >= 0);
    return 0;
}
