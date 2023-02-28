#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    int balls[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int total_sought, num_picks = 0, sum;
    srand(time(NULL));

    printf("Ball Lottery\n============\n");
    do {
        printf("Enter the total you are looking for (2 - 20): ");
        scanf("%d", &total_sought);
    } while (total_sought < 2 || total_sought > 20);

    while (1) {
        int index1 = rand() % 10;
        int index2 = rand() % 10;
        if (index1 == index2) continue;
        num_picks++;
        sum = balls[index1] + balls[index2];
        printf("Result of picks %d and %d: %d + %d = %d\n", 2 * num_picks - 1, 2 * num_picks, balls[index1], balls[index2], sum);
        if (sum == total_sought) {
            printf("You got your total in %d picks!\n", num_picks);
            break;
        }
    }
    return 0;
}
