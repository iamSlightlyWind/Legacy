#include <math.h>
#include <stdio.h>

void quadCalc() {
    printf("A auadratic equation has the form of \"ax^2 + bx + c = 0\"");
    double a, b, c;
    printf("\nEnter a: ");
    scanf("%lf", &a);
    printf("Enter b: ");
    scanf("%lf", &b);
    printf("Enter c: ");
    scanf("%lf", &c);

    double delta = b * b - 4 * a * c;

    if (delta < 0) {
        printf("The equation has no solution!");
        return;
    }

    if (delta == 0) {
        printf("The equation has a double solution of %lf", (-b) / (2 * a));
        return;
    }

    double solution[] = {((-b) + sqrt(delta)) / (2 * a),
                         ((-b) - sqrt(delta)) / (2 * a)};

    printf("The equation have 2 solutions of %.2lf and %.2lf", solution[0], solution[1]);
}

void bankRate() {
    double deposit = -1, rate = -1, years = -1;
    while (deposit <= 0) {
        printf("Enter deposit amount (>0): ");
        scanf("%lf", &deposit);
    }

    while (rate <= 0.0 || rate >= 1.0) {
        printf("Enter yearly rate (>0.0 & < 1.0): ");
        scanf("%lf", &rate);
    }

    while (years <= 0) {
        printf("Enter number of years (>0): ");
        scanf("%lf", &years);
    }

    printf("Final amount: %.2lf", deposit * pow((1 + rate), years));
}

int main() {
    int choice = 0;

    printf("Choice: ");
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            quadCalc();
            break;

        case 2:
            bankRate();
            break;
    }
}
