import re

regexlib = [0] * 3072
snort = [0] * 1254
dotstar03 = [0] * 300
dotstar06 = [0] * 300
dotstar09 = [0] * 299
exactmath = [0] * 300
ranges05 = [0] * 300
ranges1 = [0] * 299

with open('regex/regex.csv') as f:
    lines = f.readlines()
    for i, line in enumerate(lines):
        if i % 2 == 0:
            continue

        regex_string = line[line.index('"') + 1:-1]
        regex_string = re.sub(r'(\\x\d{,2})', r'\\\1', regex_string)
        regex_string = re.sub(r'(?<!\\)(\"|\')', r'\\\1', regex_string)

        try:
            regex = eval("'" + regex_string + "'")
        except:
            try:
                regex = eval('"' + regex_string + '"')
            except:
                print(line)
                exit()
                continue

        regex = regex.replace('\b', '\\b')
        regex = repr(regex)

        index = int(re.findall(r'(?<=_)\d+', line[:line.index('"')])[-1]) - 1

        if line.startswith('regexlib'):
            regexlib[index] = regex
        elif line.startswith('regex_snort'):
            snort[index] = regex
        elif line.startswith('regex_Becchi_dotstar0.3'):
            dotstar03[index] = regex
        elif line.startswith('regex_Becchi_dotstar0.6'):
            dotstar06[index] = regex
        elif line.startswith('regex_Becchi_dotstar0.9'):
            dotstar09[index] = regex
        elif line.startswith('regex_Becchi_exact-math'):
            exactmath[index] = regex
        elif line.startswith('regex_Becchi_ranges05'):
            ranges05[index] = regex
        elif line.startswith('regex_Becchi_ranges1'):
            ranges1[index] = regex
        else:
            print("error")
            print(line)
            exit()

with open('regex/regexlib-clean.re', 'w') as f:
    f.writelines([regex + '\n' for regex in regexlib])

with open('regex/snort-clean.re', 'w') as f:
    f.writelines([regex + '\n' for regex in snort])

with open('regex/Becchi/dotstar0.3.conf_300-0.re', 'w') as f:
    f.writelines([regex + '\n' for regex in dotstar03])

with open('regex/Becchi/dotstar0.6.conf_300-0.re', 'w') as f:
    f.writelines([regex + '\n' for regex in dotstar06])

with open('regex/Becchi/dotstar0.9.conf_300-0.re', 'w') as f:
    f.writelines([regex + '\n' for regex in dotstar09])

with open('regex/Becchi/exact-math.conf_300-0.re', 'w') as f:
    f.writelines([regex + '\n' for regex in exactmath])

with open('regex/Becchi/ranges05.conf_300-0.re', 'w') as f:
    f.writelines([regex + '\n' for regex in ranges05])

with open('regex/Becchi/ranges1.conf_300-0.re', 'w') as f:
    f.writelines([regex + '\n' for regex in ranges1])
