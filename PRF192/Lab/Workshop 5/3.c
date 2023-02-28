#include <stdio.h>

int isLeapYear(int year) {
    return year % 4 == 0 ? 1 : 0;
}

void isDateValid() {
    int dd, mm, yyyy;
    int isLeap = isLeapYear(yyyy);

    printf("Enter date (dd mm yyyy): ");
    scanf("%d %d %d", &dd, &mm, &yyyy);

    switch (mm) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            if (dd > 0 && dd < 32) {
                printf("is a valid date");
            }
            return;
        case 4:
        case 6:
        case 9:
        case 11:
            if (dd > 0 && dd < 31) {
                printf("is a valid date");
            }
            return;
        case 2:
            if (dd > 0) {
                if (isLeap) {
                    if (dd < 30) {
                        printf("is a valid date");
                    }
                } else if (dd < 29) {
                    printf("is not a valid date");
                }
            }
            return;
    }
    printf("is not a valid date");
}

int main() {
    int choice = 0;

    printf("Choice: ");
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            isDateValid();
            break;

        case 2:
            char myChars[2];
            printf("Enter chars: ");
            scanf("%c%c", &myChars[0], &myChars[1]);
            if(myChars[0] > myChars[1]){
                printf("%d, %d",myChars[0],myChars[1]);
            }else printf("%d, %d",myChars[1],myChars[0]);
    }
}
