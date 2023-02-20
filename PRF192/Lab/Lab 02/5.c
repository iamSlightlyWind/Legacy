#include <stdio.h>

int main() {
    int a, b, c, d, e;
    float sum;
    printf("Enter 5 integers: ");
    scanf("%d %d %d %d %d", &a, &b, &c, &d, &e);

    sum = a + b + c + d + e;
    printf("The sum: %d\n", sum);

    printf("Percentage: %d(%.0f%%); %d(%.0f%%); %d(%.0f%%); %d(%.0f%%); %d(%.0f%%);", a, a / sum * 100, b, b / sum * 100, c, c / sum * 100, d, d / sum * 100, e, e / sum * 100);
}