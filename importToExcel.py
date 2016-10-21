#you need to install the package below 
#$pip install openpyxl
#put this file and the the mona.xlsx in the same folder
#output will be in the file output.xlsx
#you need to create a excel file with the first row be the header:
# in this case, create a file names mona.xlsx and enter the first row as name time satisfiability
#then enter the name of the excel in line 16
#the output should be correct, if you get format warnings when you open the result excel 
#you can simply copy all rows and cols in the excel to another one
from openpyxl import load_workbook



def main():
    numOfRow=2; #start from the second row because first row is the header
    xfile = load_workbook('mona.xlsx') #assume the input excel file with first row filled is named 'mona.xlsx'
    
    sheet = xfile.active

    #assume the data you need to import is in output.txt
    with open('output.txt') as f:
        for line in f:
            #get time from file
            line = line.split()
            name = line[0]
            time= int(line[1])
            if len(line) ==2:
                satis = ''
            else:   
                satis = line[2]
            sheet['A'+str(numOfRow)] = name
            sheet['B'+str(numOfRow)] = time
            sheet['C'+str(numOfRow)] = satis
            numOfRow +=1
            
    xfile.save('output.xlsx') #the output is in output.xlsx

main()
