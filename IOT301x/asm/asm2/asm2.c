#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define FILENAME "log.txt"
#define MAXLENGTH 5000

char myString[1000][MAXLENGTH];
int linesCount;


void fileRead(){
    FILE *fp = fopen(FILENAME, "r");
    int count = 0;

    for(linesCount = 0;;linesCount++){
        fgets(myString[linesCount], MAXLENGTH, fp);
        // @ts-ignore
        if (myString[linesCount][0] == NULL) break;
    }
}

int soBanTinGuiDi(int *sentLines){
    int tempSentCount = 0;
    int tempSentLines = 0;
    
    for(int i = 0; i < linesCount; i++){
        if(strstr(myString[i], "\"cmd\":\"set\"") != NULL){
            tempSentCount++;
            *(sentLines + tempSentLines++) = i;
        }
    }

    printf("So ban tin gui di: %d\n",tempSentCount);

    return tempSentCount;
}

void soBanTinTuThietBi(int sentCount,int *sentLines){
    char deviceName[100];

    int tempLineContains[100];
    int tempSentLineCounts = 0;
    int tempDeviceSentCounts = 0;

    printf("\n\nNhap dia chi nwk cua thiet bi:");
    scanf("%s",deviceName);
    upperString(&deviceName,strlen(deviceName));

    for(int i = 0; i < sentCount; i++){
        if(strstr(myString[*(sentLines + i)], deviceName) != NULL){
            tempLineContains[tempSentLineCounts++] = i;
            tempDeviceSentCounts++;
            printf("%s\n",myString[tempLineContains[i]]);
        }
    }

    //for(int i = 0; i < tempSentLineCounts; i++){
    //    printf("%s\n",myString[tempLineContains[i]]);
    //}
    printf("So ban tin gui di cua thiet bi: %d",tempDeviceSentCounts);

}

void upperString(char *givenString, int strLength){
    for(int i = 0; i < strLength; i++){
        *(givenString + i) = toupper(*(givenString+i));
    }
}

int main(){
    fileRead();

    int sentLines[100];
    int sentCount = soBanTinGuiDi(&sentLines);
    soBanTinTuThietBi(sentCount,&sentLines);

    
}