import sys

if __name__ == '__main__':
	name = sys.argv[1] + '.out'
	f1 = open('./' + name, 'r')
	f2 = open('./public/' + name, 'r')

	lines_1 = f1.readlines()
	lines_2 = f2.readlines()

	if len(lines_1) != len(lines_2):
		print('line length different!')

	for i in xrange(len(lines_1)):
		if lines_1[i] != lines_2[i]:
			print('line number : ' + str(i+1))
			print(lines_1[i][:-1] + ' / ' + lines_2[i][:-1])
	print('same!')
