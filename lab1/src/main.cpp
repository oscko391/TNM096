#include "../include/Puzzle.h"
#include <vector>
int main(int argc, char*args[])
{

//    int b1[9] = {1,2,3,
//                 4,6,8,
//                 7,5,0};
//
//    int b2[9] = {0,1,2,
//                 4,5,3,
//                 7,8,6};
//
//    Puzzle p1 = Puzzle(b2, 0);


    Puzzle p = Puzzle();
    p.scrambleBoard(3);
    while( p.nmbrMisplacedTiles() == p.ManhattDist() ){
        p = Puzzle();
        p.scrambleBoard(5);
    }
     // Swapping zero n times
    std::cout << "Initial puzzle: \n";
    p.print();
    std::cout << "h1: " << p.nmbrMisplacedTiles() << "\nh2: " << p.ManhattDist() << std::endl;
    //p.aStarSolver(p);

}
