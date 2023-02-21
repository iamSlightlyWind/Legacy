#include <stdio.h>

int main() {
    int sampleArr[][4] = {{9, 2, 1, 4}, {2, 1, 5, 7}};
    int min = sampleArr[0][0];
    for (int i = 0; i < 2; i++) {
        for (int x = 0; x < 4; x++) {
            if (sampleArr[i][x] < min) {
                min = sampleArr[i][x];
            }
        }
    }
    printf("%d", min);
}
