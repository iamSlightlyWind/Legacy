#include <stdio.h>

int main()
{
    int a, b, c, d;
    printf("Enter 3 integers: ");
    scanf("%d %d %d", &a, &b, &c);

    if (a > b && a > c)
    {
        d = a;
    }
    else if (b > a && b > c)
    {
        d = b;
    }
    else if (c < a)
    {
        d = a;
    }
    else if (c < b)
    {
        d = b;
    }
    else
        d = c;

    printf("%d is the greatest.", d);
}