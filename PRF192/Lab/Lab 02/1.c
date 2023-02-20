#include <stdio.h>

int main() {
    printf("Enter the distance in inches: ");

    float inches;
    scanf("%f", &inches);
    float cms = inches * 2.54;

    printf("%.2f inches = %.2f cm", inches, cms);
}