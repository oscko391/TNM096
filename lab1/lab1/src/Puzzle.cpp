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
        if (values[i] == 0) zeroPos = i;
    }

}

Puzzle::Puzzle(int newBoard[9], int _gScore)
{

    gScore = _gScore;
    //create new board with values from newBoard
    for (int i = 0; i < 9; i++)
    {
        if(newBoard[i] == 0)
        {
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
    std::cout << "\n";
    for(int i=0; i<3; i++)
    {
        for(int j=0; j<3; j++)
        {
            std::cout << board[i*3+j];
        }
        std::cout << "\n";
    }
}

// computes heuristic h1, checks how many tiles are correctly placed
int Puzzle::nmbrMisplacedTiles() const
{
    int counter = 0;
    for(int i=0; i<9; i++)
    {
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
    for(int i=0; i<9; i++)
    {
        // Ignore 0
        if(board[i] != 0)
        {
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

bool Puzzle::operator<(const Puzzle &p)
{
    int f1 = this->gScore + this->nmbrMisplacedTiles();
    int f2 = p.gScore + p.nmbrMisplacedTiles();

    return f1 < f2;

}

int* Puzzle::getBoard()
{
    return board;
}

bool Puzzle::checkBoard()
{
    for(int i=0; i<8; i++)
    {
        if(board[i] != i+1)
            return false;
    }
    return true;
}



void Puzzle::swapZero( int newZeroPos )
{
    std::swap(board[zeroPos], board[newZeroPos]);
    zeroPos = newZeroPos;
}

void Puzzle::aStarSolver(Puzzle p)
{
    std::priority_queue<Puzzle, std::vector<Puzzle>, CompareH1> openSet;
    std::vector<int> moves;
    std::vector<Puzzle> closedSet;


    std::vector<int> possMoves;
    /*
        for(int i=0; i<possMoves.size(); i++){
            Puzzle temp = Puzzle(p.getBoard(), p.gScore+1);
            temp.swapZero(possMoves.at(i));
            temp.print();
        }*/

    openSet.push(p);
    make_heap(closedSet.begin(), closedSet.end());

    while (!openSet.empty())
    {
        Puzzle currPuzzle = openSet.top();
        openSet.pop();
        closedSet.push_back(currPuzzle);

        std::cout << "Currently ev puzzle: ";
        currPuzzle.print();
        std::cout << "Zero pos: " << currPuzzle.zeroPos << "\n";

        if (currPuzzle.checkBoard())
        {
            std::cout << "solved puzzle!" << std::endl;
            currPuzzle.print();
            break;
        }

        sort_heap(closedSet.begin(), closedSet.end());

        possMoves = currPuzzle.getMoves();

        std::cout << "Possible moves:";
        for(int i=0; i < possMoves.size(); i++)
        {

            Puzzle newPuzz = Puzzle(currPuzzle.getBoard(), currPuzzle.gScore+1);
            newPuzz.swapZero(possMoves[i]);

            newPuzz.print();

            bool isPuzzInSet = false;
            //check if board already evaluated
            for (int j = 0; j < closedSet.size(); j++)
            {
                if (newPuzz == closedSet[i])
                {
                    isPuzzInSet = true;

                }
            }
            if (!isPuzzInSet){
                openSet.push(newPuzz);
                push_heap(closedSet.begin(), closedSet.end());
            }
        }
    }
}

std::vector<int> Puzzle::getMoves()
{
    std::vector<int> possibleMoves;

    switch(zeroPos)
    {
    case 0:
        possibleMoves = { 1, 3 };
        break;
    case 1:
        possibleMoves = {0,2,4};
        break;
    case 2:
        possibleMoves = {1,5};
        break;
    case 3:
        possibleMoves = {0,4,6};
        break;
    case 4:
        possibleMoves = {1,3,5,7};
        break;
    case 5:
        possibleMoves = {2,4,8};
        break;
    case 6:
        possibleMoves = {3,7};
        break;
    case 7:
        possibleMoves = {4,6,8};
        break;
    case 8:
        possibleMoves = {5,7};
        break;

    default:
        break;
    }

    return possibleMoves;
}
