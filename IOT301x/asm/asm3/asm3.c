#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

struct POINT {
    int row;
    int column;
    int value;
    bool visited;
    struct POINT* prev;
};

typedef struct POINT point_t;

const int MAX_ROW = 9;
const int MAX_COLUMN  = 9;
point_t matrix[MAX_ROW][MAX_COLUMN];

int main(){
}