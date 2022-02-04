import numpy as np
from sys import stdin

def find_min_shops(num_islands, num_trips, trips):
    ports_from_island = {}
    all_islands_array = [i for i in range(1, num_islands+1)]
                
    for arr in trips:
        if arr[0] in ports_from_island:
            ports_from_island[arr[0]].append(arr[1])
        else:
            ports_from_island[arr[0]] = [arr[1]]
            
        if arr[1] in ports_from_island:
            ports_from_island[arr[1]].append(arr[0])
        else:
            ports_from_island[arr[1]] = [arr[0]]
            
    def backtracking_algorithm(i, visited, islands_used, all_islands_used):
        test_sorted = visited[:]
        test_sorted.sort()
        
#        print(str(islands_used) + ", "),
#        print(test_sorted)
        
        if np.array_equal(test_sorted, all_islands_array):
#        if islands_used in all_islands_array and len(islands_used) >= len(all_islands_array):
#            print("true")
#            print(visited)
#            results_array.append(islands_used)
            all_islands_used.append(islands_used)
#            print("islands used: " + str(islands_used))
#            return
            
        if len(visited) >= num_islands:
#            print("false")
            return
        if i > num_islands:
#            print("false")
            return

        islands_used_copy = islands_used[:]
        visited_copy = visited[:]
        
#        if not i in visited:
        visited.append(i)
        
#        visited.pop()
        
#        visited += ports_from_island[i]

        
#        flag = True

        if i in ports_from_island:
            for k in ports_from_island[i]:
                if k not in visited:
                    visited.append(k)

#            if k in visited:
#                flag = False
#
#        if i in ports_from_island:
#            visited += ports_from_island[i]
        
        islands_used.append(i)

        
#        if flag:
#            visited += ports_from_island[j]
#            islands_used.append(j)

#        islands_used_copy = islands_used[:]
        if i <= num_islands:
#            print("here")
            backtracking_algorithm(i + 1, visited, islands_used, all_islands_used)
            backtracking_algorithm(i + 1, visited_copy, islands_used_copy, all_islands_used)

#            print("********************************")
    
#    print("here: " + str(results_array))
    results_array = []
#    print("--------------------------")
    backtracking_algorithm(1,[], [], results_array)
#    print(ports_from_island)
    if len(results_array) > 0:
        min_arr = results_array[0]
        for el in results_array:
            if len(el) < len(min_arr):
                min_arr = el
            
#        print(results_array)
#        min_arr = results_array[0]
#        print(min_arr)
        print(len(min_arr))
        for i in range(len(min_arr)):
            if i < len(min_arr):
                print(str(min_arr[i])),
            else:
                print(str(min_arr[i]))

#    print(min(result_array))
#    return result_array

arr = [
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
    [1,2],
    [1,4],
    [2,4],
    [2,3],
    [3,5],
    [3,6],
    [4,5]
]
arr3 = [
    [1,2],
    [1,3],
    [3,4]
]
arr4 = [
    [1,2],
    [1,5],
    [1,4],
    [3,6]
]
arr5 = [
    [1,2],
    [1,3],
    [1,4],
    [1,5],
    [1,6],
    [6,7]
]
# 2 -> [1,4]
#find_min_shops(8,12, arr)
#
## 2 -> [1,3]
#find_min_shops(6, 7, arr2)
#
## 2 -> [1,4]
#find_min_shops(4, 3, arr3)
#
## 3 -> [1,3,7]
#find_min_shops(7, 4, arr4)
#
## 2 -> [1,7]
#find_min_shops(7, 6, arr5)
#
## 8 -> [1,2,3,4,5,6,7,8]
#find_min_shops(8,0,[])
#
## 7 -> [1,3,4,5,6,7,8]
#find_min_shops(8,1,[[1,2]])

in1 = str(raw_input()).strip().split(" ")
n = int(in1[0])
t = int(in1[1])
#print("n: " + str(n) + ", t: " + str(t))
a = []
for i in range(t):
    arr = []
    in2 = str(raw_input()).strip().split(" ")
    arr.append(int(in2[0]))
    arr.append(int(in2[1]))
    a.append(arr)

#print(a)
find_min_shops(n,t,a)
