#include <stdio.h>

int main(){
    int x,y, *a = &x, *b = &y;
    scanf("%d %d",a,b);
    
    if(*a > *b){
        printf("%d > %d",*a,*b);
    }else if(*a < *b){
        printf("%d > %d",*b,*a);
    }else printf("%d = %d",*a,*b);
}