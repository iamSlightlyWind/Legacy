#include <stdio.h>
#include <string.h>

char splChars[]= " !\"#$%%&'()*+,-./:;<=>?@[\\]^_`{|}~";
char myString[100];

void stringCutDown(int i){
    for(;i < strlen(myString);i++){
        myString[i] = myString[i+1]; 
    }
}

int main(){
    printf("special chars:%s\nyour string: ",splChars);
    fgets(myString,100,stdin);
    
    for(int i = 0; i < strlen(myString); i++){
        for(int x = 0; x < strlen(splChars); x++){
            if(myString[i] == splChars[x]){
                stringCutDown(i);
                i--;
            }
        }
    }

    printf("\nfiltered string: %s",myString);
}