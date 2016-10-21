#this function is slightly different from monaTest.py, this only prints file name and its total time in millliseconds

# how to run this program:
# this program is tested on python 2.7.10
# first we need EasyProcess module
# credit to http://easyprocess.readthedocs.io/en/latest/
# run $pip install EasyProcess
# to install this module.
# after installation, run this program as normal python files
# the default output will be printed to the console
# thus, use '>>' to redirect output to file instead
# e.g $python monaSriptM2L.py >> output.txt
# the output will be directed to the output.txt
# make sure to delete old output.txt before re-run the program,
# the old information in output.txt will not be automatically covered by the new input

import os
from easyprocess import EasyProcess
str = 'Total time: '

def main():
    #dir = os.getcwd() # this returns current directory of monaTest.py
    dir = 'C:\github\automatark\m2l-str'
    #dir = os.path.join(dir, 'lift') # this joins the current directory and the directory to MonaFiles
    #print header of the output in format
    #print '%-25s %-25s'%('File Name', 'Total Time')
    for root, dirs, files in os.walk(dir):
        for fname in files:
            #print fname
            fullName = os.path.join(root,fname)
            if fname.endswith('.mona'):
                callProcess('mona '+ fullName, fname)
            

#this function is slightly different from monaTest.py, this only prints file name and its total time in millliseconds
def callProcess(command, filename):
    #set timeout in seconds below in .call(timeout=XX)
    proc = EasyProcess(command).call(timeout=60)
    totalTime = 60000 #default totaltime is timeout on 60000ms
    #it will be refreshed if the experiment does not exceed time limit
    output = proc.stdout
    
    if output is None:
        print '%-25s %-25s'%(filename, 'error')
    else:
        # below will check the information of each file
        if 'unsatisfiable' in output:
            satisfiability = 'false'
        else:
            satisfiability = 'true'

        if '100%' in output:
            # if the AUTOMATON CONSTRUCTION is 100%,
            # that means this MONA file has complete result
            for line in output.split(os.linesep):
                if str in line:
                    # extract total time
                    time= line[line.index(str)+len(str):].encode('raw_unicode_escape').decode('utf8')
                    totalTime= int(time[6:8])*1000+int(time[9:11])*10+int(time[3:5])*60000
        else:
        # if the AUTOMATON CONSTRUCTION is not 100%,
        # that means it exceeds the timeout limit
            totalTime = 60000
            satisfiability = 'false'
                #print each file's info in format
        print '%-25s %-25s %-25s'%(filename, totalTime, satisfiability)


main()
