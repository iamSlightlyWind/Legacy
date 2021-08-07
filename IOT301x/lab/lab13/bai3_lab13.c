#include <stdio.h>
#include <string.h>

#define FILENAME "temp.txt"
#define MAXSIZE 20000


int main(){
    FILE *fp = fopen(FILENAME,"r");

    if(fp == NULL){
        printf("file not found");
        return 0;
    }

    char temp[MAXSIZE];
    int linesCount = 0;
    char myChar;

    while(fgets(temp,MAXSIZE,fp) != NULL){
        printf("%s",temp);
    }

    fseek(fp, 1, SEEK_SET);
    while((myChar = fgetc(fp)) != EOF){
        if(myChar == '\n') linesCount++;
    }

    printf("\n\nlines counted: %d",++linesCount);
    
}