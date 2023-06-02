from PIL import Image
import numpy as np
import csv


#1 chieu
path1 = r'C:\Users\admin\Desktop\1_chieu1.png'
img1 = Image.open(path1)
np_img1 = np.array(img1)
width, height = img1.size[:2]
out_file = open("1_chieu1.txt", "w")

for m in range (43, 740):
    for n in range (15, 590):
        if(np_img1[m, n, 0] == 0 and np_img1[m, n, 1] == 0 and np_img1[m, n, 2] == 0):
            out_file.write(str(n) + " x " + str(m) + "\n")

out_file.close()

path2 = r'C:\Users\admin\Desktop\1_chieu2.png'
img2 = Image.open(path2)
np_img2 = np.array(img2)
width, height = img2.size[:2]
out_file = open("1_chieu2.txt", "w")

for x in range (43, 740):
    for y in range (15, 590):
        if(np_img2[x, y, 0] == 10 and np_img2[x, y, 1] == 20 and np_img2[x, y, 2] == 30):
            out_file.write(str(y) + " x " + str(x) + "\n")

out_file.close()

path3 = r'C:\Users\admin\Desktop\1_chieu3.png'
img3 = Image.open(path3)
np_img3 = np.array(img3)
width, height = img3.size[:2]
out_file = open("1_chieu3.txt", "w")

for i in range (43, 740):
    for j in range (15, 590):
        if(np_img3[i, j, 0] == 20 and np_img3[i, j, 1] == 30 and np_img3[i, j, 2] == 40):
            out_file.write(str(j) + " x " + str(i) + "\n")

out_file.close()

with open('1_chieu1.txt', 'r') as f1:
    content1 = f1.read()

with open('1_chieu2.txt', 'r') as f2:
    content2 = f2.read()

with open('1_chieu3.txt', 'r') as f3:
    content3 = f3.read()
with open('1_chieu.txt', 'w') as new_file:
    new_file.write(content1)
    new_file.write(content2)
    new_file.write(content3)


#2 chieu
path4 = r'C:\Users\admin\Desktop\2_chieu1.png'
img4 = Image.open(path4)
np_img4 = np.array(img4)
width, height = img4.size[:2]
out_file = open("2_chieu1.txt", "w")

for m1 in range (43, 740):
    for n1 in range (15, 590):
        if(np_img4[m1, n1, 0] == 136 and np_img4[m1, n1, 1] == 0 and np_img4[m1, n1, 2] == 21):
            out_file.write(str(n1) + " x " + str(m1) + "\n")

out_file.close()

path5 = r'C:\Users\admin\Desktop\2_chieu2.png'
img5 = Image.open(path5)
np_img5 = np.array(img5)
width, height = img5.size[:2]
out_file = open("2_chieu2.txt", "w")

for m2 in range (43, 740):
    for n2 in range (15, 590):
        if(np_img5[m2, n2, 0] == 136 and np_img5[m2, n2, 1] == 10 and np_img5[m2, n2, 2] == 21):
            out_file.write(str(n2) + " x " + str(m2) + "\n")

out_file.close()

path6 = r'C:\Users\admin\Desktop\2_chieu3.png'
img6 = Image.open(path6)
np_img6 = np.array(img6)
width, height = img6.size[:2]
out_file = open("2_chieu3.txt", "w")

for m3 in range (43, 740):
    for n3 in range (15, 590):
        if(np_img6[m3, n3, 0] == 136 and np_img6[m3, n3, 1] == 20 and np_img6[m3, n3, 2] == 21):
            out_file.write(str(n3) + " x " + str(m3) + "\n")

out_file.close()

path7 = r'C:\Users\admin\Desktop\2_chieu4.png'
img7 = Image.open(path7)
np_img7 = np.array(img7)
width, height = img7.size[:2]
out_file = open("2_chieu4.txt", "w")

for m4 in range (43, 740):
    for n4 in range (15, 590):
        if(np_img7[m4, n4, 0] == 136 and np_img7[m4, n4, 1] == 30 and np_img7[m4, n4, 2] == 21):
            out_file.write(str(n4) + " x " + str(m4) + "\n")

out_file.close()

path8 = r'C:\Users\admin\Desktop\2_chieu5.png'
img8 = Image.open(path8)
np_img8 = np.array(img8)
width, height = img8.size[:2]
out_file = open("2_chieu5.txt", "w")

for m5 in range (43, 740):
    for n5 in range (15, 590):
        if(np_img8[m5, n5, 0] == 136 and np_img8[m5, n5, 1] == 40 and np_img8[m5, n5, 2] == 21):
            out_file.write(str(n5) + " x " + str(m5) + "\n")

out_file.close()

path9 = r'C:\Users\admin\Desktop\2_chieu6.png'
img9 = Image.open(path9)
np_img9 = np.array(img9)
width, height = img9.size[:2]
out_file = open("2_chieu6.txt", "w")

for m6 in range (43, 740):
    for n6 in range (15, 590):
        if(np_img9[m6, n6, 0] == 136 and np_img9[m6, n6, 1] == 50 and np_img9[m6, n6, 2] == 21):
            out_file.write(str(n6) + " x " + str(m6) + "\n")

out_file.close()

path10 = r'C:\Users\admin\Desktop\2_chieu7.png'
img10 = Image.open(path10)
np_img10 = np.array(img10)
width, height = img10.size[:2]
out_file = open("2_chieu7.txt", "w")

for m7 in range (43, 740):
    for n7 in range (15, 590):
        if(np_img10[m7, n7, 0] == 136 and np_img10[m7, n7, 1] == 60 and np_img10[m7, n7, 2] == 21):
            out_file.write(str(n7) + " x " + str(m7) + "\n")

out_file.close()

path11 = r'C:\Users\admin\Desktop\2_chieu8.png'
img11 = Image.open(path11)
np_img11 = np.array(img11)
width, height = img11.size[:2]
out_file = open("2_chieu8.txt", "w")

for m8 in range (43, 740):
    for n8 in range (15, 590):
        if(np_img11[m8, n8, 0] == 136 and np_img11[m8, n8, 1] == 70 and np_img11[m8, n8, 2] == 21):
            out_file.write(str(n8) + " x " + str(m8) + "\n")

out_file.close()




with open('2_chieu1.txt', 'r') as f4, open('2_chieu2.txt', 'r') as f5, open('2_chieu3.txt', 'r') as f6, open('2_chieu4.txt', 'r') as f7, open('2_chieu5.txt', 'r') as f8, open('2_chieu6.txt', 'r') as f9, open('2_chieu7.txt', 'r') as f10, open('2_chieu8.txt', 'r') as f11:
    content4 = f4.read()
    content5 = f5.read()
    content6 = f6.read()
    content7 = f7.read()
    content8 = f8.read()
    content9 = f9.read()
    content10 = f10.read()
    content11 = f11.read()

with open('2_chieu.txt', 'w') as new_file:
    new_file.write(content4)
    new_file.write(content5)
    new_file.write(content6)
    new_file.write(content7)
    new_file.write(content8)
    new_file.write(content9)
    new_file.write(content10)
    new_file.write(content11)



#Tim diem giao
A = [] 
B = [] 
C = [] 
D = [] 
E = [] 
F = [] 
G = [] 
H = [] 
I = [] 
J = [] 
K = [] 

with open("1_chieu1.txt", "r") as file1, open("1_chieu2.txt", "r") as file2, open("1_chieu3.txt", "r") as file3:
    for line1 in file1:
        a, b = map(int, line1.strip().split('x'))
        A.append((a, b))
    for line2 in file2:
        c, d = map(int, line2.strip().split('x'))
        B.append((c, d))
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


# Sử dụng set để chuyển các mảng thành set chứa các phần tử kiểu tuple (a, b)
setA = set(A)
setB = set(B)
setC = set(C)
setD = set(D)
setE = set(E)
setF = set(F)
setG = set(G)
setH = set(H)
setI = set(I)
setJ = set(J)
setK = set(K)



# Sử dụng intersection() method để tìm phần giao giữa hai set A và B
common_elems1 = setD.intersection(setF) 
common_elem2 = setA.intersection(setF) 
common_elems3 = common_elem2.intersection(setE) 
common_elems4 = setE.intersection(setG) 
common_elems6 = setB.intersection(setE) 
common_elems7 = setB.intersection(setA) 
common_elem8 = setC.intersection(setE) 
common_elems9 = common_elem8.intersection(setH) 
common_elems10 = setJ.intersection(setC) 
common_elems11 = setC.intersection(setA) 
common_elems12 = setI.intersection(setE) 
common_elems13 = setH.intersection(setI) 
common_elems14 = setI.intersection(setJ) 
common_elems15 = setK.intersection(setA) 
common_elems16 = setJ.intersection(setK) 
common_elems17 = setB.intersection(setH)
common_elems18 = setH.intersection(setG)
common_elems19 = setH.intersection(setF)


merged_set = common_elems1.union(common_elems3, common_elems4, common_elems6, common_elems7,common_elems9,common_elems10, common_elems11, 
                                 common_elems12, common_elems13, common_elems14, common_elems15, common_elems16, common_elems17,
                                 common_elems18, common_elems19)


M = []
M = list(merged_set)
with open('diem_giao.txt', 'w') as f:
    for a, b in M:
        f.write(f'{a} x {b} ' + '\n')
print(M)