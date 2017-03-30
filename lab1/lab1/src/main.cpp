
#include "../include/Puzzle.h"

int main(int argc, char*args[]){
    Puzzle p = Puzzle();
    p.print();

    int h1 = p.nmbrMisplacedTiles();

    std::cout << "h1 = " << h1 << std::endl;
}
