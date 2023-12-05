const { match } = require('assert');
const fs = require('fs');
const readline = require('readline');

const content = fs.readFileSync('./day5/input', { encoding: 'utf8', flag: 'r' });
let args = content.split("\n");

/**
 * changer le path dans la fonction readFileSync
 * 
 */

let index = -1;
let seeds = [];
seeds[0] = [];
let cpt = 0;
let Args = args[0].split(':')[1].trim().split(' '); 
for(let i = 0; i < Args.length; i += 2) {
	for(let j = 0; j < Args[i + 1]; j++) {
		seeds[0][cpt] = parseInt(Args[i]) + j;
		cpt++;
	}
}

firstValue = true;
//fonction principale
for(let i = 1; i < args.length; i++) {
	if(args[i].charCodeAt(0) >= 48 && args[i].charCodeAt(0) <= 57) {
		let newValues = [];
		let Args = args[i].trim().split(' ');
		Args[0] = parseInt(Args[0]);
		Args[1] = parseInt(Args[1]);
		Args[2] = parseInt(Args[2]);
		for(let i = 0; i < seeds[index].length; i++) {
			seeds[index][i] = parseInt(seeds[index][i]);
			let seed = seeds[index][i];
			if(seed >= Args[1] && seed < (Args[2] + Args[1])) {
				newValues[i] = seed +  Args[0] -  Args[1];
			} else if(firstValue) {
				newValues[i] = seed;
			} else {
				newValues[i] = seeds[index + 1][i];
			}
		}
		seeds[index + 1] = newValues;
		newValues = null;
		firstValue = false;
	} else if(args[i].charCodeAt(0) >= 97 && args[i].charCodeAt(0) <= 122){
		index++;
		firstValue = true;
	}
}


let min = seeds[index + 1][0];
for(let i = 0; i < seeds[index].length; i++) {
	if(min > seeds[index + 1][i]) min = seeds[index + 1][i];
}
//484023871
//859731507
//93788767
console.table(min);