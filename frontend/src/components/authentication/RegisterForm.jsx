export default function RegisterForm() {
	function registration(formData) {
		fetch("http://localhost:8080/register", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({
				username: formData.get("username"),
				password: formData.get("password"),
			}),
		})
			.then((res) => {
				console.log(res);
				alert("Success!");
			})
			.then(() => {
				fetch("http://localhost:8080/login", {
					method: "POST",
					headers: { "Content-Type": "application/json" },
					body: JSON.stringify({
						username: formData.get("username"),
						password: formData.get("password"),
					}),
				})
					.then((res) => res.text())
					.then((data) => {
						localStorage.setItem("token", data);
						document.location.reload();
					});
			});
	}
	return (
		<form action={registration} className="auth-modal__main-content">
			<label htmlFor="username">Username:</label>
			<input
				type="text"
				name="username"
				id="username"
				className="auth-modal__input"
				placeholder="John Doe"
				required
				minLength="3"
			/>

			<label htmlFor="password">Password:</label>
			<input
				id="password"
				type="password"
				name="password"
				className="auth-modal__input"
				placeholder="*******"
				required
				minLength="3"
			/>
			<button type="submit" className="auth-modal__submit" id="register">
				Sign up
			</button>
		</form>
	);
}
