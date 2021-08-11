void findSurroundingPoint(int x, int y, point_t surroundingPnt[], int* count){
    int tempCnt = 0;//posible directions
    
    if (checkCoordinate(x, y+1) == true && matrix[x][y+1].value == 1/* && matrix[x][y+1].visited == false*/){                   
        surroundingPnt[tempCnt] = matrix[x][y+1];                              /*                                              */    
        tempCnt++;                                                                        /*                                              */        
    }                                                                                     /*                                              */            
    //below                                                                               /*                                              */                            
    if (checkCoordinate(x+1, y) == true && matrix[x+1][y].value == 1/* && matrix[x+1][y].visited == false*/){                  
        surroundingPnt[tempCnt] = matrix[x+1][y];                              /*                                              */    
        tempCnt++;                                                                        /*                                              */                            
    }                                                                                     /*                                              */        
    //left                                                                                /*                                              */                        
    if (checkCoordinate(x, y-1) == true && matrix[x][y-1].value == 1/* && matrix[x][y-1].visited == false*/){
        surroundingPnt[tempCnt] = matrix[x][y-1];                              /*                                              */            
        tempCnt++;                                                                        /*                                              */                                
    }                                                                                     /*                                              */                                
    //upper                                                                               /*                                              */                                    
    if (checkCoordinate(x-1, y) == true && matrix[x-1][y].value == 1/* && matrix[x-1][y].visited == false*/){                    
        surroundingPnt[tempCnt] = matrix[x-1][y];
        tempCnt++;
    }
    *count = tempCnt;
    //printf("\n\ncount: %d\n\n",tempCnt);
}