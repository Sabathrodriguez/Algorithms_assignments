def annagram_counter():
    #input
    input_1 = str(input()).split(" ")
    num1 = int(input_1[0])
    num2 = int(input_1[1])
    count = 0
    temp_input = []
    
    while count < num1:
        count += 1
        temp_input.append(input())
        
    num = 0
    
    for i in range(len(temp_input)):
        temp_list = list(temp_input[i].strip())
        temp_list.sort()
        temp_input[i] = ''.join(temp_list)
        
    temp_input.sort()
        
    i = 0
    
    while i < len(temp_input)-1:
        if temp_input[i] == temp_input[i+1]:
            num += 1

            while i < len(temp_input)-1 and temp_input[i] == temp_input[i+1]:
                i += 1
        else:
            i += 1
    
    print(num)
    
annagram_counter()
