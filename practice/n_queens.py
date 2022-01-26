def solve_n_queens(board, starting_row):
    #check if starting row = len(board), if so then we are done
    if starting_row == len(board):
        print(board)
    
    #iterate and check if new queen can be placed
    #check if a move is valid from 0 - starting_row
    #if valid move, place queen there

    for i in range(len(board)):
        valid_move = True
        for j in range(0,starting_row):
            if board[j] == i or board[j] == (i+starting_row-j) or board[j] == (i-starting_row+j):
                valid_move = False

        if valid_move:
            board[starting_row] = i
            solve_n_queens(board, starting_row + 1)

board = [0,0,0,0]
solve_n_queens(board, 0)
