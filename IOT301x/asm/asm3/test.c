#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define MAX_COLUMN 9
#define MAX_ROW 9
#define MAX MAX_ROW*MAX_COLUMN

struct POINT{
    int row;
    int column;
    int value;
    bool visited;
    struct POINT* prev;
};typedef struct POINT point_t;

void lol(point_t *c[2]){
    point_t a,b;
    a.value = 5;
    b.value = 10;

    c[0] = &a;
    c[1] = &b;
}

int main(){
    point_t *c[2];
    lol(c);
    
    //printf("%d %d",c[0]->value,c[1]->value);
}
