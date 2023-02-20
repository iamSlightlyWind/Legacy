#include <stdio.h>

int main() {
    int count = -1, lock = 1;
    while (count < 0) {
        if (lock == 1) {
            lock = 0;
        } else
            printf("\nYou should enter a positive number!");

        printf("\nEnter a number: ");
        scanf("%d", &count);
    }

    int fact = count;
    int factCount = count;

    for (;;) {
        if (factCount - 1 == 0)
            break;
        if (fact == 0) {
            fact = 1;
            break;
        }
        fact *= --factCount;
    }

    printf("%d! = %d", count, fact);
}