import { useRef } from "react";
export default function LoginForm() {
	const username = useRef(null);
	const password = useRef(null);
	function login() {
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
				name="password"
				className="auth-modal__input"
				placeholder="password"
				required
				minLength="3"
			/>
			<button
				type="button"
				className="auth-modal__submit"
				onClick={login}
				id="login"
			>
				login
			</button>
		</>
	);
}
