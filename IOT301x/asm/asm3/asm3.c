#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

struct POINT{//struct of every point on matrix board
    int row;
    int column;
    int value;
    bool visited;
    struct POINT* prev;
};

typede   struct{
        point_t data[MAX];  // lưu trữ các nút
        int front;          // chỉ số đầu của hàng đợi
        int rear;         // chỉ số cuối của hàng đợi
}Queue;

typedef struct POINT point_t;

void initQueue(Queue *q){
    q->front = 0;
    q->rear = -1;
}

int isEmpty(Queue q){
    return(q.rear < q.front);
}

int isFull(Queue q){
    if(q.rear == MAX - 1){
        return 1;
    }
        else return 0;
}

void enQueue(Queue *q, point_t x){
    if (!isFull(*q)){
        if(q->rear == MAX - 1){
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
    if (!isEmpty(*q)){
        d = q->data[q->front];
        q->front = q->front +1;
    }

    if (q->front > q->rear){
        initQueue(q);
    };

    return d;
}

bool checkCoordinate(int x, int y){
    bool ret = false;
    if ((0 <= x < MAX_ROW ) && (0 <= y < MAX_COLUMN)) {
        ret = true;
    }
    return ret;
}

void findSurroundingPoint(int x, int y, point_t surroundingPnt[4], int* count){
    int tempCnt = 0;

    if ((checkCoordinate(x, y+1) == true) && (matrix[x, y+1].value == 1)){
        surroundingPnt[tempCnt ] = matrix[x, y+1];
        tempCnt++;
    }
    
    if ((checkCoordinate(x+1, y) == true) && (matrix[x+1, y].value == 1)) {                  
        surroundingPnt[tempCnt ] = matrix[x+1, y];
        tempCnt++;
    }

    if ((checkCoordinate(x, y-1) == true) && (matrix[x, y-1].value == 1)) {
        surroundingPnt[tempCnt ] = matrix[x, y-1];
        tempCnt++;
    }

    if ((checkCoordinate(x-1, y) == true) && (matrix[x-1, y].value == 1)) {                    
        surroundingPnt[tempCnt ] = matrix[x-1, y];
        tempCnt++;
    }
    *count = tempCnt;

}

const int MAX_ROW = 9;
const int MAX_COLUMN = 9;
point_t matrix[MAX_ROW][MAX_COLUMN];

int main(){
}