import { useRef } from "react";
export default function RegisterForm() {
	const username = useRef(null);
	const password = useRef(null);
	function registration() {
		fetch("http://localhost:8080/register", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({
				username: username.current.value,
				password: password.current.value,
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
						username: username.current.value,
						password: password.current.value,
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
		<>
			<input
				ref={username}
				type="text"
				name="username"
				className="auth-modal__input"
				placeholder="username"
				required
				minLength="3"
			/>
			<input
				ref={password}
				id="password"
				type="password"
				className="auth-modal__input"
				name="password"
				placeholder="password"
				required
				minLength="3"
			/>
			<button
				type="button"
				onClick={registration}
				className="auth-modal__submit"
				id="register"
			>
				Sign up
			</button>
		</>
	);
}
