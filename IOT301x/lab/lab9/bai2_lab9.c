#include <stdio.h>
#include <string.h>

int main(){
    int i,j;
    int n = 2;
    char temp[50];
    char name[4][50];

    printf("enter 4 names:\n");
    scanf("%s %s %s %s",name[0],name[1],name[2],name[3]);

    for(int i = 0;i <= n; i++){
        for(int j = 0; j <= n-i; j++){
            if(strcmp(name[j],name[j+1]) > 0){
                printf
                strcpy(temp,name[j]);
                strcpy(name[j],name[j+1]);
                strcpy(name[j+1],temp);
            }
        }
    }

    printf("\n\n%s\n%s\n%s",name[0],name[1],name[2],name[3]);
}