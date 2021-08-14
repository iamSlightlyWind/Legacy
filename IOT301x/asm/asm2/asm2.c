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

void fileRead(){//read file and write line by line to array
    FILE *fp = fopen(FILENAME, "r");
    int count = 0;

    for(linesCount = 0;;linesCount++){
        fgets(myString[linesCount], MAXLENGTH, fp);
        // @ts-ignore
        if (myString[linesCount][0] == NULL) break;
    }
}

void soBanTinGuiDi(int *sentLines, int *recvLines, int *sentCount, int* recvCount){//count sent lines, also returns how many lines are used to sent info(s)
    int tempSentLines = 0, tempRecvLines = 0;
    
    for(int i = 0; i < linesCount; i++){                                //check whether or not a line is a log of sent info(s)
        if(strstr(myString[i], "\"cmd\":\"set\"") != NULL){
            *sentCount += 1;
            *(sentLines + tempSentLines++) = i;                         //save the line number
        }

        if(strstr(myString[i], "\"cmd\":\"status\"") != NULL){
            *recvCount += 1;
            *(recvLines + tempRecvLines++) = i;                         //save the line number
        }
    }

    printf("So ban tin gui di: %d\n",*sentCount);
}

void soBanTinTuThietBi(int sentCount,int *sentLines){//check if input device name == device id got from sent lines
    char deviceName[5];

    int LineContainsDevice[1000];
    int DeviceSentCounts = 0;

    char *pfound = strstr(myString[0], "zwave-");                                                   //find distance to "zwave-"
    int distanceToAddress = (int)(pfound - myString[0])+6;                                          //+6 = distance starting after "zwave-" (ie distance to device address)

    printf("\n\nNhap dia chi nwk cua thiet bi: ");
    scanf("%s",deviceName);
    lowerString(&deviceName,strlen(deviceName));

    char temp[5];
    for(int i = 0; i < sentCount; i++){
        if(strcmp(deviceName,strncpy(temp,myString[sentLines[i]] + distanceToAddress,4)) == 0){     //if deviceName == deviceID got from sent line
            LineContainsDevice[DeviceSentCounts++] = i;                                             //
            printf("%s\n",myString[LineContainsDevice[i]]);                                           
        }
    }
    printf("So ban tin gui di cua thiet bi: %d",DeviceSentCounts);
}

void lowerString(char *givenString, int strLength){//lowercase the input address to match device address in log
    for(int i = 0; i < strLength; i++){
        *(givenString + i) = tolower(*(givenString+i));
    }
}

void thoiGianTreLonNhat(int *sentLines, int *recvLines, int *errorLines, int *sentCount, int* recvCount,int errorCount){//get largest delay of valid sents and receives
    int maxDelay = 0;
    int calculationTrigger = 0;
    char tempStr[5];
    int sMins, sSeconds, sMilliseconds, rMins, rSeconds, rMilliseconds;
    int sentTime = 0, recvTime = 0;
    int tempSentCount = 0, tempRecvCount = 0;
    int skip = 0;                                                   //used to 'continue' loop when encountered a line with error

    char *pfound = strstr(myString[0], "zwave-");                   //find distance to "zwave-"
    int distanceToAddress = (int)(pfound - myString[0])+6;          //pfound + 6 = distance starting after "zwave-" (ie distance to device address,"zwave-" = 6)


    for (int i = 0; i < linesCount+1; i++){
        for(int x = 0; x < errorCount; x++){
            if(i == errorLines[x]){                                 //if the line is already marked as contains error
                skip = 1;                                           //set skip condition
            }
        }
        
        if(skip == 1){
            skip = 0;                                               //reset skip condition
            if(i == sentLines[tempSentCount]){tempSentCount++;}     //sync the counter of sentLines
            if(i == recvLines[tempRecvCount]){tempRecvCount++;}     //and recvLines
            continue;                                               //-next loop
        }

        memset(tempStr, 0, 5);

        if(i <= linesCount){
            if(i == sentLines[tempSentCount]){
                sMins           = atol(strncpy(tempStr, myString[i] + 20,2));   //
                sSeconds        = atol(strncpy(tempStr, myString[i] + 23,2));   //consider location is set, get substring and atol convert said substring into int
                sMilliseconds   = atol(strncpy(tempStr, myString[i] + 26,3));   //
                memset(tempStr, 0, 5);                                          //strncpy does not null terminate, empty tempStr instead
                sentTime = sMins*60000 + sSeconds*1000 + sMilliseconds;
                tempSentCount++;
                calculationTrigger++;                                           //only trigger the recalculation of delay once got both sent and recv time ie == 2
            }

            if(i == recvLines[tempRecvCount]){
                rMins           = atol(strncpy(tempStr, myString[i] + 20,2));   //
                rSeconds        = atol(strncpy(tempStr, myString[i] + 23,2));   //same as above
                rMilliseconds   = atol(strncpy(tempStr, myString[i] + 26,3));   //
                memset(tempStr, 0, 3);                                          //
                recvTime = rMins*60000 + rSeconds*1000 + rMilliseconds;
                tempRecvCount++;
                calculationTrigger++;
            }

            if(calculationTrigger == 2){   //
                if (maxDelay < (recvTime - sentTime)){          //maxDelay compares and rewrite
                    maxDelay = (recvTime - sentTime);           //
                }calculationTrigger = 0;   //

                delay[delayCounter] = recvTime - sentTime;      //log delay times for thoiGianTreTrungBinh()
                delayCounter++;
                sentTime = 0;
                recvTime = 0;
            }

        }
    }
    printf("\n\nDo tre lon nhat la: %d",maxDelay);
}

void soBanTinGuiLoi(int *sentLines, int *recvLines, int *errorLines, int *sentCount, int* recvCount,int *errorCount){//check if received lines have matching id with sents, count and store them in "errorLines" and "errorCount"
    int CheckTrigger = 0;
    char tempStr[5];
    int sID, rID;
    int tempErrorLinesMarked[2];

    int tempSentCount = 0, tempRecvCount = 0;

    char *sentReqidLocation = strstr(myString[sentLines[0]], "reqid");  //find distance to "reqid" in sent lines
    char *recvReqidLocation = strstr(myString[recvLines[0]], "reqid");  //find distance to "reqid" in recv lines
    
    int distanceToSentID = (int)(sentReqidLocation - myString[sentLines[0]])+9; //reqid": "xxxx", {reqid": "} = 9
    int distanceToRecvID = (int)(recvReqidLocation - myString[recvLines[0]])+9; //reqid": "xxxx", {reqid": "} = 9

    for (int i = 0; i < linesCount+1; i++){
        if(i <= linesCount){
            if(i == sentLines[tempRecvCount]){
                sID = atol(strncpy(tempStr, myString[i] + distanceToSentID,4)); //get sentID and convert to long
                CheckTrigger++;
                tempSentCount++;
                tempErrorLinesMarked[0] = i;
            }

            if(i == recvLines[tempRecvCount]){
                rID = atol(strncpy(tempStr, myString[i] + distanceToRecvID,4)); //get recvID and convert to long
                CheckTrigger++;
                tempRecvCount++;
                tempErrorLinesMarked[1] = i;
            }
        }

        if(CheckTrigger == 2){
            if (sID != rID){
                errorLines[*errorCount] = tempErrorLinesMarked[0];              //
                *errorCount += 1;                                               //mark each line as contain error
                errorLines[*errorCount] = tempErrorLinesMarked[1];              //
                *errorCount += 1;                                               //and increase errorCount
            }
            CheckTrigger = 0;                                                   //reset checkTrigger
        }
    }
    printf("\n\nSo ban tin loi: %d",(*errorCount)/2);
}

void thoiGianTreTrungBinh(){//get stored delay values and divide them to get average delay
    int totalDelay = 0;
    for(int i = 0; i < delayCounter; i++){
        totalDelay += delay[i];
    }
    printf("\n\nDe tre trung binh la: %d",totalDelay/delayCounter);
}

int main(){
    fileRead();                                                                             //read log to string

    int sentLines[100], recvLines[100], errorLines[100];
    int sentCount = 0, recvCount = 0, errorCount = 0;
    soBanTinGuiDi(&sentLines,&recvLines,&sentCount,&recvCount);                             //count both sent and received lines
    
    soBanTinTuThietBi(sentCount,&sentLines);                                                //check sent lines if log deviceID matches user input deviceID
    soBanTinGuiLoi(&sentLines,&recvLines,&errorLines,&sentCount,&recvCount,&errorCount);    //check if received lines have matching id with sents, count and store them in "errorLines" and "errorCount"

    thoiGianTreLonNhat(&sentLines,&recvLines,&errorLines,&sentCount,&recvCount,errorCount); //get largest delay of valid sents and receives
    thoiGianTreTrungBinh();                                                                 //get average delay of valid sents and receives
}