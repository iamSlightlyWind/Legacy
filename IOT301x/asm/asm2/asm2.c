#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

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

    printf("\n\nNhap dia chi nwk cua thiet bi: ");
    scanf("%s",deviceName);
    upperString(&deviceName,strlen(deviceName));

    for(int i = 0; i < sentCount; i++){
        if(strstr(myString[*(sentLines + i)], deviceName) != NULL){
            tempLineContains[tempSentLineCounts++] = i;
            tempDeviceSentCounts++;
            printf("%s\n",myString[tempLineContains[i]]);
        }
    }

    printf("So ban tin gui di cua thiet bi: %d",tempDeviceSentCounts);

}

void upperString(char *givenString, int strLength){
    for(int i = 0; i < strLength; i++){
        *(givenString + i) = toupper(*(givenString+i));
    }
}

void getMaxDelay(){
    int maxDelay = 0;
    char tempStr[100];
    long int p1,p2,p3;
    long int sentTime = 0, recvTime = 0;
    for (int i = 0; i < linesCount-1; i++){
        if(i % 2==0){
            memset(tempStr, 0, 255);p1 = atol(strncpy(tempStr, myString[i] + 20,2));
            memset(tempStr, 0, 255);p2 = atol(strncpy(tempStr, myString[i] + 23,2));
            memset(tempStr, 0, 255);p3 = atol(strncpy(tempStr, myString[i] + 26,3));
            //sentTime = p1*60000 + p2*1000 + p3;
            printf("\n%d\n%d\n%d\n\n",p1,p2,p3);
        }
    }
}

int main(){
    fileRead();

    int sentLines[100];
    int sentCount = soBanTinGuiDi(&sentLines);
    soBanTinTuThietBi(sentCount,&sentLines);
    getMaxDelay();

}