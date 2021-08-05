#include <stdio.h>

int main(){
    int a[100] = {2,3,4,23,143,12,32,345,1235,123654,123,123123};
    int arrLength = 0, temp = 0;
    for(int i = 0;*(a+i) != '\0';i++){
        arrLength++;
    }

    int *aa = &a, *pS = &temp;
    *pS = 1235;

    for(int i = 0; i < arrLength; i++){
        if(*pS == *(aa+i)){printf("found!"); return 0;}
    }
    printf("not found!");
}