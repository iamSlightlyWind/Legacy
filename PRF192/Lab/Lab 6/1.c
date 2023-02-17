#include <stdio.h>

void drawRectangle() {
    printf("\n ******");
    printf("\n *    *");
    printf("\n *    *");
    printf("\n *    *");
    printf("\n ******");
}

void drawTriangle() {
    printf("\n   *   ");
    printf("\n  *  * ");
    printf("\n *    *");
    printf("\n********");
}

void drawCircle() {
    printf("\n   **  ");
    printf("\n ");
    printf("\n *    *");
    printf("\n *    *");
    printf("\n ");
    printf("\n   **  ");
}

void drawInvertedV() {
    printf("\n   *   ");
    printf("\n  *  * ");
    printf("\n *    *");
    printf("\n*      *");
}

int main() {
    drawTriangle();
    drawRectangle();
    drawInvertedV();

    printf("\n\n");

    drawCircle();
    drawRectangle();
    drawInvertedV();

    printf("\n\n");

    drawCircle();
    drawTriangle();
    drawInvertedV();
}