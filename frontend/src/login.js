export default function Login(element) {
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
			.then((response) => response.text())
			.then((data) => {
				localStorage.setItem("TOKEN", data);
				document.location.reload();
			});
	});
}
