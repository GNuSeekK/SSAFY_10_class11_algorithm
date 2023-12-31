def solution(book_time):
    l = [0 for _ in range(25*60)]
    for i in book_time:
        start = int(i[0][:2])*60+int(i[0][3:])
        end = int(i[1][:2])*60+int(i[1][3:])+10

        for j in range(start,end):
            l[j] +=1
    
    answer = max(l)


    return answer

# 테스트 1 〉	통과 (0.14ms, 10.3MB)
# 테스트 2 〉	통과 (6.47ms, 10.4MB)
# 테스트 3 〉	통과 (37.92ms, 10.5MB)
# 테스트 4 〉	통과 (14.34ms, 10.5MB)
# 테스트 5 〉	통과 (0.17ms, 10.4MB)
# 테스트 6 〉	통과 (23.70ms, 10.5MB)
# 테스트 7 〉	통과 (27.36ms, 10.4MB)
# 테스트 8 〉	통과 (10.69ms, 10.3MB)
# 테스트 9 〉	통과 (13.47ms, 10.5MB)
# 테스트 10 〉	통과 (20.15ms, 10.5MB)
# 테스트 11 〉	통과 (38.33ms, 10.4MB)
# 테스트 12 〉	통과 (34.77ms, 10.4MB)
# 테스트 13 〉	통과 (5.48ms, 10.4MB)
# 테스트 14 〉	통과 (13.37ms, 10.5MB)
# 테스트 15 〉	통과 (17.20ms, 10.6MB)
# 테스트 16 〉	통과 (9.78ms, 10.4MB)
# 테스트 17 〉	통과 (15.73ms, 10.4MB)
# 테스트 18 〉	통과 (13.03ms, 10.6MB)
# 테스트 19 〉	통과 (7.69ms, 10.6MB)