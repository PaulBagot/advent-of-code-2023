const fs = require('fs');
const readline = require('readline');

const content = fs.readFileSync('./input.txt', { encoding: 'utf8', flag: 'r' });
let args = content.split('/\r?\n/');

//fonction principale
args.forEach(line => {
	console.log(line)
});