#include<stdio.h>

int main(){
    const int a = 10;
    // @ts-ignore
    int *aa = &a;
    *aa = 11;

    printf("a = %d\n", a);
}

//basically whats asked but cant compile locally
//https://onlinegdb.com/G3xA0W8C_