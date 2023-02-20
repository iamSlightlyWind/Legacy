#include <stdio.h>

int main() {
    printf("Enter value: ");
    int uh;
    scanf("%d", &uh);
    printf("%d", uh);
    switch (uh % 10) {
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
}