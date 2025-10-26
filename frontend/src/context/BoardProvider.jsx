import { UserContext, BoardContext } from "./Contexts";
import React from "react";

export default function BoardProvider({ children }) {
	const { currentBoardId, TOKEN } = React.useContext(UserContext);
	const [refreshBoardData, setRefreshBoardData] = React.useState(false);
	const [boardData, setBoardData] = React.useState({});
	const boardDepartments = boardData.boardDepartments;
	const boardId = boardData.boardId;
	const boardName = boardData.boardName;
	const boardTasks = boardData.boardTasks;
	const members = boardData.members;
	let quantityOfTask = React.useRef(0);

	React.useEffect(() => {
		if (currentBoardId) {
			fetch("http://localhost:8080/getBoardInfo", {
				method: "GET",
				headers: {
					Authorization: `Bearer ${TOKEN}`,
				},
			})
				.then((res) => res.json())
				.then((data) => {
					setBoardData(data);
					quantityOfTask.current = data.boardTasks.length;
				});
		}
	}, [refreshBoardData, currentBoardId, TOKEN]);

	return (
		<BoardContext.Provider
			value={{
				boardDepartments,
				boardId,
				boardName,
				boardTasks,
				members,
				quantityOfTask: quantityOfTask.current,
				refreshBoardData,
				setRefreshBoardData,
			}}
		>
			{children}
		</BoardContext.Provider>
	);
}
