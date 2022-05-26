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
    while(fgets(temp,MAXSIZE,fp) != NULL){
        printf("%s",temp);
    }
    
}