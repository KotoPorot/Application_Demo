import React from "react";
import "./MainPage.css";
import Sidebar from "./Sidebar";
import Header from "./Header";
import Board from "../board/Board";
import NewBoard from "../ui/NewBoard";
import Modal from "../ui/Modal";

export default function MainPage({ TOKEN }) {
	const [userData, setUserData] = React.useState([]);
	const [currentBoardId, setCurrentBoardId] = React.useState(null);

	const [showCreateTask, setShowCreateTask] = React.useState(false);
	const [showModal, setShowModal] = React.useState(false);

	const [refreshBoardData, setRefreshBoardData] = React.useState(false);

	const [boardData, setBoardData] = React.useState([]);
	let quantityOfTask = React.useRef(0);

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
				setCurrentBoardId(data.defaultBoardId);
				// 	userData.userId,
				// 	userData.username,
				// 	userData.userBoards,
				// 	userData.defaultBoardId
			});
	}, [currentBoardId, TOKEN]);

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
					// boardData.boardDepartments
					// boardData.boardId
					// boardData.boardName
					// boardData.boardTasks
					// boardData.members
				});
		}
	}, [refreshBoardData, currentBoardId, TOKEN, showCreateTask]);
	return (
		<>
			{!currentBoardId && (
				<Modal>
					<NewBoard
						TOKEN={TOKEN}
						setCurrentBoardId={setCurrentBoardId}
						setShowModal={setShowModal}
						closeButton={false}
					/>
				</Modal>
			)}

			<Header userName={userData.username} />
			<div className="container">
				<Sidebar
					userBoardsData={userData.userBoards}
					currBoardData={boardData}
					showModal={showModal}
					setShowModal={setShowModal}
					setCurrentBoardId={setCurrentBoardId}
					currentBoardId={currentBoardId}
					setRefreshBoardData={setRefreshBoardData}
				/>
				<Board
					currBoardData={boardData}
					boardName={boardData.boardName}
					showCreateTask={showCreateTask}
					setShowCreateTask={setShowCreateTask}
					quantityOfTask={quantityOfTask.current}
					setRefreshBoardData={setRefreshBoardData}
				/>
			</div>
		</>
	);
}
