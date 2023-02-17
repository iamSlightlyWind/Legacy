#include <stdio.h>

int greatestDivisor(int a, int b) {
    while (a != b) {
        if (a > b) {
            a -= b;
        } else {
            b -= a;
        }
    }
    return a;
}

int leastMultiple(int a, int b) {
    return a * b / greatestDivisor(a, b);
}

int main(void) {
    int a, b, d, m;
    do {
        printf("Enter two positive integers: ");
        scanf("%d%d", &a, &b);
    } while (a <= 0 || b <= 0);
    d = greatestDivisor(a, b);
    m = leastMultiple(a, b);
    printf("The greatest common divisor of %d and %d is: %d\n", a, b, d);
    printf("The least common multiple of %d and %d is: %d\n", a, b, m);
    return 0;
}
