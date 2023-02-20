#include <stdio.h>

int main() {
    printf("Enter a character: ");
    char a;
    scanf("%c", &a);

    switch (toupper(a)) {
        case 'M':
            printf("It's Monday");
            break;
        case 'T':
            printf("It's Tuesday");
            break;
        case 'W':
            printf("It's Wednesday");
            break;
        case 'H':
            printf("It's Thursday");
            break;
        case 'F':
            printf("It's Friday");
            break;
        case 'S':
            printf("It's Saturday");
            break;
        case 'U':
            printf("It's Sunday");
            break;
    }
}