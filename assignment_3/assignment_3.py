# import numpy as np
# import time
# from line_profiler import LineProfiler

def find_min_shops():
    in1 = str(input()).strip().split(" ")
    n = int(in1[0])
    t = int(in1[1])

    all_islands_array = [0]*(n+1)

    collector1 = [10000000, 0]

    checker = 1
    island_used_checker_outer = 1
    su = 1

    for i in range(1, n + 1):
        su = su | (1 << i)

    for i in range(t):
        arr = []
        in2 = str(input()).strip().split(" ")
        arr.append(int(in2[0]))
        arr.append(int(in2[1]))

        all_islands_array[arr[0]] = all_islands_array[arr[0]] | arr[1]
        all_islands_array[arr[1]] = all_islands_array[arr[1]] | arr[0]

    def backtracking_algorithm(i, islands_visited_checker, island_used_checker, num):
        if (su & islands_visited_checker) == su:
            if num < collector1[0]:
                collector1[0] = num
                collector1[1] = island_used_checker
            return

        if i > n or num >= n:
            return

        checker_copy = islands_visited_checker
        island_used_checker_copy = island_used_checker

        islands_visited_checker = islands_visited_checker | all_islands_array[i] | (1 << i)

        island_used_checker = island_used_checker | (1 << i)

        backtracking_algorithm(i + 1, islands_visited_checker, island_used_checker, num + 1)
        backtracking_algorithm(i + 1, checker_copy, island_used_checker_copy, num)

    backtracking_algorithm(1, checker, island_used_checker_outer, 0)

    someNum = collector1[1]
    tempArr = []
    for k in range(1, n + 1):
        if not someNum & (1 << k) == 0:
            tempArr.append(k)

    print(str(collector1[0]))
    print(str(tempArr).replace(", ", " ")[1:-1])


arr1 = [
    [1, 2],
    [1, 6],
    [1, 8],
    [2, 3],
    [2, 6],
    [3, 4],
    [3, 5],
    [4, 5],
    [4, 7],
    [5, 6],
    [6, 7],
    [6, 8]
]
arr2 = [
    [1, 2],
    [1, 4],
    [2, 4],
    [2, 3],
    [3, 5],
    [3, 6],
    [4, 5]
]
arr3 = [
    [1, 2],
    [1, 3],
    [3, 4]
]
arr4 = [
    [1, 2],
    [1, 5],
    [1, 4],
    [3, 6]
]
arr5 = [
    [1, 2],
    [1, 3],
    [1, 4],
    [1, 5],
    [1, 6],
    [6, 7]
]
arr6 = [
    [24, 30],
    [9, 16],
    [20, 18],
    [17, 19],
    [8, 22],
    [23, 22],
    [6, 17],
    [24, 11],
    [10, 12],
    [17, 15],
    [13, 14],
    [11, 17],
    [18, 6],
    [18, 23],
    [5, 8],
    [16, 6],
    [1, 14],
    [20, 8],
    [2, 5],
    [24, 23],
    [3, 4],
    [1, 19],
    [23, 20],
    [6, 12],
    [20, 14],
    [23, 21],
    [19, 14],
    [2, 4],
    [13, 22],
    [17, 22],
    [7, 6]
]
arr7 = [
    [1, 2],
    [1, 3],
    [1, 4],
    [1, 5],
    [1, 6]
]
# t0 = time.time()
# # 2 -> [1,4]
# find_min_shops(8,12, arr1)
# #print()
# # 2 -> [1,3]
# find_min_shops(6, 7, arr2)
# #print()
# # 2 -> [1,4] or [1,3]
# find_min_shops(4, 3, arr3)
# #print()
# # 3 -> [1,3,7]
# find_min_shops(7, 4, arr4)
# #print()
# # 2 -> [1,7] or [1,6]
# find_min_shops(7, 6, arr5)
# #print()
# # 3 -> [1,7,8] or [1,6,8]
# find_min_shops(8, 6, arr5)
# #print()
# # 8 -> [1,2,3,4,5,6,7,8]
# find_min_shops(8,0,[])
# #print()
# # 7 -> [1,3,4,5,6,7,8]
# find_min_shops(8,1,[[1,2]])
# # print()
# find_min_shops(6, len(arr7), arr7)
#print()

#
find_min_shops()

