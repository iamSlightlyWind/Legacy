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

void fibo() {
    int count, prev = 0, current = 1, next = prev + current;

    printf("Fibonacci count: ");
    scanf("%d", &count);

    printf("Fibonacci sequence: %d %d ", prev, current);

    for (int i = 3; i <= count; ++i) {
        printf("%d ", next);
        prev = current;
        current = next;
        next = prev + current;
    }
}

int getFunc() {
    printf("1. Fibonacci sequence");
    printf("\n2. Check a date");
    printf("\n3. Quit");
    printf("\n\nChoice: ");

    int choice;
    scanf("%d", &choice);

    return choice;
}

int main() {
    do {
        switch (getFunc()) {
            case 1:
                fibo();
                break;

            case 2:
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
                break;
            case 3:
                return 0;
        }
        printf("\n\n");
    } while (1);
}