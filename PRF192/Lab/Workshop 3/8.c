#include <stdio.h>

double makeDouble(int myInt, int fraction)
{
    double fract = fraction;
    while (fract >= 1)
    {
        fract = fract / 10;
    }
    if (myInt < 0)
    {
        return myInt - fract;
    }
    return myInt + fract;
}

int main(void)
{
    int myInt, fraction;
    double value;
    printf("Enter the integral part: ");
    scanf("%d", &myInt);
    do
    {
        printf("Enter the fraction (positive value only): ");
        scanf("%d", &fraction);
    } while (fraction < 0);
    value = makeDouble(myInt, fraction);
    printf("The real number is: %.3f\n", value);
    return 0;
}
