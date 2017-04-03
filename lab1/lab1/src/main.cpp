#include "../include/Puzzle.h"
#include <vector>
int main(int argc, char*args[])
{

    int b1[9] = {1, 2, 3, 4, 5, 6, 0, 7, 8 };

    Puzzle p1 = Puzzle(b1, 0);

    p1.aStarSolver(p1);

}
