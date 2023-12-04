const fs = require('fs');
const readline = require('readline');

const content = fs.readFileSync('./day4/input', { encoding: 'utf8', flag: 'r' });
let args = content.split("\n");

/**
 * changer le path dans la fonction readFileSync
 * 
 */

let total = 0;
//fonction principale
args.forEach(line => {
	let Args = line.split(':');
	let game = Args[1].split('|');
	let win = game[0].split(' ');
	let numbers = game[1].split(' ');
	let sum = 0;
	console.log(win)
	console.log(numbers)
	for(let  i = 0; i < win.length; i++) {
		for(let j = 0; j < numbers.length; j++) {
			if(win[i] == numbers[j] && (win[i] != '' || numbers[j] != '')) {
				console.log(win[i] + ' : ' + numbers[j])
				if(sum == 0) sum++
				else sum *= 2;
				console.log(sum)
			}
		}
	}
	total += sum;
});


console.log(total);