def combination_sums(candidates, target):
    res = []

    def backtracking_algo(curr_list, curr_sum, i):
        if curr_sum == target:
            res.append(curr_list.copy())
            return
        
        elif curr_sum > target or i >= len(candidates):
            return

        else:
            curr_list.append(candidates[i])
            backtracking_algo(curr_list, curr_sum + candidates[i], i)
            curr_list.pop()
            backtracking_algo(curr_list, curr_sum, i + 1)

    backtracking_algo([],0,0)
    
    return res
print(combination_sums([1,2,3,6,7,4], 7))
