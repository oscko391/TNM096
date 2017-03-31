#include "../include/Puzzle.h"
#include <ctime>
#include <math.h>
#include <vector>
#include <queue>

Puzzle::Puzzle()
{
    //fill board with distinct random values
    //let zero be the empty tile
    srand(time(0));
    for (int rows = 0; rows < sizeof(board)/sizeof(*board); rows++)
    {
        for (int cols = 0; cols < sizeof(*board)/sizeof(*board[0]); cols++)
        {
            while (1)
            {
                bool alreadyPresent = false;
                int value = rand() % 9;

                //check for the value in the board
                for (int i = 0; i < sizeof(board)/sizeof(*board); i++)
                {
                    for (int j = 0; j < sizeof(*board)/sizeof(*board[0]); j++)
                    {
                        int temp = board[i][j];
                        if (value == board[i][j])
                        {
                            alreadyPresent = true;
                            break;
                        }
                    }
                }
                //add value to board if not already present
                if (!alreadyPresent)
                {
                    board[rows][cols] = value;
                    break; //breaks while loop
                }
            }
        }
    }
}

Puzzle::Puzzle(int (&newBoard)[3][3])
{
    //create new board with values from newBoard
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            board[i][j] = newBoard[i][j];
        }
    }
}

Puzzle::~Puzzle()
{
    //dtor
}

//prints values in the board
void Puzzle::print()
{
    for (int rows = 0; rows < sizeof(board)/sizeof(*board); rows++)
    {
        for (int cols = 0; cols < sizeof(*board)/sizeof(*board[0]); cols++)
        {
            std::cout << board[rows][cols];
        }
        std::cout<< "\n";
    }
}

// computes heuristic h1, checks how many tiles are correctly placed
int Puzzle::nmbrMisplacedTiles()
{
    int counter = 0;

    for (int rows = 0; rows < sizeof(board)/sizeof(*board); rows++)
    {
        for (int cols = 0; cols < sizeof(*board)/sizeof(*board[0]); cols++)
        {
            // the empty tile (0) should not be taken into account
            if ( board[rows][cols] != (3 * rows + cols + 1) &&
                    board[rows][cols] != 0)
            {
                counter++;
            }
        }
    }

    return counter;
}



// computes heuristic h2, calculates manhattan distance for all tiles
int Puzzle::ManhattDist()
{

    //ROw -> ceil( (this/3) -1 )
    int rowShouldBe;
    int colShouldBe;
    int distance = 0;

    for (int rows = 0; rows < sizeof(board)/sizeof(*board); rows++)
    {
        for (int cols = 0; cols < sizeof(*board)/sizeof(*board[0]); cols++)
        {
            int value= board[rows][cols];

            // Ignore the blank space (0)
            if(value != 0)
            {
                int thisSpaceShouldBe = (rows*3)+1;


                rowShouldBe = ceil(value/3.0-1);
                colShouldBe = value%3 - 1;

                // Special case for 3, 6
                if(value == 3 || value == 6) colShouldBe = 2;


                std::cout <<  value <<  ": Row " << rowShouldBe << " Col " << colShouldBe << std::endl;

                // case for the zero
            }
            else
            {
                rowShouldBe = 2;
                colShouldBe = 2;
                std::cout <<  value <<  ": Row " << rowShouldBe << " Col " << colShouldBe << std::endl;
            }

            // Calculate the manhattan distance for this slot
            int manHatDist = abs(rows - rowShouldBe) + abs(cols - colShouldBe);
            std::cout << "manhattan distance: " << manHatDist << std::endl;
            // add to total Manhattan distance
            distance += manHatDist;
        }
    }

    return distance;
}

void Puzzle::aStarSolver(Puzzle p)
{
    std::priority_queue<Puzzle, std::vector<Puzzle>, CompareH1> closedSet;

    closedSet.push(p);

    Puzzle temp = closedSet.top();

    temp.print();
}
