#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define MAX_COLUMN 9
#define MAX_ROW 9
#define MAX MAX_ROW*MAX_COLUMN

struct POINT{                               //
    int row;                                //
    int column;                             //struct consist of basic attributes of a point
    int value;                              //
    bool visited;                           //
    struct POINT* prev;                     //
};typedef struct POINT point_t;           //compiler refuse to compile "typedef struct POINT{}point_t;"

point_t matrix[MAX_ROW][MAX_COLUMN];        //matrix of MAX_ROW*MAX_COLUMN

typedef struct{                             //
        point_t data[MAX];                  //basic struct of Queue
        int front;                          //
        int rear;                           //
}Queue;

void initQueue(Queue *q){                   //
    q->front = 0;                           //function initialize a proper queue
    q->rear = -1;                           //
}

int isEmpty(Queue q){                       //function returns 1(true) if queue is empty
    return (q.rear < q.front);              //or 0(false) if not empty
}

int isFull(Queue q){                        //
    if(q.rear == MAX - 1) {                 //
    return 1;                               //function returns 1(true) if queue is full
    }                                       //
    else return 0;                          //or 0(false) if not full
}

void enQueue(Queue *q, point_t x){//put the point in rear of queue
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

point_t deQueue(Queue *q){//return the first point in queue
    point_t d;   
    if (!isEmpty(*q)) {             //if queue is not empty
       d = q->data[q->front];       //get d = new front in queue
       q->front = q->front +1;      //
    }                               
    if (q->front > q->rear) {           //if queue is empty
        initQueue(q);                   //init queue again
    };                                  //
    return d;//then return d
}

bool checkCoordinate(int x, int y){                             //
    if (x >= 0 && x < MAX_ROW && y >= 0 && y < MAX_COLUMN){     //check if point exist on matrix
        return true;                                            //
    }                                                           //
    return false;                                               
}

void findSurroundingPoint(int x, int y,point_t *pp[4], int* count){
    int tempCount = 0;
    point_t surroundingPnt[4];
    if(checkCoordinate(x, y+1) == true && matrix[x][y+1].value == 1 && matrix[x][y+1].visited == false){    //check if(point exist && point can be moved to (==1) && if point hasn't been visited yet)
        pp[tempCount] = &matrix[x][y+1];                                                                    //pointCanBeMovedTo[counter++] = checking point
        tempCount++;                                                                                        //same for below
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
    
    *count = tempCount;//how many surrounding points can be moved to
}

int findShortestPath(int x, int y){//find shortest path from [0][0] to [x][y]
    Queue myQueue;initQueue(&myQueue);                      //initialize myQueue

    int count = 0, distanceCount = 0;

    point_t *pp[4];                                         //how many surrounding points can be moved to
    point_t p;                                              //current point
    point_t *current;                                       //current point used to track back to starting point

    matrix[0][0].visited = true;                            //starting point marks as visited

    enQueue(&myQueue,matrix[0][0]);                         //add matrix[0][0] to queue 
    bool found = false;
    while (isEmpty(myQueue) == false && found == false){
        p = deQueue(&myQueue);
        findSurroundingPoint(p.row,p.column,pp,&count);     //find surrounding points of p and store in pp[4], number of available points stores in count

        for(int i = 0; i < count; i++){
            pp[i]->visited = true;                          //mark current point moved to as "visited"
            pp[i]->prev = &matrix[p.row][p.column];         //merge prev point
            current = &matrix[p.row][p.column];             //merge current point

            if (pp[i]->row == x && pp[i]->column == y){
                found = true;                               
                current = pp[i];                            //store destination pointer to track back to starting point
                break;
            }else{
                enQueue(&myQueue,*pp[i]);                   //if not found, add pp[i] to myQueue, run queue again
            }
        }
    }

    if (found == true) {
        printf("\n\nQuang duong ngan nhat di tu [0][0] den [%d][%d]",current->row,current->column);
        for(;;){                                                                
            if(current->column != 0 || current->row !=0){                       //if current point is not starting point
                distanceCount++;                                                //
                current = current->prev;                                        //keep getting prev point's row and column
                printf("\nDiem truoc do: [%d][%d]",current->row,current->column);        //and print route tracked back to starting point
            }else break;
        }
    }else{
        printf("\n\nKhông có đường đi từ O(0, 0) đến A(%d, %d)",x,y);
        return -1;                                                              //return -1 if no route is found
    }                                                                           //
    return distanceCount;                                                       //return route distance if (shortest) path is found
}

int main(){
    srandom(6);
    for(int i = 0; i < MAX_COLUMN; i++){                                        //
        for(int x = 0; x < MAX_ROW; x++){                                       //
            matrix[x][i].row = x;                                               //create matrix with random value using seed
            matrix[x][i].column = i;                                            //
            matrix[x][i].value = rand()%2;                                      //
            matrix[x][i].visited = false;                                       //
        }                                                                       
    }

    //int customMatrix[9][9] ={
    //    {1, 1, 1, 1, 1, 1, 1, 1, 1},
    //    {1, 0, 0, 0, 0, 0, 0, 0, 1},
    //    {1, 1, 0, 0, 0, 0, 0, 1, 1},
    //    {0, 1, 0, 0, 0, 0, 0, 1, 0},
    //    {0, 1, 0, 0, 0, 0, 0, 1, 1},
    //    {1, 1, 0, 0, 0, 0, 0, 0, 1},
    //    {1, 0, 0, 0, 0, 0, 0, 1, 1},
    //    {1, 0, 0, 0, 0, 0, 1, 1, 0},
    //    {1, 1, 1, 1, 1, 1, 1, 0, 0}
    //};
//custom matrix used for testing, could also be used in assignment review, keeping as commented.
    //for(int i = 0; i < 9; i++){
    //    for(int x = 0; x < 9; x++){
    //        matrix[i][x].value = customMatrix[x][i];
    //    }
    //}

    for(int i = 0; i < MAX_COLUMN; i++){
        for(int x = 0; x < MAX_ROW; x++){
            printf(" %d ",matrix[x][i].value);
            if(x != MAX_ROW-1){printf("|");}
        }
        printf("\n");
        for(int z = 0; z < MAX_ROW; z++){
            printf("----");
        }
        printf("\n");
    }
    
    int destX = 4;                                      //destination.x     no attributes needed, not using point_t
    int destY = 4;                                      //destination.y     
    int distanceCount = findShortestPath(destX,destY);
    
    switch(distanceCount){
        case -1: return 0;
        default: printf("\nKhoang canh ngan nhat tu [0][0] den [%d][%d] la %d\n\n",destX,destY,distanceCount);break;
    }
}