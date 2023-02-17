#include <stdio.h>

int main() {
    for (int a, b, temp;;) {
        printf("A = ");
        scanf("%d", &a);
        if (a == 0) break;

        printf("B = ");
        scanf("%d", &b);
        if (b == 0) break;

        temp = a;
        a = b;
        b = temp;
        printf("\nSwapped!\nA = %d\nB = %d\n\n", a, b);
    }
}