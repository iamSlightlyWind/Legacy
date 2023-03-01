#include <stdio.h>

int main() {
    int size = 1024;
    char first[size], middle[size], last[size];

    printf("Please enter your first name: ");
    scanf("%s", first);
    printf("Please enter your last name: ");
    scanf("%s", last);
    printf("Please enter your middle name: ");
    scanf("%s", middle);

    printf("\n“F M L” format: %s %s %s", first, middle, last);
    printf("\n“L M F” format: %s %s %s", last, middle, first);

    return 0;
}
