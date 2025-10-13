import { useState } from "react";
import LoginForm from "./LoginForm";
import RegisterForm from "./RegisterForm";
import "./AuthModal.css";

export default function AuthModal() {
	const [loginOrRegister, setLoginOrRegister] = useState("login");
	return (
		<div className="auth-modal">
			<div className="auth-modal__header">
				<button
					onClick={() => {
						setLoginOrRegister("login");
					}}
					className={
						loginOrRegister === "login"
							? "auth-modal__toggle-btn--choosed auth-modal__toggle-btn"
							: "auth-modal__toggle-btn"
					}
				>
					login
				</button>
				<button
					onClick={() => {
						setLoginOrRegister("register");
					}}
					className={
						loginOrRegister === "register"
							? "auth-modal__toggle-btn--choosed auth-modal__toggle-btn"
							: "auth-modal__toggle-btn"
					}
				>
					register
				</button>
			</div>
			<form className="auth-modal__main-content">
				{loginOrRegister === "login" && <LoginForm />}
				{loginOrRegister === "register" && <RegisterForm />}
			</form>
		</div>
	);
}
