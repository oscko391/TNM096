#include "../include/Puzzle.h"
#include <ctime>
#include <math.h>
#include <vector>
#include <queue>
#include <algorithm>
#include <random>       // std::default_random_engine
#include <chrono>       // std::chrono::system_clock
#include <array>        // std::array

Puzzle::Puzzle()
{
    //fill board with distinct random values
    //let zero be the empty tile
    //srand(time(0));

    std::array<int,9> values {0,1,2,3,4,5,6,7,8};

    // obtain a time-based seed:
    unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();

    shuffle (values.begin(), values.end(), std::default_random_engine(seed));

    for (int i = 0; i < 9; i++)
    {
        board[i] = values[i];
    }

}

Puzzle::Puzzle(int newBoard[9], int _gScore)
{

    gScore = _gScore;
    //create new board with values from newBoard
    for (int i = 0; i < 9; i++){
        if(newBoard[i] == 0){
            zeroPos = i;
        }

        board[i] = newBoard[i];

    }

}

Puzzle::~Puzzle()
{
    //dtor
}

//prints values in the board
void Puzzle::print()
{
    for(int i=0; i<3; i++){
        for(int j=0; j<3; j++){
            std::cout << board[i*3+j];
        }
        std::cout << "\n";
    }
}

// computes heuristic h1, checks how many tiles are correctly placed
int Puzzle::nmbrMisplacedTiles()
{
    int counter = 0;
    for(int i=0; i<9; i++){
        int tileValue = board[i];
        if(tileValue-1 != i && tileValue != 0)
            counter++;
    }

    return counter;
}



// computes heuristic h2, calculates manhattan distance for all tiles
int Puzzle::ManhattDist()
{
    int distance = 0;
    for(int i=0; i<9; i++){
        // Ignore 0
        if(board[i] != 0){
            int thisRow = getRow(i);
            int thisCol = getCol(i);
            int correctCol = ((board[i]-1) % 3);
            int correctRow = floor((board[i]-1) / 3);
            int manHatDist = abs(thisRow - correctRow) + abs(thisCol - correctCol);

            distance += manHatDist;
        }

    }

    return distance;
}


int* Puzzle::getBoard(){
    return board;
}


void Puzzle::aStarSolver(Puzzle p)
{

    std::priority_queue<Puzzle, std::vector<Puzzle>, CompareH1> closedSet;

    closedSet.push(p);

    Puzzle temp = closedSet.top();

    temp.print();

}
