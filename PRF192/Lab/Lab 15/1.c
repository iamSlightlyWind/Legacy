#include <stdio.h>
#include <string.h>

int main() {
    char myString[100];
    scanf("%s", myString);

    char placeHolder[] = "Have a good day, ";
    strcat(placeHolder, myString);

    FILE *fp = fopen("wishing", "w");

    if(fp != NULL){
        fputs(placeHolder,fp);
    }
    fclose(fp);

}
