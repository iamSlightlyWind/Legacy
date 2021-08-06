#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

#define FILENAME "log.txt"
#define MAXLENGTH 5000

char myString[1000][MAXLENGTH];
int linesCount;
int delay[100];
int delayCounter = 0;

void fileRead(){
    FILE *fp = fopen(FILENAME, "r");
    int count = 0;

    for(linesCount = 0;;linesCount++){
        fgets(myString[linesCount], MAXLENGTH, fp);
        // @ts-ignore
        if (myString[linesCount][0] == NULL) break;
    }
}

int soBanTinGuiDi(int *sentLines){//also returns how many lines are used to sent info(s)
    int tempSentCount = 0;
    int tempSentLines = 0;
    
    for(int i = 0; i < linesCount; i++){//check whether or not a line is a log of sent info(s)
        if(strstr(myString[i], "\"cmd\":\"set\"") != NULL){
            tempSentCount++;
            *(sentLines + tempSentLines++) = i; //save the line number
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

    for(int i = 0; i < sentCount; i++){                                 //
        if(strstr(myString[*(sentLines + i)], deviceName) != NULL){     //if destination line contains (parts of) device address
            tempLineContains[tempSentLineCounts++] = i;                 //then mark the line which contains the device address
            tempDeviceSentCounts++;                                     //and increase the count of times which device has been called
            printf("%s\n",myString[tempLineContains[i]]);               //
        }                                                               //
    }                                                                   //

    printf("So ban tin gui di cua thiet bi: %d",tempDeviceSentCounts);

}

void upperString(char *givenString, int strLength){//uppercase the input address to match device address in log
    for(int i = 0; i < strLength; i++){
        *(givenString + i) = toupper(*(givenString+i));
    }
}

void getMaxDelay(){
    int maxDelay = 0;
    int customTimerSoIWontMessWithTheCalculator = 0;
    char tempStr[5];
    int s1,s2,s3,r1,r2,r3;
    int sentTime = 0, recvTime = 0;
    for (int i = 0; i < linesCount+1; i++){
        if(customTimerSoIWontMessWithTheCalculator == 2){   //
            if (maxDelay < (recvTime - sentTime)){          //maxDelay compares and rewrite
                maxDelay = (recvTime - sentTime);           //
            }customTimerSoIWontMessWithTheCalculator = 0;   //

            delay[delayCounter] = recvTime - sentTime;      //log delay times for thoiGianTreTrungBinh()
            delayCounter++;                                 //
        }

        if(i <= linesCount){
            if(i % 2 == 0){
                s1 = atol(strncpy(tempStr, myString[i] + 20,2));    //
                s2 = atol(strncpy(tempStr, myString[i] + 23,2));    //consider location is set, get substring and atol convert said substring into int
                s3 = atol(strncpy(tempStr, myString[i] + 26,3));    //
                memset(tempStr, 0, 3);      //strncpy does not null terminate, empty tempStr instead
                sentTime = s1*60000 + s2*1000 + s3;
                customTimerSoIWontMessWithTheCalculator++;          //only trigger the recalculation of delay once got both sent and recv time ie == 2
            }

            if(i % 2 != 0){
                r1 = atol(strncpy(tempStr, myString[i] + 20,2));    //
                r2 = atol(strncpy(tempStr, myString[i] + 23,2));    //same as above
                r3 = atol(strncpy(tempStr, myString[i] + 26,3));    //
                memset(tempStr, 0, 3);                              //
                recvTime = r1*60000 + r2*1000 + r3;
                customTimerSoIWontMessWithTheCalculator++;
            }
        }
    }
    printf("\n\nDo tre lon nhat la: %d",maxDelay);
}

void soBanTinGuiLoi(){
    int customTimerSoIWontMessWithTheCalculator = 0;
    char tempStr[5];
    int sID, rID;
    int error = 0;
    for (int i = 0; i < linesCount+1; i++){
        if(customTimerSoIWontMessWithTheCalculator == 2){
            if (sID != rID) {error++;}                      //increase error counts
            customTimerSoIWontMessWithTheCalculator = 0;    //once got both sent and recv id
        }

        if(i <= linesCount){
            if(i % 2 == 0){
                sID = atol(strncpy(tempStr, myString[i] + 185,4));  //get sent id
                customTimerSoIWontMessWithTheCalculator++;          //
            }

            if(i % 2 != 0){
                rID = atol(strncpy(tempStr, myString[i] + 199,4));  //get recived id
                customTimerSoIWontMessWithTheCalculator++;          //
            }
        }
    }
    printf("\n\nSo ban tin loi: %d",error);
}

void thoiGianTreTrungBinh(){
    int totalDelay = 0;
    for(int i = 0; i < delayCounter; i++){
        totalDelay += delay[i];
    }
    printf("\n\nDe tre trung binh la: %d",totalDelay/delayCounter);
}

int main(){
    fileRead();

    int sentLines[100];
    int sentCount = soBanTinGuiDi(&sentLines);
    soBanTinTuThietBi(sentCount,&sentLines);
    soBanTinGuiLoi();
    getMaxDelay();
    thoiGianTreTrungBinh();
}