export default function Register(element) {
	element.innerHTML = `
      <input type="text" name="username" placeholder="username" />
			<input id="password" type="password" name="password" placeholder="password" />
			<button id="register" type="submit">Sign up</button>`;

	document.querySelector("#register").addEventListener("click", () => {
		let username = document.querySelector("input[name='username']").value;
		let password = document.getElementById("password").value;
		fetch("http://localhost:8080/register", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ username: username, password: password }),
		})
			.then((res) => res.json())
			.then((data) => {
				console.log(data);
			});
	});
}
