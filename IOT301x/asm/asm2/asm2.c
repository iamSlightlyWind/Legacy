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
    int customTimerSoIWontMessWithTheCalculator  = 0;
    char tempStr[5];
    int s1,s2,s3,r1,r2,r3;
    int sentTime = 0, recvTime = 0;
    for (int i = 2; i < linesCount+2; i++){
        if(customTimerSoIWontMessWithTheCalculator == 2){
            if (maxDelay < (recvTime - sentTime)){
                maxDelay = (recvTime - sentTime);
            }customTimerSoIWontMessWithTheCalculator = 0;
        }

        if(i % 2 == 0){
            s1 = atol(strncpy(tempStr, myString[i-2] + 20,2));
            s2 = atol(strncpy(tempStr, myString[i-2] + 23,2));
            s3 = atol(strncpy(tempStr, myString[i-2] + 26,3));
            memset(tempStr, 0, 3);
            sentTime = s1*60000 + s2*1000 + s3;
            customTimerSoIWontMessWithTheCalculator++;
        }

        if(i % 2 != 0){
            r1 = atol(strncpy(tempStr, myString[i-2] + 20,2));
            r2 = atol(strncpy(tempStr, myString[i-2] + 23,2));
            r3 = atol(strncpy(tempStr, myString[i-2] + 26,3));
            memset(tempStr, 0, 3);
            printf("\n%d:\n%d\n%d\n%d\n\n",i-2,s1,s2,s3);
            recvTime = r1*60000 + r2*1000 + r3;
            customTimerSoIWontMessWithTheCalculator++;
        }
    }
    printf("\nDo tre lon nhat la: %d",maxDelay);
}

int main(){
    fileRead();

    int sentLines[100];
    int sentCount = soBanTinGuiDi(&sentLines);
    soBanTinTuThietBi(sentCount,&sentLines);
    getMaxDelay();

}