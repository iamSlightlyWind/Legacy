#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#define inputFile "TRIACU.INP" 

int readFile(FILE *myReadStream){
    int mySetCount;
    fscanf(myReadStream, "%d", &mySetCount);
    printf("number of set(s): %d\n",mySetCount);
    return mySetCount;
}

int getSetSize(FILE *myReadStream){
    int temp;
    fscanf(myReadStream, "%d", &temp);
    return temp;
}

int cmpfunc(const void* a, const void* b){
   return(*(int*)a - *(int*)b);
}

int availSet[10][3];
int availCount = 0, errorCount = 0;

int main(){
    FILE *readStream = fopen(inputFile,"r");
    int setCount = readFile(readStream);
    int set[setCount][3];

    for(int i = 0; i < setCount; i++){
        set[i][2] = 0;
    }

    for(int i = 0; i < setCount; i++){
        set[i][0] = getSetSize(readStream);
        set[i][1] = getSetSize(readStream);
    }

    int temp[3];

    for(int i = 0; i < setCount; i++){
        for(int x = 0; (i+x) < setCount; x++){
            for(int z = 0; (i+z) < setCount; z++){
                if(set[i][0] == set[i+x][0] || set[i][0] == set[i+z][0] || set[i+x][0] == set[i+z][0] || set[i][2] == set[i][1] || set[i+x][2] == set[i+x][1] || set[i+z][2] == set[i+z][1]){
                    continue;
                }else{
                    temp[0] = set[i][0];
                    temp[1] = set[i+x][0];
                    temp[2] = set[i+z][0];

                    qsort(&temp,3,sizeof(int),cmpfunc);
                    if(pow(temp[0],2) + pow(temp[1],2) > pow(temp[2],2)){
                        errorCount = 0;
                        for(int y = 0; y < availCount; y++){
                            if(temp[0] == availSet[y][0]) errorCount++;
                            if(temp[1] == availSet[y][1]) errorCount++;
                            if(temp[2] == availSet[y][2]) errorCount++;
                        }

                        if(errorCount < 3){
                            printf("%d %d %d",temp[0],temp[1],temp[2]);
                            printf("\n");

                            availSet[availCount][0]   = temp[0];
                            availSet[availCount][1]   = temp[1];
                            availSet[availCount++][2] = temp[2];   
                        }
                    }
                }
            }
        }
    }
}