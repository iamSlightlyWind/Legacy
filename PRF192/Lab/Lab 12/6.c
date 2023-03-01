#include <stdio.h>
#include <string.h>

void rearrange(char myString[]) {
    int vowelCount = 0, current = 0, leftoverCount = 0, vowelFirst = 0;

    char vowel[strlen(myString)];
    char preset[] = {'a', 'e', 'i', 'o', 'u'};
    char leftoever[strlen(myString)];

    for (int i = 0; i < strlen(myString); i++) {
        vowel[i] = '\0';
        leftoever[i] = '\0';
    }

    for (int i = 0; i < strlen(myString); i++) {
        current = vowelCount;

        for (int x = 0; x < strlen(preset); x++) {
            if (myString[i] == preset[x]) {
                vowel[vowelCount++] = myString[i];
                if (i == 0) vowelFirst = 1;
            }
        }

        if (current == vowelCount) {
            leftoever[leftoverCount++] = myString[i];
        }
    }

    if (vowelFirst) {
        strcat(vowel, leftoever);
        strcpy(myString, vowel);
    } else {
        strcat(leftoever, vowel);
        strcpy(myString, leftoever);
    }

    return;
}

int main() {
    char myString[] = "Hello";
    rearrange(myString);
    printf("%s", myString);

    return 0;
}
