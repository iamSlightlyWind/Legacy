#include <stdio.h>

int sumLast3(int arr[], int size) {
    int offset = 3, skeleton[size + offset];
    skeleton[0] = 0;
    skeleton[1] = 0;
    skeleton[2] = 0;
    for (int count = size + offset; count >= offset; count--) {
        skeleton[count] = arr[count - offset];
    }

    return skeleton[size + --offset] + skeleton[size + --offset] + skeleton[size + --offset];
}

int main() {
    int sampleArr[] = {1};
    printf("%d", sumLast3(sampleArr, sizeof(sampleArr) / sizeof(sampleArr[0])));
}
