const fs = require('fs');
const readline = require('readline');

const content = fs.readFileSync('./day2/input', { encoding: 'utf8', flag: 'r' });
let args = content.split("\n");

/**
 * changer le path dans la fonction readFileSync
 * 
 */
let sum = 0;

function checkColor(num, color) {
	let Num = Number.parseInt(num);
	if(color.includes('blue'))
		return Num <= 14
	if(color.includes('red'))
		return Num <= 12
	if(color.includes('green'))
		return Num <= 13
	console.log(Num + ' ' + color + ' wth')
}


//fonction principale
args.forEach(line => {
	let Args = line.split(':');
	let iterations = Args[1].split(';');
	let error = false;
	iterations.forEach(iteration => {
		colors = iteration.split(',');
		colors.forEach(color => {
			let parameters = color.split(' ');
			if(!checkColor(parameters[1], parameters[2]))
				error = true;
		});
	});
	if(!error) {
		//console.log(Args[0].split(' ')[1])
		sum += Number.parseInt(Args[0].split(' ')[1]);
	}
});


console.log(sum);