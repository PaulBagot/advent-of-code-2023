const fs = require('fs');
const readline = require('readline');

const content = fs.readFileSync('./day1/input', { encoding: 'utf8', flag: 'r' });
let args = content.split("\n");

//fonction principale
let sum = 0;
tabNumber = ['one', 'tw', 'three', 'four', 'five', 'six', 'seve', 'igh', 'nine'];
args.forEach(line => {
	let Args = new Array();
	for(let i = 0; i < tabNumber.length; i++)
		line = line.replaceAll(tabNumber[i], (i + 1));
	
	for(let i = 0; i < line.length; i++)
		if(line.charCodeAt(i) >= 48 && line.charCodeAt(i) <= 57)
			Args.push(line[i]);

	sum += Number.parseInt(Args[0] + Args[Args.length - 1]);
});

console.log(sum)