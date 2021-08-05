#include <stdio.h>

int fact(int *num){
    int fact= 1;
    for (int i = 1; i <= *num; i++) fact *= i;
    return fact;
}

int main(){
    int vFact, num;
    scanf("%d",&num);
    vFact = fact(&num);
    printf("%d",vFact);
}