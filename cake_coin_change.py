
saved = dict()
#saved[0] = 1 # base case

def numways(amount, denominations):
    if (amount, denominations) in saved:
        return saved[(amount, denominations)]

    if amount == 0:
        return 1
    if amount < 0:
        return 0

    else:
        ans = 0
    
        for i in range(len(denominations)):
            ans += numways(amount-denominations[i], denominations[i:]) # don't allow backtracking to previously used denominations to ensure against multiple orderings

        # shortcut: ans = sum(numways(amount-d, denominations) for d in denominations)

        saved[(amount, denominations)] = ans # save ans
        return ans

val = 7#int(input())
denoms = (1,2,3)#tuple(map(int, input().split()))
print(numways(val, denoms))
