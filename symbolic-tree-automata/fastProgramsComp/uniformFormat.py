import os,sys



def main():
    dir = os.getcwd()
    #print dir
    for root, dirs, files in os.walk(dir):
        for fname in files:
            
            #fullName = os.path.join(root,fname)
            if fname.endswith('.md') or fname.endswith('.py') or fname.endswith('.csv'):
                continue
            if fname.endswith('.timbuk'):
                fname = os.path.join(root, fname)
                os.rename(fname, fname.replace('.timbuk','.fast'))

main()
