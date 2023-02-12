#include <stdio.h>

int main()
{
    int gender, lower, upper;
    float weight, height, bmi;

    int *gender_ptr = &gender, *lower_ptr = &lower, *upper_ptr = &upper;
    float *weight_ptr = &weight, *height_ptr = &height, *bmi_ptr = &bmi;

    printf("Enter your gender (0 for female, 1 for male): ");

    scanf("%d", gender_ptr);

    printf("Enter your weight (kg) and height (m): ");

    scanf("%f %f", weight_ptr, height_ptr);

    switch (*gender_ptr)
    {
    case 0:
        *lower_ptr = 19;
        *upper_ptr = 24;
        break;
    case 1:
        *lower_ptr = 20;
        *upper_ptr = 25;
        break;
    }

    *bmi_ptr = *weight_ptr / (*height_ptr * *height_ptr);

    if (*bmi_ptr < *lower_ptr)
    {
        printf("You are a little skinny");
    }
    else if (*bmi_ptr > *upper_ptr)
    {
        printf("You are a little big");
    }
    else
        printf("You are in good shape");
}