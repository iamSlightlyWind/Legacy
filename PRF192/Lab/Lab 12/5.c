#include <stdio.h>
#include <string.h>

int main() {
    char myString[1024];

    printf("Enter a string: ");
    scanf("%s", myString);

    int length = strlen(myString);

    for (int i = 0; i < length; i++) {
        if (myString[i] >= 'A' && myString[i] <= 'Z'){
            myString[i] += 32;
        }
    }

    for (int i = 0; i < length / 2; i++) {
        if (myString[i] != myString[length - i - 1]) {
            printf("\"%s\" is not a palindrome\n", myString);
            break;
        }
    }

    printf("%s is a palindrome\n", myString);

    return 0;
}