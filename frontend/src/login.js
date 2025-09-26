export default function Login(element) {
	let TOKEN = undefined;
	element.innerHTML = `
      <input type="text" name="username" placeholder="username" />
			<input id="password" type="password" name="password" placeholder="password" />
			<button id="login" type="submit">login</button>
      `;
	document.querySelector("#login").addEventListener("click", () => {
		let username = document.querySelector("input[name='username']").value;
		let password = document.getElementById("password").value;

		fetch("http://localhost:8080/login", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ username: username, password: password }),
		})
			.then((res) => res.text())
			.then((data) => {
				TOKEN = data;
			})
			.then(() => console.log("Registered, token:", TOKEN));
	});
}
