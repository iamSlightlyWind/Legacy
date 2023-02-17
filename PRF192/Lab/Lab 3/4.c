#include <stdio.h>

int main() {
    printf("Enter your gender (0 for female, 1 for male): ");
    int gender;
    scanf("%d", &gender);

    printf("Enter your weight (kg) and height (m): ");
    float weight, height;
    scanf("%f %f", &weight, &height);

    int lower, upper;
    switch (gender) {
        case 0:
            lower = 19;
            upper = 24;
            break;
        case 1:
            lower = 20;
            upper = 25;
            break;
    }

    float bmi = weight / (height * height);

    if (bmi < lower) {
        printf("You are a little skinny");
    } else if (bmi > upper) {
        printf("You are a little big");
    } else
        printf("You are in good shape");
}