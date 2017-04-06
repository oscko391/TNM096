#ifndef PUZZLE_H
#define PUZZLE_H
#include <iostream>
#include <stdlib.h>
#include <array>
#include <math.h>
#include <vector>

class Puzzle
{
public:
    Puzzle();
    Puzzle(int newBoard[9], int _gScore);
    virtual ~Puzzle();

    void scrambleBoard(int noSwap);

    void print();

    int nmbrMisplacedTiles() const;
    int ManhattDist();

    void aStarSolver(Puzzle p);
    int gScore = 0;
    int zeroPos;
    int* copyBoard();
    void swapZero( int newZeroPos );
    bool checkBoard();

    bool operator<( Puzzle p);
    bool operator==( Puzzle p)
    {
        for (int i = 0; i < 9; i++)
        {
            if (board[i] != p.getBoard()[i])
            {
                return false;
            }
        }
        return true;
    }

    int* getBoard();

    int getCol(int index)
    {
        int col = index%3;

        return col;
    }
    int getRow(int index)
    {
        int row = floor(index/3);

        return row;
    }

    std::vector<int> getMoves();

protected:

private:
    int board[9];


};

class CompareH2
{
public:
    bool operator()(Puzzle &p1, Puzzle &p2)
    {
        int f1 = p1.gScore + p1.ManhattDist();
        int f2 = p2.gScore + p2.ManhattDist();

        return f1 > f2;
    }
};

#endif // PUZZLE_H
