#include <stdio.h>

int main(){
    int a; scanf("%d",&a);

    if(a<0){printf("thoi tiet lanh cong");}
    if(a>=0 && a <10) {printf("thoi tiet rat lanh");}
    if(a>=10 && a <20){printf("thoi tiet lanh");}
    if(a>=20 && a <30){printf("thoi tiet tuyet voi");}
    if(a>=30 && a <40){printf("thoi tiet nong");}
    if(a>=40){printf("thoi tiet rat nong");}
}