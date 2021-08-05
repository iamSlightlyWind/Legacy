#include <stdio.h>

int main(){
    int a[100] = {1,2,3,4,5,6};

    int arrLength = 0;
    for(int i = 0;*(a+i) != '\0';i++){
        arrLength++;
    }
    
    // @ts-ignore
    int *aa = &a;
    aa += arrLength - 1;
    for(int i = 0; i < arrLength; i++){
        printf("%d\n",*(aa - i));
    }
}