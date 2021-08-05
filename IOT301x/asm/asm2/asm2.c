#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define FILENAME "log.txt"
#define MAXLENGTH 5000

char myString[1000][MAXLENGTH];
int linesCount;

void fileRead(){
    FILE *fp = NULL;
    fp = fopen(FILENAME, "r");
    int count = 0;

    for(linesCount = 0;;linesCount++){
        fgets(myString[linesCount], MAXLENGTH, fp);
        // @ts-ignore
        if (myString[linesCount][0] == NULL) break;
    }
}

void soBanTinGuiDi(){
    int sentCount = 0;
    char temp[3];
    
    for(int i = 0; i < linesCount; i++){
        if(strstr(myString[i], "\"cmd\":\"set\"") != NULL){
            sentCount++;
        }
    }

    printf("So ban ti gui di: %d\n",sentCount);
}

int main(){
    fileRead();
    soBanTinGuiDi();
    
}