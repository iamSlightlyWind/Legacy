#include <stdio.h>
#include <string.h>

int main(){
    char temp[2][2000];

    printf("nhap file can doi ten: ");  scanf("%s",temp[0]);
    printf("nhap ten file moi: ");      scanf("%s",temp[1]);
    rename(temp[0],temp[1]);

    printf("nhap ten file can xoa: "); scanf("%s", temp[0]);
    remove(temp[0]);
}