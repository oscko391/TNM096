
#include "C:/Users/oscar/skola/TNM096/TNM096/lab1/lab1/include/Puzzle.h"

int main(int argc, char*args[]){
    Puzzle p = Puzzle();
    p.print();

    int h1 = p.nmbrMisplacedTiles();

    std::cout << "h1 = " << h1 << std::endl;
}
