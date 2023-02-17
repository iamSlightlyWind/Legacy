#include <stdio.h>

int isDateValid(int dd, int mm, int isLeapYear) {
    switch (mm) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            if (dd > 0 && dd < 32) {
                return 1;
            }
        case 4:
        case 6:
        case 9:
        case 11:
            if (dd > 0 && dd < 31) {
                return 1;
            }
            return 0;
        case 2:
            if (dd > 0) {
                if (isLeapYear) {
                    if (dd < 30) {
                        return 1;
                    }
                } else if (dd < 29) {
                    return 1;
                }
            }
            return 0;
    }
}

int isLeapYear(int year) {
    return year % 4 == 0 ? 1 : 0;
}

int main() {
    int dd, mm, yyyy;

    printf("Enter date (dd mm yyyy): ");
    scanf("%d %d %d", &dd, &mm, &yyyy);

    printf("%d/%d/%d ", dd, mm, yyyy);
    switch (isDateValid(dd, mm, isLeapYear(yyyy))) {
        case 1:
            printf("is a valid date");
            break;
        case 0:
            printf("is not a valid date");
            break;
    }
}