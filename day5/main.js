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
seeds[0] = args[0].split(':')[1].trim().split(' ');
let Args = [];
//fonction principale
for(let i = 1; i < args.length; i++) {
	if(args[i].charCodeAt(0) >= 48 && args[i].charCodeAt(0) <= 57) {
		let Args = args[i].trim().split(' ');
		console.table(Args);
		console.table(seeds[index])
		for(let i = 0; i < seeds[index].length; i++) {
			if(parseInt(seeds[index][i]) >= parseInt(Args[0]) && parseInt(seeds[index][i]) < parseInt(Args[2]) + parseInt(Args[1])) { // => refaire ça mdrr
				//seeds[index][i + 1] = parseInt(seeds[index][i]) +  parseInt(Args[j][0]) -  parseInt(Args[j][1]);
				console.log('seed : ' + seeds[index][i] + ' mini : ' + Args[0] + ' max : ' + + (parseInt(Args[0]) + parseInt(Args[2])));
				console.log(parseInt(seeds[index][i]) - parseInt(Args[0]) + parseInt(Args[1]))
			}
		}
	} else if(args[i].charCodeAt(0) >= 97 && args[i].charCodeAt(0) <= 122){
		index++;
	}
}


console.table(seeds);