#include <stdio.h>

int main(){
    int x, myBit, bitNumber;
    printf("Choose a number: "); scanf("%d",&x);
    printf("Check bit number "); scanf("%d",&bitNumber);
    myBit = (x >> bitNumber) & 1;
    printf("Bit number %d is %d",bitNumber,myBit);
}