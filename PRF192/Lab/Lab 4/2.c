#include <stdio.h>

int main()
{
    int number = 0;
    int max = 0;
    while(number >= 0){
        printf("Enter a number: ");
        scanf("%d", &number);
        if(number > max) max = number;
    }
    printf("The maximum number is %d", max);
}