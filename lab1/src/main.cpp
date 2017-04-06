#include "../include/Puzzle.h"
#include <vector>
int main(int argc, char*args[])
{

    int b1[9] = {1,2,3,
                 4,6,8,
                 7,5,0};

    int b2[9] = {0,1,2,
                 4,5,3,
                 7,8,6};

    Puzzle p1 = Puzzle(b2, 0);

    p1.aStarSolver(p1);

}
