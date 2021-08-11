#include <stdio.h>

void cb(int a[]){
    printf("%d\n",a[0]);
    printf("%d\n",a[1]);
    printf("%d\n",a[2]);
    printf("%d\n",a[3]);
}

int main(){
    int b[4] = {1,2,3,4};
    cb(b);
}