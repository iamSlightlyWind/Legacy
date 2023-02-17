#include <stdio.h>

int main() {
    printf("Enter a year: ");
    int a;
    scanf("%d", &a);

    if (a % 4 == 0) {
        printf("It is a LEAP year.");
        return 0;
    }
    printf("It is NOT a leap year.");
}