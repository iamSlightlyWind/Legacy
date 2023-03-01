#include <stdio.h>

int main() {
    char myString[1024];
    int spaceCount = 2, wordCount = 0;

    printf("Enter a sentence: ");
    scanf("%[^\n]s", myString);

    for (int i = 0; i < strlen(myString); i++) {
        if (myString[i] == ' ') {
            if (spaceCount == 1 && myString[i + 1] != ' ' && myString[i + 1] != '\0') {
                if (wordCount == 0 && i != 0) wordCount++;
                wordCount++;
            }

            if (spaceCount == 2 && myString[i + 1] == ' ' && myString[i + 2] != '\0') {
                if (wordCount == 0 && i != 0) wordCount++;
                wordCount++;
            }
        }
    }

    printf("Word count: %d", wordCount);
}
