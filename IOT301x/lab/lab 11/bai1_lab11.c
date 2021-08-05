#include <stdio.h>

int main(){
    int a[100] = {1,2,3,4,5,6};

    int arrLength = 0, temp = 0;
    while(*(a+temp) != '\0'){
        arrLength++;
        temp++;
    }

    int *aa = &a;
    aa += arrLength - 1;
    for(int i = 0; i < arrLength; i++){
        printf("%d\n",*(aa - i));
    }
}