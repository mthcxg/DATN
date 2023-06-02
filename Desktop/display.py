import cv2
import math

path = r'C:\Users\admin\Desktop\PJAI.png' 
img = cv2.imread(path)

cv2.namedWindow('Image')
 
 
points = []
def get_points(event, x, y, flags, param):
    if event == cv2.EVENT_LBUTTONUP:
        points.append((x, y))
        if len(points) == 2:
            cv2.destroyAllWindows()
cv2.setMouseCallback('Image', get_points)

while True:
    cv2.imshow('Image', img)
    if len(points) == 2:
        break
    cv2.waitKey(1)

print('Coordinate of point 1:', points[0])
print('Coordinate of point 2:', points[1])


A = [] #luu duong 2 chieu

path2 = r'C:\Users\admin\Desktop\2_chieu.txt' 
with open(path2, "r") as f:
      for line in f:
        a, b = map(int, line.strip().split('x'))
        A.append((a, b))


B = [] #luu duong 1 chieu

path3 = r'C:\Users\admin\Desktop\1_chieu.txt' 
with open(path3, "r") as f:
      for line in f:
        c, d = map(int, line.strip().split('x'))
        B.append((c, d))


P = [] #1.1 
Q = [] #1.2
C = [] #1.3
D = [] #2.1
E = [] #2.2
F = [] #2.3
G = [] #2.4
H = [] #2.5
I = [] #2.6
J = [] #2.7
K = [] #2.8
M = [] #diem giao
N = []
with open("1_chieu1.txt", "r") as file1, open("1_chieu2.txt", "r") as file2, open("1_chieu3.txt", "r") as file3:
    for line1 in file1:
        ax, bx = map(int, line1.strip().split('x'))
        P.append((ax, bx))
    for line2 in file2:
        cx, dx = map(int, line2.strip().split('x'))
        Q.append((cx, dx))
    for line3 in file3:
        e, f = map(int, line3.strip().split('x'))
        C.append((e, f))

with open('2_chieu1.txt', 'r') as file4, open('2_chieu2.txt', 'r') as file5, open('2_chieu3.txt', 'r') as file6, open('2_chieu4.txt', 'r') as file7, open('2_chieu5.txt', 'r') as file8, open('2_chieu6.txt', 'r') as file9, open('2_chieu7.txt', 'r') as file10, open('2_chieu8.txt', 'r') as file11:
    for line4 in file4:
        a1, b1 = map(int, line4.strip().split('x'))
        D.append((a1, b1))
    for line5 in file5:
        a2, b2 = map(int, line5.strip().split('x'))
        E.append((a2, b2))
    for line6 in file6:
        a3, b3 = map(int, line6.strip().split('x'))
        F.append((a3, b3))
    for line7 in file7:
        a4, b4 = map(int, line7.strip().split('x'))
        G.append((a4, b4))
    for line8 in file8:
        a5, b5 = map(int, line8.strip().split('x'))
        H.append((a5, b5))
    for line9 in file9:
        a6, b6 = map(int, line9.strip().split('x'))
        I.append((a6, b6))
    for line10 in file10:
        a7, b7 = map(int, line10.strip().split('x'))
        J.append((a7, b7))
    for line11 in file11:
        a8, b8 = map(int, line11.strip().split('x'))
        K.append((a8, b8))

with open('diem_giao.txt', 'r') as f:
    for l in f:
        a9, b9 = map(int, l.strip().split('x'))
        M.append((a9, b9))
#xet points[0]
closest_point_in_A = min(A, key=lambda x: math.sqrt((x[0]-points[0][0])**2 + (x[1]-points[0][1])**2))

# Tính khoảng cách Euclid giữa từng điểm trong mảng B với điểm points[0]
closest_point_in_B = min(B, key=lambda y: math.sqrt((y[0]-points[0][0])**2 + (y[1]-points[0][1])**2))

# So sánh khoảng cách Euclid và in ra kết quả tương ứng
distance_to_A = math.sqrt((closest_point_in_A[0]-points[0][0])**2 + (closest_point_in_A[1]-points[0][1])**2)
distance_to_B = math.sqrt((closest_point_in_B[0]-points[0][0])**2 + (closest_point_in_B[1]-points[0][1])**2)

if distance_to_A < distance_to_B:
    print("point[0] thuoc duong 2 chieu:", closest_point_in_A)
    x = closest_point_in_A
    if x in D:
        print("x thuoc duong 2_chieu1")
    elif x in E:
        print("x thuoc duong 2_chieu2")
    elif x in F:
        print("x thuoc duong 2_chieu3")
    elif x in G:
        print("x thuoc duong 2_chieu4")
    elif x in H:
        print("x thuoc duong 2_chieu5")
    elif x in I:
        print("x thuoc duong 2_chieu6")
    elif x in J:
         print("x thuoc duong 2_chieu7")
    elif x in K:
        print("x thuoc duong 2_chieu8")
else:
    print("point[0] thuoc duong 1 chieu:", closest_point_in_B)
    x = closest_point_in_B
    if x in P:
        print("x thuoc duong 1_chieu1")
    elif x in Q:
        print("x thuoc duong 1_chieu2")
    elif x in C:
        print("x thuoc duong 1_chieu3")

#xet points[1]
closest_point_in_A1 = min(A, key=lambda z: math.sqrt((z[0]-points[1][0])**2 + (z[1]-points[1][1])**2))

# Tính khoảng cách Euclid giữa từng điểm trong mảng B với điểm points[0]
closest_point_in_B1 = min(B, key=lambda k: math.sqrt((k[0]-points[1][0])**2 + (k[1]-points[1][1])**2))

# So sánh khoảng cách Euclid và in ra kết quả tương ứng
distance_to_A1 = math.sqrt((closest_point_in_A1[0]-points[1][0])**2 + (closest_point_in_A1[1]-points[1][1])**2)
distance_to_B1 = math.sqrt((closest_point_in_B1[0]-points[1][0])**2 + (closest_point_in_B1[1]-points[1][1])**2)

if distance_to_A1 < distance_to_B1:
    print("point[1] thuoc duong 2 chieu:", closest_point_in_A1)
    y = closest_point_in_A1
    if y in D:
        print("y thuoc duong 2_chieu1")
        
    elif y in E:
        print("y thuoc duong 2_chieu2")
    elif y in F:
        print("y thuoc duong 2_chieu3")
    elif y in G:
        print("y thuoc duong 2_chieu4")
    elif y in H:
        print("y thuoc duong 2_chieu5")
    elif y in I:
        print("y thuoc duong 2_chieu6")
    elif y in J:
        print("y thuoc duong 2_chieu7")
    elif y in K:
        print("y thuoc duong 2_chieu8")
else:
    print("point[1] thuoc duong 1 chieu:", closest_point_in_B1)
    y = closest_point_in_B1
    if y in P:
        print("y thuoc duong 1_chieu1")
    elif y in Q:
        print("y thuoc duong 1_chieu2")

    elif y in C:
        print("y thuoc duong 1_chieu3")






