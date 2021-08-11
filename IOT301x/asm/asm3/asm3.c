#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define MAX_COLUMN 9
#define MAX_ROW 9
#define MAX MAX_ROW*MAX_COLUMN

struct POINT{
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
    if (x >= 0 && x <= MAX_ROW && y >= 0 && y <= MAX_COLUMN){
        return true;
    }
    return false;
}

void findSurroundingPoint(int x, int y,point_t *pp[4], int* count){
    int tempCount = 0;
    static point_t surroundingPnt[4];
    if(checkCoordinate(x, y+1) == true && matrix[x][y+1].value == 1 && matrix[x][y+1].visited == false){                   
        pp[tempCount] = &matrix[x][y+1];
        tempCount++;
    }

    if(checkCoordinate(x+1, y) == true && matrix[x+1][y].value == 1 && matrix[x+1][y].visited == false){                  
        pp[tempCount] = &matrix[x+1][y];
        tempCount++;
    }

    if(checkCoordinate(x, y-1) == true && matrix[x][y-1].value == 1 && matrix[x][y-1].visited == false){  
        pp[tempCount] = &matrix[x][y-1];
        tempCount++;                                                    
    }

    if(checkCoordinate(x-1, y) == true && matrix[x-1][y].value == 1 && matrix[x-1][y].visited == false){                    
        pp[tempCount] = &matrix[x-1][y];
        tempCount++;
    }
    
    *count = tempCount;
}

void findShortestPath(int x, int y){
    Queue myQueue;
    initQueue(&myQueue);
    int count = 0;
    point_t *pp[4];
    point_t p;
    point_t destP;

    matrix[0][0].visited = true;
    enQueue(&myQueue,matrix[0][0]);
    bool found = false;
    while (isEmpty(myQueue) == false && found == false){
        p = deQueue(&myQueue);
        findSurroundingPoint(p.row,p.column,pp,&count);

        for(int i = 0; i < count; i++){
            pp[i]->visited = true;
            pp[i]->prev = &p;
            if (pp[i]->row == x && pp[i]->column == y){
                destP = *pp[i];
                found = true;
                break;
            }else{
                enQueue(&myQueue,*pp[i]);
            }
        
        }
    }

    if (found == true) {
        printf("\n\nfound!");
        printf("\n[%d][%d]\n\n",(destP.prev)->prev->row,(destP.prev)->prev->column);
    }else{
        printf("\n\nKhông có đường đi từ O(0, 0) đến A(%d, %d)",x,y);
    }
}

int main(){
    srandom(6);
    for(int i = 0; i < MAX_COLUMN; i++){
        for(int x = 0; x < MAX_ROW; x++){
            matrix[x][i].row = x;
            matrix[x][i].column = i;
            matrix[x][i].value = rand()%2;
            matrix[x][i].visited = false;

            printf(" %d ",matrix[x][i].value);

            if(x != MAX_ROW-1){printf("|");}
        }
        printf("\n");
        for(int z = 0; z < MAX_ROW; z++){
            printf("----");
        }
        printf("\n");
    }
    
    
    int destX = 3;
    int destY = 1;
    findShortestPath(destX,destY);
}