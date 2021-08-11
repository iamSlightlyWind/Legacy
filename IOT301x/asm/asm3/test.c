//void findShortestPath(int x, int y){
//    Queue myQueue;
//    initQueue(&myQueue);
//
//    matrix[0][0].visited = true;
//    enQueue(&myQueue, matrix[0][0]);
//    bool found = false;
//
//    point_t pp[4];
//    int count;
//
//    while((isEmpty(myQueue) == false) && (found == false)){
//        point_t p = deQueue(&myQueue);
//        findSurroundingPoint(p, pp, &count);
//        for(int i = 0; i < count; i++){
//            pp[i].visited = true;
//            pp[i].prev = &p;
//            if(pp[i].column == y && pp[i].row == x){
//                found = true;
//                break;
//            }
//            else {
//                enQueue(&myQueue,pp[i]);
//            }
//        }
//    }
//    if (found == true) {
//        printf("found!");
//    }else{
//        printf("Không có đường đi từ O(0, 0) đến A(%d, %d)\n\n",x,y);
//    }
//}