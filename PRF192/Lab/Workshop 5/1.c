#include <math.h>
#include <stdio.h>

int main() {
    int total, x, y, count = 0;
    srand((unsigned int)time(NULL));

    printf("Dice Thrower\n============\n");
    do {
        printf("Total sought: ");
        scanf("%d", &total);
    } while (total < 2 || total > 12);

    do {
        x = rand() % 5 + 2;
        y = rand() % 5 + 2;
        printf("Result of throw %d: %d + %d\n", count + 1, x, y);
        count++;
    } while (x + y != total);

    printf("You got your total in %d throws!\n", count);
    return 0;
}
