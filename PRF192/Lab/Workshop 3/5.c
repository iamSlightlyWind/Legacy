#include <stdio.h>

int fibo(int myInt)
{
    int prev = 1, current = 1, next, i;
    
    if (myInt <= 2)
        return 1;
    
    for (i = 3; i <= myInt; i++)
    {
        next = prev + current;
        prev = current;
        current = next;
    }
    return next;
}

int main()
{
    int myInt;
    do
    {
        printf("Enter a positive integer: ");
        scanf("%d", &myInt);
    } while (myInt < 1);
    printf("The value at the %dth position in the Fibonacci sequence is %d\n", myInt, fibo(myInt));
    return 0;
}