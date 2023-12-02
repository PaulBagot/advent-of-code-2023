const fs = require('fs');
const readline = require('readline');

const content = fs.readFileSync('./day2/input', { encoding: 'utf8', flag: 'r' });
let args = content.split("\n");

/**
 * changer le path dans la fonction readFileSync
 * 
 */
let sum = 0;
let tab = [0,0,0];
function checkColor(num, color) {
	let Num = Number.parseInt(num);
	if(color.includes('red')) {
		if(Num > tab[0]) tab[0] = Num;
	}
	if(color.includes('green')) {
		if(Num > tab[1]) tab[1] = Num;
	}
	if(color.includes('blue')) {
		if(Num > tab[2]) tab[2] = Num;
	}
}


//fonction principale
args.forEach(line => {
	let Args = line.split(':');
	let iterations = Args[1].split(';');
	let error = false;
	tab = [0,0,0];
	iterations.forEach(iteration => {
		colors = iteration.split(',');
		colors.forEach(color => {
			let parameters = color.split(' ');
			checkColor(parameters[1], parameters[2]);
		});
	});
	console.log(tab[0] + ' ' + tab[1] + ' ' + tab[2])
	sum += tab[0] * tab[1] * tab[2];
});


console.log(sum);