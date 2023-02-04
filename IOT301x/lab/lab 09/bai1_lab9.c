#include <stdio.h>
#include <string.h>

int main(){
    char str1[100] = "string";
    char str2[100] = "strinG";

    printf("%d\n", strlen(str1));

    char sStr[100];
    for (int i = 0; i < strlen(str1); i++){
        sStr[i] = str1[i];
    }
    strcat(sStr,str2);
    printf("merged string: %s\n",sStr);

    if(strcmp(str1,str2) == 0){
        printf("same string");
    }else print("not same string");
}