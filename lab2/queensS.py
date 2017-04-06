
#  Solve the nQueens problem by using depth-first search

from time import time
from datetime import timedelta
from aima.search import depth_first_tree_search, NQueensProblem, Problem

def secondsToStr(t):
    return str(timedelta(seconds=t))

def now():
    return secondsToStr(time())
    

# 1. Set up the problem and set the starting time
n = 30
print "\nStarting at at  "+now()[12:20]
print "problem with n =",n
start = time()

# 2. Solve the NQueens problem with depth-first search
solution = depth_first_tree_search(NQueensProblem(n))
sol = str(solution)
print "Solution: ", sol[5:len(sol)-1]


# 3. Print time elapsed
end = time()
elapsed = end-start
print "\nElapsed time ",  secondsToStr(elapsed)[0:15]
