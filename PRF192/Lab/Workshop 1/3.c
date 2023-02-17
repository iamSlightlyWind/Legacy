#include <stdio.h>

int main() {
    int current = -1, sum = 1;  // offset
    while (current != 0) {
        sum += current;
        printf("Enter a number: ");
        scanf("%d", &current);
    }
    printf("sum: %d", sum);
}