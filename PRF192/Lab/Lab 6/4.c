#include <stdio.h>

int isLeapYear(int year)
{
    printf("");
    return year % 4 == 0 ? 1 : 0;
}

int main()
{
    for (int year;;)
    {
        printf("Enter a year: ");
        scanf("%d", &year);
        if (isLeapYear(year) == 0)
        {
            printf("It is NOT a leap year");
            continue;
        }
        printf("It is a LEAP year");
        break;
    }
}