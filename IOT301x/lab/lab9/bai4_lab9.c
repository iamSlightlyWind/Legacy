#include <stdio.h>
#include <string.h>

int main(){
    char splChars[]= " !\"#$%%&'()*+,-./:;<=>?@[\\]^_`{|}~";
    char myString[100];

    printf("special chars:%s\nyour string: ",splChars);
    fgets(myString,100,stdin);
    
    for(int i = 0; i < strlen(myString); i++){
        for(int x = 0; x < strlen(splChars); x++){
            if(myString[i] == splChars[x]){
                for(int z = i;z < strlen(myString);z++){
                    myString[z] = myString[z+1]; 
                }
                i--;
            }
        }
    }

    printf("\nfiltered string: %s",myString);
}