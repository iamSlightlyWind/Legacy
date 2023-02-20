#include <stdio.h>

int main() {
    float a, b, temp;
    printf("Enter the first number: ");
    scanf("%f", &a);
    printf("Enter the second number: ");
    scanf("%f", &b);

    printf("Values entered are a=%f and b=%f\n", a, b);

    temp = a;
    a = b;
    b = temp;

    printf("Values after swap are a=%f and b=%f", a, b);
}