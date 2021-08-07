#include <stdio.h>
#include <string.h>

#define MAXSIZE 200000

//files needed: file1, file2, destFile

int main(){
    char temp1[MAXSIZE];
    char temp2[MAXSIZE];


    /////////////////////////////////////////
    FILE *fp = fopen("file1.txt","r");

    if(fp == NULL){
        printf("file1 not found");
        return 0;
    }else fgets(temp1,MAXSIZE,fp);
    /////////////////////////////////////////
    fclose(fp); fp = fopen("file2.txt","r");
    
    if(fp == NULL){
        printf("file2 not found");
        return 0;
    }else fgets(temp2,MAXSIZE,fp);
    /////////////////////////////////////////
    strcat(temp1,temp2);

    fclose(fp); fp = fopen("destFile.txt","w");

    fputs(temp1,fp);
    fclose(fp);
   
}