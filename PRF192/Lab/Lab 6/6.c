#include <stdio.h>

int getSmallest(int n)
{
    int a = n / 10000;
    int b = (n % 1000) / 100;
    int c = n % 10;
    return (a + b + c) % 10;
}

int main()
{
    for (int n, count = 1;; count++)
    {
        printf("\n\nEnter %d", count);
        switch (count % 10)
        {
        case 1:
            printf("st");
            break;
        case 2:
            printf("nd");
            break;
        case 3:
            printf("rd");
            break;
        default:
            printf("th");
            break;
        }

        printf(" value: ");
        scanf("%d", &n);
        printf("Magic number = %d", getSmallest(n));

        if(getSmallest(n) == 0){
            break;
        }
    }
}