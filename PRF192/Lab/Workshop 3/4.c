#include <stdio.h>

void printFact(int count) {
    int fact = count;
    printf("%d! = %d ", count, count);
    for (; count > 2;) {
        fact *= --count;
        printf("%d ", fact);
    }
}

int main() {
    int count = -1;

    while (count < 0) {
        printf("Enter a positive number: ");
        scanf("%d", &count);
    }

    printFact(count);
}