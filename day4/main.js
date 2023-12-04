const fs = require('fs');
const readline = require('readline');

const content = fs.readFileSync('./day4/input', { encoding: 'utf8', flag: 'r' });
let args = content.split("\n");

/**
 * changer le path dans la fonction readFileSync
 * 
 */

let index = 0;
let total = 0;
let tab = [];
for(let i = 0; i < args.length; i++) {
	tab[i] = 1;
}
//fonction principale
args.forEach(line => {
	let Args = line.split(':');
	let game = Args[1].split('|');
	let win = game[0].trim().split(' ');
	let numbers = game[1].trim().split(' ');
	let sum = 0;
	for(let  i = 0; i < win.length; i++) {
		for(let j = 0; j < numbers.length; j++) {
			if(win[i] == numbers[j] && (win[i] != '' || numbers[j] != '')) {
				sum++;
				tab[index + sum] += tab[index];
			}
		}
	}
	index++;
});

tab.forEach(n => {
	total += n;
})

console.log(total);