#include <math.h>
#include <stdio.h>

int distance(int x, int y, int r) {
    double distanceToPoint = sqrt(pow(x, 2) + pow(y, 2));
    double radius = r;

    distanceToPoint = r - distanceToPoint;

    if (distanceToPoint < 0) {
        return 0;
    }
    if (distanceToPoint == 0) {
        return 1;
    }
    return 2;
}

int main() {
    double x, y, r;
    printf("Enter the circle's radius: ");
    scanf("%lf", &r);
    printf("Enter the point's position (x, y): ");
    scanf("%lf %lf", &x, &y);

    switch (distance(x, y, r)) {
        case 0:
            printf("The point is in the circle");
            break;
        case 1:
            printf("The point is on the circle");
            break;
        case 2:
            printf("The point is out of the circle");
            break;
    }
}