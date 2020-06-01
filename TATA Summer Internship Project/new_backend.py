import matplotlib
import csv
import numpy as np
from tkinter import *
from tkinter import filedialog
from matplotlib.figure import Figure
from matplotlib import pyplot as plt
from matplotlib import colors as mcolors
colors = dict(mcolors.BASE_COLORS, **mcolors.CSS4_COLORS)
# Sort colors by hue, saturation, value and name.
by_hsv = sorted((tuple(mcolors.rgb_to_hsv(mcolors.to_rgba(color)[:3])), name)
                for name, color in colors.items())
sorted_names = [name for hsv, name in by_hsv]
sorted_names.reverse()
class process:
	def __init__(self,p_num,name,process_time,stack):
		self.p_num=p_num
		self.name=name
		self.process_time=process_time
		self.stack=stack
		self.maxp_time=0
		self.maxp_stack=0
		self.minp_time=100000000
		self.minp_stack=1000000000
root=Tk()
global filename
filename = filedialog.askopenfilename(title="Select the file")
newlis=[]
newset=set()
csvfile=open(filename)
csv_reader=csv.reader(csvfile,delimiter=',')
next(csv_reader)
i=0
j=0
for line in csv_reader:
	if i==0:
	    i=int(line[0])
	if int(line[0])>=i+10:
		break
	if line[1] in newset or line[1]=="":
	    continue
	else:
	    newset.add(line[1])
for x in newset:
    newlis.append(process(j,x,0,0))
    j+=1
csv_file2=open(filename)
csv_reader2=csv.reader(csv_file2)
count=0
arr_ptime = [[0 for i in range(1)] for j in range(len(newset))]
arr_pstack = [[0 for i in range(1)] for j in range(len(newset))]
next(csv_reader2)
for line in csv_reader2:
    if len(line)==4:
	    count+=1
    if line[0]=='' or line[1]=='' or line[2]=='' or line[3]=='':
        continue
    for x in newlis:
        if x.name==line[1]:
            x.process_time+=int(line[2])
            arr_ptime[x.p_num].append(int(line[2]))
            x.maxp_time=max(int(line[2]),x.maxp_time)
            x.minp_time=min(int(line[2]),x.minp_time)
            x.stack+=int(line[3])
            arr_pstack[x.p_num].append(int(line[3]))
            x.maxp_stack=max(int(line[3]),x.maxp_stack)
            x.minp_stack=min(int(line[3]),x.minp_stack)
dropdown=Options()
window1=Toplevel()
window1.title('Average values of Process')
window2=Toplevel()
window2.title('Min_max values of Process')
data1=list()
data2=list()
data1.append("Number of minor frames= "+str(int(count/10)))
data2.append("Number of minor frames= "+str(int(count/10)))
for x in newlis:
    data1.append("Process Name="+str(x.name)+"  Process time="+str(int(x.process_time/count))+"ns  Process stack size="+str(int(x.stack/count)))
    data2.append("Process Name= "+str(x.name)+":")
    data2.append("Min p_time= "+str(x.minp_time)+"  Min p_stack= "+str(x.minp_stack)+"  Max p_time= "+str(x.maxp_time)+"  Max p_stack= "+str(x.maxp_stack))
for st in data1:
    mylabel=Label(root,text=st)
    mylabel.pack()
for st in data2:
	mylabel=Label(window,text=st)
	mylabel.pack()
labels=[]
time=[]
mem_stack=[]
for x in newlis:
    labels.append(x.name)
    time.append(x.process_time)
    mem_stack.append(x.stack)
colors=[]
for x in range(0,len(sorted_names),5):
    colors.append(sorted_names[x])
plt.figure(num=1, figsize=(12, 8), dpi=80, facecolor='w', edgecolor='k')
plt.pie(time, labels=labels, colors=colors,
autopct='%1.1f%%', shadow=True, startangle=90)
plt.figure(num=2, figsize=(12, 8), dpi=80, facecolor='w', edgecolor='k')
plt.pie(mem_stack, labels=labels, colors=colors,
autopct='%1.1f%%', shadow=True, startangle=90)
plt.show()
no_iter=np.arange(0,len(arr_ptime[0]),1)
plt.figure(num=3, figsize=(12, 8), dpi=80, facecolor='w', edgecolor='k')
plt.plot(no_iter,arr_ptime[0])
plt.title('Process')
plt.show()