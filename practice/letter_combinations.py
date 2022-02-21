class Solution(object):
    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        dig_to_char = {
            "2": "abc",
            "3": "def",
            "4": "jkl",
            "6": "mno",
            "7": "pqrs",
            "8": "tuv",
            "9": "wxyz"
        }
        results_array = []
        
        def backtracking_algorithm(temp_str, index):
            if len(temp_str) == len(digits):
                results_array.append(temp_str)
                return
                
            for el in dig_to_char[digits[index]]:
                backtracking_algorithm(temp_str + el, index + 1)
        
        backtracking_algorithm("", 0)
        
        return results_array
x = Solution()
print(x.letterCombinations("999"))
