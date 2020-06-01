import matplotlib
import xlrd
from tkinter import *
from tkinter import filedialog
from PIL import Image, ImageTk
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg, NavigationToolbar2Tk
from matplotlib.figure import Figure
import tkinter as tk
from tkinter import ttk
from tkinter import messagebox
import pandas
import pandas as pd
import numpy as np
from matplotlib import pyplot as plt
from matplotlib import style
import os
import csv
import matplotlib.pyplot as plt
from matplotlib import colors as mcolors
from tkinter.filedialog import asksaveasfile
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


LARGE_FONT = ("Verdana", 12)
NORM_FONT = ("Verdana", 10)
SMALL_FONT = ("Verdana", 0)
style.use("ggplot")


class Run(tk.Tk):

    def __init__(self, *args, **kwargs):
        tk.Tk.__init__(self, *args, **kwargs)

        tk.Tk.wm_title(self, "PROCESS TIMING ANALYSIS")
        #tk.Tk.iconbitmap(self, 'download1.ico')
        tk.Tk.configure(self, background='#4D4D4D')

        container = tk.Frame(self)
        container.pack(side="top", fill="both", expand=True)
        container.grid_rowconfigure(0, weight=1)
        container.grid_columnconfigure(0, weight=1)

        self.frames = {}

        for F in (StartPage, PageOne):
            frame = F(container, self)
            # frame = F(container, self)
            self.frames[F] = frame
            frame.grid(row=0, column=0, sticky="nsew")

        self.show_frame(StartPage)

    def show_frame(self, cont):
        frame = self.frames[cont]
        frame.tkraise()



def show(clicked):
    if clicked=="Select":
        messagebox.showerror("ERROR","Select a Process")
    else:
        for x in newlis:
            if x.name==clicked:
                no_iter=np.arange(0,len(arr_ptime[x.p_num]),1)
                plt.figure(num=1, figsize=(14, 10), dpi=80, facecolor='w', edgecolor='k')
                plt.plot(no_iter,arr_ptime[x.p_num],color='blue',linewidth=0.4)
                plt.title(clicked)
                plt.xlabel('Number of Minor frames')
                plt.ylabel('Process time')
                plt.figure(num=2, figsize=(14, 10), dpi=80, facecolor='w', edgecolor='k')
                plt.plot(no_iter,arr_pstack[x.p_num],color='blue',linewidth=0.4)
                plt.title(clicked)
                plt.xlabel('Number of Minor frames')
                plt.ylabel('Process Stack')
                plt.show()


def fileDialog():
    global filename
    filename = filedialog.askopenfilename(title="Select the file")
def helper3():
    if filename=='':
        messagebox.showerror("ERROR","Select a file")
        return
    global newlis
    newlis=[]
    global newset
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
    global minorframes
    minorframes=list()
    count=0
    global arr_ptime 
    global framesnum
    arr_ptime = [[0 for i in range(1)] for j in range(len(newset))]
    global arr_pstack
    arr_pstack = [[0 for i in range(1)] for j in range(len(newset))]
    next(csv_reader2)
    i=0
    j=len(newset)
    newl=str()
    framesnum=1
    for line in csv_reader2:
        i=i+1
        if i==j:
            if len(newl)!=0:
                minorframes.append(newl)
            framesnum=framesnum+1
            newl=''
            i=0
        if line[0]=='' or line[1]=='' or line[2]=='' or line[3]=='':
            i=i-1
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
                if int(line[2])==0:
                    if newl=='':
                        newl="Minor_FrameNo "+str(framesnum)+": "+line[1]
                    else:
                        newl=newl+" , "+line[1]

    window=Toplevel()
    window.geometry("400x400")
    window.title('Process Details')
    '''menubar = tk.Menu(window)
    savefile = tk.Menu(menubar, tearoff=1)
    savefile.add_command(label="Generate Data", command=save)
    savefile.add_separator()
    savefile.add_command(label="Data", command=helper)
    savefile.add_separator()
    savefile.add_command(label="Quit", command=quit)
    savefile.add_separator()
    menubar.add_cascade(label="Save Data", menu=savefile)
    tk.Tk.config(window, menu = menubar)'''

    click=StringVar()
    drop=ttk.OptionMenu(window,click,"Select","Average Values","Min_max Values","Idle Processes").pack()
    button1 = ttk.Button(window, text="Show Data", command=lambda: helper(click.get()))
    button1.pack()
    button2=ttk.Button(window,text="Pie Plots",command=plotgraph)
    button2.pack()
    clicked=StringVar()
    drop=ttk.OptionMenu(window,clicked,"Select",*list(newset))
    drop.pack()
    button3=ttk.Button(window,text="Show Graph",command=lambda: show(clicked.get())).pack()
    button4 = ttk.Button(window, text="Exit",command=window.destroy)
    button4.pack()

filename = ""

def helper(click):
    if click=="Select":
        messagebox.showerror("ERROR","Select a Option")
    global data1
    global data2
    data1=list()
    data2=list()
    data1.append("Number of Minor Frames= "+str(int(framesnum)))
    data2.append("Number of Minor Frames= "+str(int(framesnum)))
    for x in newlis:
        data1.append("Process Name="+str(x.name)+"  Process time="+str(int(x.process_time/framesnum))+"ns  Process stack size="+str(int(x.stack/framesnum)))
        data2.append("Process Name= "+str(x.name)+":")
        data2.append("Min p_time= "+str(x.minp_time)+"  Min p_stack= "+str(x.minp_stack)+"  Max p_time= "+str(x.maxp_time)+"  Max p_stack= "+str(x.maxp_stack))

    len_max = 0
    for m in data1:
        if len(m) > len_max:
            len_max = len(m)
    len_max2 = 0
    for m in data2:
        if len(m) > len_max2:
            len_max2 = len(m)
    if click=="Average Values":
        root=Toplevel()
        root.title('Average Values of Process')
        frame = Frame(root)
        sb = Scrollbar(frame)
        sb.pack(side=RIGHT, fill=Y)
        mylist = Listbox(frame, height=20, width=len_max)
        mylist.config(yscrollcommand=sb.set)
        for st in data1:
            mylist.insert(END, st)
        mylist.pack(side=LEFT)
        sb.config(command=mylist.yview)
        frame.pack()
        button5=ttk.Button(root,text='SAVE DATA',command=saveavg).pack()
    if click=="Min_max Values":
        window=Toplevel()
        window.title('Min_max Values of Process')
        frame = Frame(window)
        sb = Scrollbar(frame)
        sb.pack(side=RIGHT, fill=Y)
        mylist = Listbox(frame, height=20, width=len_max2)
        mylist.config(yscrollcommand=sb.set)
        for st in data2:
            mylist.insert(END, st)
        mylist.pack(side=LEFT)
        sb.config(command = mylist.yview)
        frame.pack()
        button6=ttk.Button(window,text='SAVE DATA',command=saveminmax).pack()
    if click=="Idle Processes":
        window2=Toplevel()
        window2.title('Idle Processes')
        frame=Frame(window2)
        scroll=Scrollbar(frame)
        scroll.pack(side=RIGHT,fill=Y)
        mylist=Listbox(frame, height=20,width=len(minorframes))
        mylist.config(yscrollcommand=scroll.set)
        for line in minorframes:
            mylist.insert(END,line)
        mylist.pack(side=LEFT)
        scroll.config(command = mylist.yview)
        frame.pack()
        button7=Button(window2,text='SAVE DATA',command=saveidleproc).pack()

def plotgraph():
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
    plt.figure(num=1, figsize=(14, 10), dpi=80, facecolor='w', edgecolor='k')
    plt.title('Process Time plot')
    plt.pie(time, labels=labels, colors=colors,
    autopct='%1.1f%%', shadow=True, startangle=90)
    plt.figure(num=2, figsize=(14, 10), dpi=80, facecolor='w', edgecolor='k')
    plt.title('Process Stack Plot')
    plt.pie(mem_stack, labels=labels, colors=colors,
    autopct='%1.1f%%', shadow=True, startangle=90)
    plt.show()


def saveavg():
    file = filedialog.asksaveasfile(mode = 'w', defaultextension = ".txt")
    if file:
        file.write('AVERAGE VALUES OF PROCESSES :')
        file.write("\n")
        for row in data1:
            file.write(row)
            file.write("\n")
        file.close()
            
def saveminmax():
    file = filedialog.asksaveasfile(mode = 'w', defaultextension = ".txt")
    if file:
        file.write('MIN-MAX VALUES OF PROCESSES :')
        file.write("\n")
        for row in data2:
            file.write(row)
            file.write("\n")
        file.close()

def saveidleproc():
    file = filedialog.asksaveasfile(mode = 'w', defaultextension = ".txt")
    if file:
        file.write('IDLE PROCESSES IN EACH Frame :')
        file.write("\n")
        for row in minorframes:
            file.write(row)
            file.write("\n")
        file.close()


class StartPage(tk.Frame):

    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
        label = tk.Label(self, text="Hey", font=LARGE_FONT)
        label.pack(pady=10, padx=10)

        button1 = ttk.Button(self, text="Agree",
                             command=lambda: controller.show_frame(PageOne))
        button1.pack()

        button2 = ttk.Button(self, text="Disagree",
                             command=quit)
        button2.pack()


class PageOne(tk.Frame):

    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
        label = tk.Label(self, text="Page1", font=LARGE_FONT)
        label.pack(pady=10, padx=10)

        #img = PhotoImage(file = "button1.png")
        # imgs = img.subsample(2,2)

        button1 = ttk.Button(self, text="Choose file",
                             command=fileDialog)
        button1.pack()
        # # button1.configure(image = img, compound = RIGHT)
        # button3 = ttk.Button(self, text="Show Data",
        #                      command=helper)
        # button3.pack()
        # button4=ttk.Button(self,text="Plot Graphs",command=plotgraph)
        # button4.pack()
        button3 = ttk.Button(self, text="Analyse",
                            command=helper3)
        button3.pack()
        button2 = ttk.Button(self, text="Back to Home",
                            command=lambda: controller.show_frame(StartPage))
        button2.pack()



app = Run()
app.geometry("400x400")
app.mainloop()