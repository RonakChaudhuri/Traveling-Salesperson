PROJECT TITLE: Traveling Salesperson Problem
PURPOSE OF PROJECT: Implement two greedy heuristics in the attempt to find
an optimal solution to the Traveling Salesperson Problem
DATE:11/18/19
AUTHOR:Aidan Moy, Ronak Chaudhuri
ALGORITHM DESCRIPTIONS: 
    insertNearest(): In the insert nearest method, we followed the nearest neighbour 
    heuristic. We start at one point, and find the nearest point from that starting point,
    and then contiunuosly checking the points that are the closest until all points are 
    checked where it will then go back to the original point. We do this in our algorithm 
    as we check to see where the smallest distance is from point we are adding using a 
    while loop to iterate through, then using and if statement to find the smallest 
    distance, then setting the smallest variable equal to the smallest distance, then 
    recording the postition of where the smallest distance is, using a variable we 
    created named asize to do so. We as well checked to see if it was the starting point, 
    so it didn't run the algorithm at the very start and to update our rear only when it is
    not the first point.
    
    insertSmallest(): In the insert smallest method, we followed the smallest increase 
    heuristic. We had to add the point to the tour where it resulted in least possible
    increase in the tour length. To do this, in our algorithm we calculated every possible
    increase from a point to figure out which is the smallest. To do this we calculated the 
    distance from a point(say point A) to the point we are adding(point P) to the next
    point(point B) and subtracted the original distance(point A to point B) and then found
    out which resulted in the smallest value and recorded the position in which we had to insert
    it in the list.
    
    
    
    
    