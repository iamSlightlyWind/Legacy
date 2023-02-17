#include <stdio.h>

int main() {
    int current = 1, count = 0, sum = -1;  // offset
    int max = -1, min = -1;

    for (count--; current > 0; count++) {
        sum += current;

        if (count > -1) {
            if (max < 0 || max < current)
                max = current;
            if (min < 0 || min > current)
                min = current;
        }
        printf("Enter a positive integer: ");
        scanf("%d", &current);
    }

    printf("\nNumber of positive values entered is %d", count);
    printf("\nMaximum value entered is %d", max);
    printf("\nMinimum value entered is %d", min);
    printf("\nAverage value is %f", (double)sum / count);
}