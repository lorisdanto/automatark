import os,sys



def main():
    dir = os.getcwd()
    #print dir
    for root, dirs, files in os.walk(dir):
        for fname in files:
            
            #fullName = os.path.join(root,fname)
            if'DS_Store' in fname:
                fname = os.path.join(root, fname)
                os.rename(fname, os.path.join(root,'DS_Store'))
main()
