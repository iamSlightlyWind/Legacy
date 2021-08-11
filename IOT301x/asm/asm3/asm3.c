#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define MAX_COLUMN 9
#define MAX_ROW 9
#define MAX MAX_ROW*MAX_COLUMN

struct POINT{//struct of every point on matrix board
    int row;
    int column;
    int value;
    bool visited;
    struct POINT* prev;
};typedef struct POINT point_t;
point_t matrix[MAX_ROW][MAX_COLUMN];

typedef struct{
        point_t data[MAX];
        int front;
        int rear;
}Queue;

void initQueue(Queue *q){
    q->front = 0;
    q->rear = -1;
}

int isEmpty(Queue q){
    return (q.rear < q.front);
}

int isFull(Queue q){
    if(q.rear == MAX - 1) {
    return 1;
    }
    else return 0;
}

void enQueue(Queue *q, point_t x) {
    if (!isFull(*q)) {
        if(q->rear == MAX - 1) {
            for(int i = q->front; i <= q->rear; i++){
                q->data[i - q->front] = q->data[i];
            }
            q->rear = MAX - q->front;
            q->front = 0;
        }
        q->rear = q->rear + 1;
        q->data[q->rear] = x;
    }
}

point_t deQueue(Queue *q){
    point_t d;   
    if (!isEmpty(*q)) {
       d = q->data[q->front];
       q->front = q->front +1;
    }
    if (q->front > q->rear) {
        initQueue(q);
    };
    return d;
}

bool checkCoordinate(int x, int y) {
    bool ret = false;
    if ((0 <= x < MAX_COLUMN ) && (0 <= y < MAX_ROW)) {
        ret = true;
    }
    return ret;
}

void findSurroundingPoint(int x, int y, point_t surroundingPnt[], int* count){
    int tempCnt = 0;//posible directions
    
    if (checkCoordinate(x, y+1) == true && matrix[x][y+1].value == 1 && matrix[x][y+1].visited == false){                   
        surroundingPnt[tempCnt] = matrix[x][y+1];
        tempCnt++;
        printf("\nsuiteable: %d, %d",x,y+1);
    }
    if (checkCoordinate(x+1, y) == true && matrix[x+1][y].value == 1 && matrix[x+1][y].visited == false){                  
        surroundingPnt[tempCnt] = matrix[x+1][y];
        tempCnt++;
        printf("\nsuiteable: %d, %d",x+1,y);
    }
    if (checkCoordinate(x, y-1) == true && matrix[x][y-1].value == 1 && matrix[x][y-1].visited == false){
        surroundingPnt[tempCnt] = matrix[x][y-1];                     
        tempCnt++;                                                    
        printf("\nsuiteable: %d, %d",x,y-1);
    }
    if (checkCoordinate(x-1, y) == true && matrix[x-1][y].value == 1 && matrix[x-1][y].visited == false){                    
        surroundingPnt[tempCnt] = matrix[x-1][y];
        tempCnt++;
        printf("\nsuiteable: %d, %d",x-1,y);
    }

    *count = tempCnt;
    //printf("\n\ncount: %d\n\n",tempCnt);
}

void findShortestPath(int x, int y){
    Queue myQueue;
    initQueue(&myQueue);
    point_t pp[4];
    int count = 0;

    matrix[0][0].visited = true;
    enQueue(&myQueue,matrix[0][0]);
    bool found = false;

    while (isEmpty(myQueue) == false && found == false) {
        printf("\n\nnext in queue");
        point_t p = deQueue(&myQueue);
        findSurroundingPoint(p.row,p.column,pp,&count);
        for(int i = 0; i < 4; i++){
            if(pp[i].visited == false){
                pp[i].visited = true;
                pp[i].prev = &p;
                if (pp[i].row == x && pp[i].column == y){
                    found = true;
                    break;
                }else{
                    enQueue(&myQueue,pp[i]);
                }
            }
        }
    }
    if (found == true) {
        printf("found!");
        //Sử dụng con trỏ prev trong mỗi nút để in lần lượt tọa độ các nút đã đi qua từ A(x, y) đến O(0, 0);
    }
    else {
        printf("Không có đường đi từ O(0, 0) đến A(%d, %d)",x,y);
    }
}

int main(){
    srand(10);
    int tmpc = 0;

    for(int i = 0; i < MAX_COLUMN; i++){
        for(int x = 0; x < MAX_ROW;x++){
            matrix[x][i].row = x;
            matrix[x][i].column = i;
            matrix[x][i].value = rand()%10;
            matrix[x][i].visited = false;

            printf(" %d ",matrix[i][x].value);
            if(x != MAX_COLUMN-1) printf("|");
        }
        printf("\n-----------------------------------\n");
    }

    //int destX = 1;
    //int destY = 0;
    //findShortestPath(destX,destY);
}