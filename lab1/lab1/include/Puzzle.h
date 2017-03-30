#ifndef PUZZLE_H
#define PUZZLE_H
#include <iostream>
#include <stdlib.h>

class Puzzle
{
    public:
        Puzzle();
        virtual ~Puzzle();

        void print();

        int nmbrMisplacedTiles();


    protected:

    private:
        int board[3][3];
};

#endif // PUZZLE_H
