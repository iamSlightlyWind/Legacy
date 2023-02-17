#include <stdio.h>

int main() {
    int pa = 9000000, pd = 3600000, n, tf, ti, income, tax;

    printf("Your income of this year: ");
    scanf("%d", &income);

    printf("Number of dependent: ");
    scanf("%d", &n);

    printf("Tax-free income: %d", tf = (12 * (pa + n * pd)));
    printf("\nTaxable income: %d", ti = income > tf ? (income - tf) : (0));

    if (ti <= 5000000) {
        ti *= 0.05;
    } else if (ti <= 10000000) {
        ti *= 0.1;
    } else if (ti <= 18000000) {
        ti *= 0.15;
    } else
        ti *= 0.2;

    printf("\nIncome tax: %d", ti);
}