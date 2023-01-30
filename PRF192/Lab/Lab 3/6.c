#include <stdio.h>

int main()
{
    printf("Enter 3 positive integers: ");
    int a, b, c;
    scanf("%d %d %d", &a, &b, &c);
    printf("The integers are %d, %d and %d", a, b, c);
    a = (a % 1000) / 100;
    b = (b % 1000) / 100;
    c = (c % 1000) / 100;
    if (a < b && b < c)
    {
        printf("Yes");
    }
    else
        printf("No");
}