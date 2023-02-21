#include <stdio.h>

int main() {
    int sampleArr[][4] = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}};

    int r = 3;
    int st = 1, nd = 1;

    for (int i = 0,x = r; i <= r; i++,x--) {
        st *= sampleArr[i][i];
        nd *= sampleArr[i][x];
    }
    return 0;
}