# Assignment 1
This project has four algorithms:
MergeSort
QuickSort
Deterministic Select
Closest Pair
The program tests the algorithms and measures time recursion depth and recursive calls

## MergeSort
MergeSort splits an array into two parts
Then it sorts the parts and merges them
For small parts it uses Insertion Sort
T(n) = 2T(n/2) + O(n)
Master Theorem gives O(n log n)
Time O(n log n)
Space O(n)

## QuickSort
QuickSort uses a random pivot
It puts smaller values on one side and bigger values on the other side
The smaller part uses recursion
The bigger part uses a loop
Average time O(n log n)
Worst time O(n^2)
Space O(log n)
This helps to keep recursion depth smaller

## Deterministic Select
This algorithm finds the k smallest element
It uses groups of 5 and Median of Medians
T(n) <= T(n/5) + T(7n/10) + O(n)
Akra Bazzi idea shows O(n) time
Time O(n)
Space O(log n)

## Closest Pair
This algorithm finds two closest points
It splits the points into two parts
Then it checks points near the middle
T(n) = 2T(n/2) + O(n)
Master Theorem gives O(n log n)
Time O(n log n)
Space O(n)

## Results
I used sizes 100 1000 and 10000
I used Random Sorted Reverse and Duplicates
Time was measured with System.nanoTime()
Full results are in results/results.csv
Execution time
n | MergeSort | QuickSort | Select | ClosestPair
100 | 812600 | 710800 | 535400 | 8644300
1000 | 392500 | 162300 | 351600 | 5379200
10000 | 1616100 | 785200 | 2123300 | 30215800
Recursion depth
n | MergeSort | QuickSort | Select | ClosestPair
100 | 6 | 5 | 7 | 7
1000 | 9 | 8 | 8 | 10
10000 | 13 | 10 | 13 | 13
Different input types were also tested
Full results are in results/results.csv

## Plots
![Time](docs/plots/time_vs_n.png)
![Depth](docs/plots/recursion_depth_vs_n.png)

## Discussion
The results are close to the expected complexity
Input type can change the time
QuickSort uses recursion for the smaller part so recursion depth is smaller
Median of Medians removes many values every step so it gives O(n)
Closest Pair is faster than brute force for large input because it does not check every pair
JVM cache GC and computer load can change the time

## Reflection
I learned how divide and conquer algorithms work
The hardest parts were recursion partition and testing

## Screenshots
Program output
![Program](docs/screenshots/program_output.png)
Test results
![Tests](docs/screenshots/test_results.png)
Plots and results
![Results](docs/screenshots/plots_results.png)
