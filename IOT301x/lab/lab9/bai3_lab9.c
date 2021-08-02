#include <stdio.h>
#include <string.h>

int main(){
    char a[100], b[100];int matchCounter;
    scanf("%s %s",a,b);
    
    for (int i = 0; i < strlen(a); i++){
        if(a[i] == b[0]){
            for(int x = 0; x < strlen(b); x++){
                if(a[i+x] == b[x]){matchCounter+=1;}else{matchCounter = 0;}

                if(matchCounter == strlen(b)){
                    printf("the larger string contains the smaller one");
                    return 0;
                }
            }
        }
    }printf("the larger string does not contains the smaller one");
}