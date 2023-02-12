#include <stdio.h>
#include <math.h>

int isFibonacci(int myInt)
{
    int prev = 1, current = 1, fibo = 1;
    while (fibo < myInt)
    {
        int temp = fibo;
        fibo = prev + current;
        current = prev;
        prev = temp;
    }
    return fibo == myInt;
}

int main()
{
    int myInt;
    printf("Enter a positive integer: ");
    scanf("%d", &myInt);
    while (myInt < 1)
    {
        printf("Please enter a positive integer: ");
        scanf("%d", &myInt);
    }
    if (isFibonacci(myInt))
    {
        printf("It is a Fibonacci element.\n");
    }
    else
    {
        printf("It is not a Fibonacci element.\n");
    }
    return 0;
}
