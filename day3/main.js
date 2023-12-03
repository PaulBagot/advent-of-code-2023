const fs = require('fs');
const readline = require('readline');

const content = fs.readFileSync('./day3/input.txt', { encoding: 'utf8', flag: 'r' });
let args = content.split("\n");

/**
 * changer le path dans la fonction readFileSync
 * 
 */


let etoileCoord = '';
let sum = 0;
let coords = [];
function solve(start, end, line) {
	if(line == undefined)
		return 0;
	end++;
	start--;
	for(let i = start; i <= end; i++) {
		if(line[i] == '*') {
			etoileCoord = i + '-';
			return true;
		}
	}
	return false;
}

function solveAround(start, end, currentIndex) {
	let coteLeft = args[currentIndex][start - 1];
	let coteRight = args[currentIndex][end + 1];
	if(solve(start, end, args[currentIndex - 1]))
		etoileCoord += currentIndex - 1;
	if(solve(start, end, args[currentIndex + 1]))
		etoileCoord += currentIndex + 1;

	if(coteLeft == '*')
		etoileCoord = (start - 1) + '-' + currentIndex;
	if(coteRight == '*')
		etoileCoord = (end + 1) + '-' + currentIndex;
	if(etoileCoord != '') {
		let nombre = args[currentIndex].slice(start, end + 1);
		let Args = [];
		Args[0] = etoileCoord;
		Args[1] = nombre;
		coords[coords.length] = Args;
		etoileCoord = '';
	}
}


//fonction principale
for(let j=  0; j < args.length; j++) {
	let str = '';
	for(let i = 0; i < args[j].length; i++) {
		if(args[j].charCodeAt(i) >= 48 && args[j].charCodeAt(i) <= 57) {
			str += args[j][i];
		} else {
			if(str.length >= 1)
				solveAround(i - str.length, i - 1, j);
			str = '';
		}
	}
};

for(let i = 0; i < coords.length - 1; i++) {
	for(let j = i + 1; j < coords.length; j++) {
		if(coords[i][0] == coords[j][0])
			sum += Number.parseInt(coords[i][1]) * Number.parseInt(coords[j][1]);
	}
}

console.log(sum);