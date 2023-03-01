#include <stdio.h>
#include <string.h>

int main() {
    char myString[3][1024];

    printf("Please enter 3 lowercase myString seperated by a space: ");
    scanf("%s %s %s", myString[0], myString[1], myString[2]);

    int max = 0, min = 2, size = 3;
    for (int i = 0; i < size; i++) {
        if (strcmp(myString[max], myString[i]) > 0) {
            max = i;
        } else if (strcmp(myString[min], myString[i]) < 0 && min != i) {
            min = i;
        }
    }

    printf("\nThe first string is: %s", myString[max]);
    printf("\nThe last string is: %s", myString[min]);

    return 0;
}
