import "./style.css";
import Register from "./register.js";
import Login from "./Login.js";

document.querySelector("#app").innerHTML = `
  	<div class="pop-up">
			<div class="pop-up__header">
				<button class="pop-up__header-btn --choosed" id="choose-register">Sign up</button>
				<button class="pop-up__header-btn" id="choose-login">Login</button>
			</div>
			
			<div class="pop-up__body">
				
			</div>
			<!-- <button id="home" type="submit">home page</button> -->
	</div>
`;

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

const popUpBody = document.querySelector(".pop-up__body");
Register(popUpBody);
// document.querySelector("#home").addEventListener("click", () => {
// 	fetch("http://localhost:8080/", {
// 		method: "GET",
// 		headers: { "Content-Type": "application/json" },
// 	}).then((res) => res.text());
// });
