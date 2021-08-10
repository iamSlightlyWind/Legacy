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
};

typedef struct POINT point_t;
point_t matrix[MAX_ROW][MAX_COLUMN];

typedef struct{
        point_t data[MAX];
        int front;
        int rear;
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
    int tempCnt = 0;  // khởi tạo số biến đếm số lượng các nút có thể đi được quanh 1 vị trí
    // kiểm tra xem vị trí điểm bên phải có đến được ko?
    if ((checkCoordinate(x, y+1) == true) && (matrix[x][y+1].value == 1)){                   
        surroundingPnt[tempCnt] = matrix[x][y+1];
        tempCnt++;
    }
    // kiểm tra xem vị trí điểm bên dưới có đến được ko?
    if ((checkCoordinate(x+1, y) == true) && (matrix[x+1][y].value == 1)){                  
        surroundingPnt[tempCnt] = matrix[x+1][y];
        tempCnt++;
    }
    // kiểm tra xem vị trí điểm bên trái có đến được ko?
    if ((checkCoordinate(x, y-1) == true) && (matrix[x][y-1].value == 1)){
        surroundingPnt[tempCnt] = matrix[x][y-1];
        tempCnt++;
    }
    // kiểm tra xem vị trí điểm bên trên có đến được ko?
    if ((checkCoordinate(x-1, y) == true) && (matrix[x-1][y].value == 1)){                    
        surroundingPnt[tempCnt] = matrix[x-1][y];
        tempCnt++;
    }
    *count = tempCnt;
}

void findShortestPath (int x, int y) {
    Khai báo 1 hàng đợi Queue queue;
    Gọi hàm initQueue() để khởi tạo hàng đợi đã khai báo;
    Đánh dấu nút matrix[0][0] là đã đi qua (matrix[0][0].visited = true);
    Cho nút matrix[0][0] vào hàng đợi bằng hàm enQueue();
    bool found = false;
    while ((hàng đợi chưa rỗng (isEmpty(queue) == false)) && (chưa tìm thấy nút A(x, y) trên đường đi(found == false))) {
        Gọi hàm deQueue() để lấy nút p là phần tử đầu của hàng đợi;
        Gọi hàm surroundingPnt() để tìm các nút xung quanh pp[] của nút p vừa lấy ra khỏi hàng đợi;
        Dùng vòng for duyệt lần lượt các nút chung quanh vừa được tìm thấy ở trên {
            Nếu là nút chưa được đi qua (visited = false) {
                Đánh dấu nút pp[i] là đã đi qua (pp[i].visited = true);
                Đánh dấu nút p là nút đi qua trước đó của nút pp[i] (pp[i].prev = &p);
                if (pp[i] chính là nút A(x, y) {
                    found = true;
                    break khỏi vòng lặp for;
                }
                else {
                    cho nút pp[i] vào hàng đợi bằng hàm enQueue();
                }
            }
        }
    }
    if (found == true) {
        Sử dụng con trỏ prev trong mỗi nút để in lần lượt tọa độ các nút đã đi qua từ A(x, y) đến O(0, 0);
    }else{
        Thông báo "Không có đường đi từ O(0, 0) đến A(x, y)";
    }
}

int main(){
    //initQueue();
    //srand(time(NULL));
    for(int i = 0; i < MAX_ROW; i++){
        for(int x = 0; x < MAX_COLUMN; x++){
            matrix[i][x].row = i;
            matrix[i][x].column = x;
            matrix[i][x].value = rand() % 2;
            matrix[i][x].visited = false;
            
            printf(" %d ",matrix[i][x].value);
            if(x == MAX_COLUMN-1){}else printf("|");
        }
        printf("\n-----------------------------------\n");
    }
}