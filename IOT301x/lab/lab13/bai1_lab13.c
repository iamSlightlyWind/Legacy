#include <stdio.h>

int main(){
    FILE *fp = fopen("newFile.txt","w");
    char a[5000];
    scanf("%s",a);
    fputs(a,fp);
    fclose(fp);
    fp = NULL;
}