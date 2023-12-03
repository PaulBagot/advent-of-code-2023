const fs = require('fs');
const readline = require('readline');

const content = fs.readFileSync('./day3/input.txt', { encoding: 'utf8', flag: 'r' });
let args = content.split("\n");

/**
 * changer le path dans la fonction readFileSync
 * 
 */

function solve(start, end, line) {
	//console.log('start ' + start + ' end : ' + end)
	if(line == undefined)
		return 0;
	let cpt = 0;
	end = end < line.length - 1 ? end + 1 : end;
	start = start > 0 ? start - 1 : start;
	//console.log('after change : start ' + start + ' end : ' + end)
	for(let i = start; i <= end; i++) {
		if(!(line.charCodeAt(i) >= 48 && line.charCodeAt(i) <= 57)) {
			if(line[i] != '.' && line[i] != undefined && line.charCodeAt(i) != 13) {
				cpt++;
				//console.log(i + ' : ' + line[i])
			}
		}
	}
	//console.log(cpt > 0)
	return cpt > 0;
}

let sum = 0;
//fonction principale
for(let j=  0; j < args.length; j++) {
	let str = '';
	for(let i = 0; i < args[j].length; i++) {
		if(args[j].charCodeAt(i) >= 48 && args[j].charCodeAt(i) <= 57) {
			str += args[j][i];
		} else {
			if(str.length >= 1) {
				if(solve(i - str.length, i - 1, args[j + 1]) || solve(i - str.length, i - 1, args[j - 1])) {
					sum += Number.parseInt(str);
				} else {
					let coteLeft = args[j][i - str.length - 1];
					let coteRight = args[j][i];
					if(coteLeft != '.' && coteLeft != undefined || coteRight != '.' && coteRight != undefined && args[j].charCodeAt(i) != 13)
						sum += Number.parseInt(str);
				}
			}
			str = '';
		}
	}
};


console.log(sum);