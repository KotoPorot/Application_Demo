export default function Header({ userName }) {
	return (
		<header className="header">
			<h2 className="header__username">{userName}</h2>
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
