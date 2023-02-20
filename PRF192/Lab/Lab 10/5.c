#include <stdio.h>

int main() {
    int sampleArr[] = {{1, 2, 3, 4}, {2, 3, 5, 7}};
    int min = sampleArr[0, 0];
    for (int i = 0; i < 3; i++) {
        for (int x = 0; x < 3; x++) {
            if (sampleArr[i, x] < min) {
                min = sampleArr[i, x];
            }
        }
    }
    printf("%d", min);
}
