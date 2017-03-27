#include "Puzzle.h"



Puzzle::Puzzle()
{
    //fill board with distinct random values
    //let zero be the empty tile
    for (int rows = 0; rows < sizeof(board)/sizeof(*board); rows++){
        for (int cols = 0; cols < sizeof(*board)/sizeof(*board[0]); cols++){
            while (1){
                bool alreadyPresent = false;
                int value = rand() % 9;

                //check for the value in the board
                for (int i = 0; i < rows; i++){
                    for (int j = 0; j < cols; j++){
                        int temp = board[i][j];
                        if (value == board[i][j]) {
                            alreadyPresent = true;
                            break;
                        }
                    }
                }
                //add value to board if not already present
                if (!alreadyPresent){
                    board[rows][cols] = value;
                    break; //breaks while loop
                }
            }
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
    for (int rows = 0; rows < sizeof(board)/sizeof(*board); rows++){
        for (int cols = 0; cols < sizeof(*board)/sizeof(*board[0]); cols++){
            std::cout << board[rows][cols];
        }
        std::cout<< "\n";
    }
}
