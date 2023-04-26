
function personalBMI(Name, Age, Weight, Height) {
	var n = 5;
	let status = "";
	let bmi = Math.round(Weight / (Height / 100 * Height / 100));

	if (bmi < 18.5) {
		status = "underweight";
	} else if (bmi < 25) {
		status = "underweight";
	} else if (bmi < 30) {
		status = "underweight";
	} else if (bmi >= 30) {
		status = "underweight";
	}


	let personalInf = {
		age: Age,
		weight: Weight,
		height: Height
	}

	let result = {
		name: Name,
		personalInfo: {
			age: Age,
			weight: Weight,
			height: Height
		},
		BMI: bmi,
		status: status

	}
	console.log(result);
}
personalBMI("Petar", 22, 75, 175);
console.log(n)
