import "./style.css";
import Register from "./register.js";
import Login from "./login.js";

document.querySelector("#app").innerHTML = `
  	<div class="pop-up">
			<div class="pop-up__header">
				<button class="pop-up__header-btn --choosed" id="choose-register">Sign up</button>
				<button class="pop-up__header-btn" id="choose-login">Login</button>
			</div>
			
			<div class="pop-up__body">
				
			</div>
	</div>
`;
// Default view
if (localStorage.getItem("TOKEN")) {
	toMainPage();
} else {
	const popUpBody = document.querySelector(".pop-up__body");
	Register(popUpBody);
}

function logout() {
	localStorage.removeItem("TOKEN");
	document.location.reload();
}

function toMainPage() {
	fetch("http://localhost:8080/", {
		method: "GET",
		headers: {
			Authorization: `Bearer ${localStorage.getItem("TOKEN")}`,
		},
	})
		.then((res) => res.text())
		.then(() => {
			document.querySelector("#app").innerHTML = `
  	<div class="pop-up">
		<span class='succeed'>You are logged in!</span>

			<div class="pop-up__body">
				<button id="logout">Logout</button>
			</div>
			<div>
	</div>
`;
			document.getElementById("logout").addEventListener("click", logout);
		});
}
// Register-Login switcher
document.querySelector("#choose-register").addEventListener("click", (e) => {
	e.target.classList.add("--choosed");
	document.querySelector("#choose-login").classList.remove("--choosed");
	const popUpBody = document.querySelector(".pop-up__body");
	Register(popUpBody);
});
document.querySelector("#choose-login").addEventListener("click", (e) => {
	e.target.classList.add("--choosed");
	document.querySelector("#choose-register").classList.remove("--choosed");
	const popUpBody = document.querySelector(".pop-up__body");
	Login(popUpBody);
});

// Default view
if (localStorage.getItem("TOKEN")) {
	toMainPage();
} else {
	const popUpBody = document.querySelector(".pop-up__body");
	Register(popUpBody);
}
