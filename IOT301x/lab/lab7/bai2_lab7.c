#include <stdio.h>

int main(){
    int a[] = {1,3,6,2,3,6,8,5};
    for(int i = sizeof(a)/sizeof(a[0])-1; i >= 0 ;i--){
        printf("%d\n",a[i]);
    }
}