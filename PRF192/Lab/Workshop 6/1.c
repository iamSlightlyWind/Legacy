#include <stdio.h>
#include <string.h>

int checkSin(int sin[10]) {
    int check[10];
    int sum = 0;

    for (int i = 0; i < 10; i++) {
        check[i] = sin[i] * (10 - i);
        sum += check[i];
    }

    if (sum % 11 == 0) {
        return 1;
    }

    return 0;
}

void getSin(int input, int *sin) {
    int div = 1000000000;
    for (int i = 0; i < 10; i++) {
        sin[i] = (input / div);
        if (sin[i] != 0) input %= div;
        div /= 10;
    }
}

int main() {
    int input, sin[10];

    printf("Enter a 10 digit number: ");
    scanf("%d", &input);

    if (input == 0) return 0;

    getSin(input, &sin);
    switch (checkSin(sin)) {
        case 0:
            printf("ISBN is not valid!");
            break;
        case 1:
            printf("ISBN is valid!");
            break;
    }

    return 0;
}