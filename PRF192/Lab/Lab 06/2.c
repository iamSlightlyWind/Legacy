#include <stdio.h>

double inchToCm(double inches) {
    return inches * 2.54;
}

int main() {
    printf("Enter the distance in inches: ");
    double n;
    scanf("%lf", &n);
    printf("%.2lf inches = %.2lf cm", n, inchToCm(n));
}