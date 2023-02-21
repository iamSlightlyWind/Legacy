#include <stdio.h>

int greatestSum(int arr[], int size) {
    int offset = 2, skeleton[size + offset];
    for (int i = 0; i < size; i++) {
        skeleton[i] = arr[i];
    }

    int sum[2] = {0, 1};

    for (int count = 0; count < 2; count++) {
        for (int i = 1; i < size; i++) {
            if (skeleton[i] > skeleton[sum[count]]) {
                if (!count) {
                    if (i != sum[1]) {
                        sum[count] = i;
                    }
                } else if (i != sum[0]) {
                    sum[count] = i;
                }
            }
        }
    }

    return skeleton[sum[0]] + skeleton[sum[1]];
}

int main() {
    int sampleArr[] = {1, 0, 2, 9, 3, 8, 4, 7, 5, 6};
    printf("%d", greatestSum(sampleArr, sizeof(sampleArr) / sizeof(sampleArr[0])));
}
