f = open("tttt.out", mode='w')

def process(s):
    nw = 0
    no = 0
    nt = 0
    for c in s:
        if c=='X': nw+=1
        if c=='O': no+=1
        if c=='T': nt+=1

    #print(s, nw, no, nt)
    if nw+nt == 4: return 2
    if no+nt == 4: return 1
    else: return 0


N = int(input())
for h in range(1, N+1):
    board = list()
    for i in range(4):
        board.append(str(input()))
    input() # blank line

    wx = False
    wo = False
    
    for s in board:
        
        res = process(s)
        if (res == 2):
            wx = True
        if (res == 1):
            wo = True

    for i in range(4):
        s = ""
        for j in range(4):
            s += board[i][j]
            
        res = process(s)
        if (res == 2):
            wx = True
        if (res == 1):
            wo = True
            
    for i in range(4):
        s = ""
        for j in range(4):
            s += board[j][i]
            
        res = process(s)
        if (res == 2):
            wx = True
        if (res == 1):
            wo = True

    s = ""
    for i in range(4):
        s += board[i][4-1-i]
    
    res = process(s)
    if (res == 2):
        wx = True
    if (res == 1):
        wo = True

    s = ""
    for i in range(4):
        s += board[i][i]

    res = process(s)
    if (res == 2):
        wx = True
    if (res == 1):
        wo = True

    f.write("Case #"+str(h)+": ")
    if wx:
        if wo:
            f.write("Draw"+"\n")
        else:
            f.write("X won\n")
    else:
        if wo:
            f.write("O won\n")
        else:
            if '.' in "".join(e for e in board):
                f.write("Game has not completed"+"\n")
            else:
                f.write("Draw"+"\n")

            
f.close()
