#include <stdio.h>

#define inputFile  "SUMDIV.INP"
#define outputFile "SUMDIV.OUT"
#define maxSize 5

void readFile(int *readInt){
    FILE *readStream = fopen(inputFile,"r");
    fscanf(readStream, "%d", &readInt[0]);
    fscanf(readStream, "%d", &readInt[1]);
    fclose(readStream);
}

void resultToFile(int result){
    FILE *writeStream = fopen(outputFile,"w");
    fprintf(writeStream,"%d",result);
    fclose(writeStream);
}

int main(){
    int myInt[2]; readFile(&myInt);
    int smaller, larger, divCounter = 0, sum = 0;
    int divLog[100];
    int check = 0;

    if(myInt[0] > myInt[1]){
        smaller = 1;
        larger = 0;
    }else{
        smaller = 0;
        larger = 1;
    }

    for(int i = myInt[smaller]; i <= myInt[larger]; i++){
        for(int x = 1; x <= i; x++){
            if(i % x == 0){
                check = 0;

                for(int z = 0; z <= divCounter; z++){
                    if(divLog[z] == x){
                        check++;
                    }
                }

                if(check == 0){
                    divLog[divCounter++] = x;
                }
            }
        }
    }

    for(int i = 0; i < divCounter; i++){
        sum+= divLog[i];
    }

    resultToFile(sum);
}