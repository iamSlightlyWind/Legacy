#include <stdio.h>

int main(){
    float a,b;
    printf("enter first and second numbers:\n"); scanf("%f %f",&a,&b);
    if(a != (int)a || b!= (int)b){
        printf("\nnot int(s)"); return 0;
    }else if(a != b){
        printf("\nnot matched"); return 0;
    }else printf("\nmatched");
}