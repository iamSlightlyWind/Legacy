#include<stdio.h>

int main(){
    const int a = 10;
    // @ts-ignore
    int *aa = &a;
    *aa = 11;

    printf("a = %d\n", a);
}