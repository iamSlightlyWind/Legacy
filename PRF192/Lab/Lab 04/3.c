#include <stdio.h>

int main() {
    int sum = 0;
    for (int i = 9; i < 300; i++) {
        if (i % 7 == 0 && i % 63 != 0) {
            sum += i;
        }
    }
    printf("Sum of integers between 9 & 300 that are divisible by 7 but not by 63 is %d", sum);
}