#include <stdio.h>
#include <math.h>
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

void sort(int *toBeSorted){
    int temp;
    //for(int i = 0; i < 3; i++){
    //    for(int x = 0; x < 3; x++){
    //        if(*toBeSorted[x] > *toBeSorted[x+1]){
    //            temp = *toBeSorted[0];
    //            *toBeSorted[0] = *toBeSorted[1];
    //            *toBeSorted[1] = temp;
    //        }
    //    }
    //}

    printf("\n\n%d %d %d",toBeSorted[0],toBeSorted[1],toBeSorted[2]);
}

int availSet[1000000][3];

int main(){
    FILE *readStream = fopen(inputFile,"r");
    int setCount = readFile(readStream);
    int set[setCount][2];

    for(int i = 0; i < setCount; i++){
        set[i][0] = getSetSize(readStream);
        set[i][1] = getSetSize(readStream);
    }

    int temp[3];

    for(int i = 0; i < setCount; i++){
        for(int x = 1; x < setCount; x++){
            for(int z = 2; z < setCount; z++){
                if(set[i][0] == set[i+x][0] || set[i][0] == set[i+z][0] || set[i+x][0] == set[i+z][0]){
                    continue;
                }

                if(set[i][0] == 0 || set[i+x][0]==0 || set[i+z][0] == 0){
                    continue;
                }

                temp[0] = set[i][0];
                temp[1] = set[i+x][0];
                temp[2] = set[i+z][0];

                sort(&temp);

                if(temp[0]*temp[0] + temp[1]*temp[1] > temp[2]*temp[2]){
                    printf("\n%d + %d > %d",temp[0],temp[1],temp[2]);
                }
            }
        }
    }
}
yo yo yeah phong pham tre trau 