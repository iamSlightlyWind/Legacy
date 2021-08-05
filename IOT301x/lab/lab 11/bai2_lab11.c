#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool strCmp(const char *aa, const char *bb){
    for(int i = 0;*(aa+i) != '\0' && *(bb+i) != '\0';i++){
        if(*(aa+i) == *(bb+i)){
            continue;
        }else return false;
    }
    
    return true;
}

int main(){
    char a[100] = "imagine doing thid", b[100] = "imagine doing this";
    printf("%d", strCmp(a,b));
}