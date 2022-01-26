def subset_sum(X, target, bag):
    if target == 0:
        print(bag)
        return True

    elif len(X) == 0 or target < 0:
        return False

    else:
        val = X.pop(len(X)//2)
        print("target-val: " + str(target-val))
        bag.append(val)
        w = subset_sum(X, target-val, bag)
        wout = subset_sum(X, target, bag)

        return w or wout

arr = [8,6,7,5,3,10,9]

print(subset_sum(arr, 15, []))
