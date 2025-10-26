import React from "react";
import { UserContext } from "./Contexts";

export default function UserProvider({ children }) {
	const [currentBoardId, setCurrentBoardId] = React.useState(null);
	const [userData, setUserData] = React.useState([]);
	const userId = userData.userId;
	const username = userData.username;
	const userBoards = userData.userBoards;
	const defaultBoardId = userData.defaultBoardId;
	const TOKEN = localStorage.getItem("token");

	React.useEffect(() => {
		fetch("http://localhost:8080/getUserInfo", {
			method: "GET",
			headers: {
				Authorization: `Bearer ${TOKEN}`,
			},
		})
			.then((res) => {
				if (res.status === 401) {
					localStorage.removeItem("token");
					document.location.reload();
				}
				return res.json();
			})
			.then((data) => {
				setUserData(data);
				if (currentBoardId === null) {
					setCurrentBoardId(data.defaultBoardId); // Possible issue here
				}
			});
	}, [currentBoardId, TOKEN]);

	return (
		<UserContext.Provider
			value={{
				userId,
				username,
				userBoards,
				defaultBoardId,
				currentBoardId,
				setCurrentBoardId,
				TOKEN,
			}}
		>
			{children}
		</UserContext.Provider>
	);
}
