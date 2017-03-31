
#include "../include/Puzzle.h"

int main(int argc, char*args[])
{
    Puzzle p = Puzzle();
    p.print();

    int h1 = p.nmbrMisplacedTiles();
    int h2 = p.ManhattDist();

    std::cout << "h1 = " << h1 << std::endl;
    std::cout << "h2 = " << h2 << std::endl;

    p.aStarSolver(p);
}
