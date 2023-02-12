#include <stdio.h>

double fToC(double fDeg)
{
    return (fDeg - 32.0) * (5.0 / 9.0);
}

int main()
{
    printf("Enter temp in Fahrenheit: ");
    double n;
    scanf("%lf", &n);
    printf("Temp %.2lf in Fahrenheit = %.2lf Celsius", n, fToC(n));
}