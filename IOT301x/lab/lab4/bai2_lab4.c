#include <stdio.h>

int main(){
    int x;
    scanf("%i",&x);
    x |= (1<<4);
    printf("\n%i",x);
}