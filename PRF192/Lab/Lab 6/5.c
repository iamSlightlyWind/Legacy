#include <stdio.h>

int getSmallest(int a, int b, int c)
{
    int min = a;
    if (min > b)
        min = b;

    if (min > c)
        min = c;

    return min;
}

int main()
{
    for (int a, b, c;;)
    {
        printf("\nEnter 3 integers: ");
        scanf("%d %d %d", &a, &b, &c);

        if(a == b || b == c || a == c){
            break;
        }

        printf("%d is the smallest", getSmallest(a, b, c));
    }
}