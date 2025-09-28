import Login from "./login.js";
export default function Register(element) {
	element.innerHTML = `
      <input type="text" name="username" placeholder="username" required minlength='3' />
			<input id="password" type="password" name="password" placeholder="password" required minlength='3' />
			<button id="register">Sign up</button>`;

	document.querySelector("#register").addEventListener("click", () => {
		let username = document.querySelector("input[name='username']").value;
		let password = document.getElementById("password").value;

		fetch("http://localhost:8080/register", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ username: username, password: password }),
		})
			.then((res) => {
				console.log(res);
				document.querySelector("#choose-login").classList.add("--choosed");
				document
					.querySelector("#choose-register")
					.classList.remove("--choosed");
				const popUpBody = document.querySelector(".pop-up__body");
				Login(popUpBody);
			})
			.catch((error) => {
				console.error("Error:", error);
			});
	});
}
