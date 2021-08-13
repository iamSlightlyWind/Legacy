#include <stdio.h>

int main(){
    int x;
    scanf("%d",&x);
    x |= (1<<4);
    printf("\n%d",x);
}