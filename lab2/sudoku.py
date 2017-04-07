"""  TASK 1/LAB2  TNM096                         07-02-2017
     Run the various CSP solvers on selected Sudoku puzzles   """

from time import time
from datetime import timedelta
from aima.csp import Sudoku, AC3, backtracking_search, mrv, forward_checking, min_conflicts
from aima.search import depth_first_graph_search    
  
def secondsToStr(t):
    return str(timedelta(seconds=t))
def now():
    return secondsToStr(time())

print "\nSetting up the puzzle at\n     "+now()[12:20]
print "\nStart:" 


# 1. Set up the puzzle; select ONLY one of the following problem specifications
#puzzle = Sudoku('483921657967345821251876493548132976729564138136798245372689514814253769695417382')  #solved
#puzzle = Sudoku('..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3..')  #easy
#puzzle = Sudoku('4173698.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......')  #harder
puzzle = Sudoku('1....7.9..3..2...8..96..5....53..9...1..8...26....4...3......1..4......7..7...3..')   #hardest

puzzle.display(puzzle.infer_assignment())
start = time()


# 2. Solve the puzzle; select ONLY one of the following algorithms
#depth_first_graph_search(puzzle)
backtracking_search(puzzle)
#AC3(puzzle)
#min_conflicts(puzzle)


# 3. Print the results
print
if puzzle.goal_test(puzzle.infer_assignment()):
    print "Solution:"
    puzzle.display(puzzle.infer_assignment())
else:
    print "Failed - domains: "
    #print str(puzzle.curr_domains)

    
# 4. Print elapsed time
end = time()
elapsed = end-start
print "\nElapsed time ",  secondsToStr(elapsed)[0:15]

