#include <stdio.h>

int main() {
    float tempC, tempF;
    printf("Enter temp in Fahrenheit: ");
    scanf("%f", &tempF);

    tempC = (tempF - 32) / 9 * 5;

    printf("Temp %.2f in Fahrenheit = %.2f Celsius", tempF, tempC);
}