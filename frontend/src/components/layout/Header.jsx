import { useContext } from "react";
import { UserContext } from "../../context/Contexts";

export default function Header() {
	const { username } = useContext(UserContext);
	return (
		<header className="header">
			<h2 className="header__username">{username}</h2>
			<button
				onClick={() => {
					localStorage.removeItem("token");
					document.location.reload();
				}}
				className="logout"
			>
				logout
			</button>
		</header>
	);
}
