#include <stdbool.h>

int main(){
    int a[100] = {2,3,4,23,143,12,32,345,1235,123654,123,123123};
    const int *aa = a;
    
    int arrLength = 0;
    for(int i = 0;*(a+i) != '\0';i++){
        arrLength++;
    }

    int largest = *aa;
    for(int i = 0; i < arrLength;i++){
        if(*(aa + i) > largest) largest = *(aa + i);
    }

    printf("%d",largest);
}