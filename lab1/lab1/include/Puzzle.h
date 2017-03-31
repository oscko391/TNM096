#ifndef PUZZLE_H
#define PUZZLE_H
#include <iostream>
#include <stdlib.h>

class Puzzle
{
public:
    Puzzle();
    Puzzle(int (&newBoard)[3][3]);
    virtual ~Puzzle();

    void print();

    int nmbrMisplacedTiles();
    int ManhattDist();

    void aStarSolver(Puzzle p);


protected:

private:
    int board[3][3];
};

class CompareH1
{
public:
    bool operator()(Puzzle &p1, Puzzle &p2)
    {
        return true;
    }
};

#endif // PUZZLE_H
