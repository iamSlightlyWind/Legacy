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

int soBanTinGuiDi(int *sentLines){
    int sentCount = 0;
    char temp[3];
    int sLineCounts = 0;
    
    for(int i = 0; i < linesCount; i++){
        if(strstr(myString[i], "\"cmd\":\"set\"") != NULL){
            sentCount++;
            *(sentLines + sLineCounts++) = i;
        }
    }

    printf("So ban ti gui di: %d\n",sentCount);

    return sentCount;
}

void soBanTinTuThietBi(){
    char deviceName[100];
    int lineContains[100];
    int cLineCounts = 0;
    printf("Nhap dia chi nwk cua thiet bi:");
    scanf("%s",deviceName);

    for(int i = 0; i < linesCount; i++){
        if(strstr(myString[i], deviceName) != NULL){
            lineContains[cLineCounts++] = i;
        }
    }
}

int main(){
    fileRead();

    int sentLines[100];
    int sentCount = soBanTinGuiDi(&sentLines);
    
}