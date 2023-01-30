#include <stdio.h>

int main()
{
    int count = 0, age = 0;
    while (age < 1 || age > 100)
    {
        printf("Enter age: ");
        scanf("%d", &age);
        count++;
    }
    printf("\nYour age is %d", age);
    printf("\nNumber of attempts = %d", count);
}