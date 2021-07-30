#include  <stdio.h>


//Main function
int main(){
    int pCount=0,aCount=0;
    int primeN[50];

    for(int i = 0;i <= 100;i++){
        for(int x = 1;x <= i;x++){
            if(i%x==0) pCount++;
        }
        if(pCount == 2) primeN[aCount++] = i;;
    pCount=0;
    }

    for(int i = 0; i < aCount; i++){
        printf("%d\n",primeN[i]);
    }
}