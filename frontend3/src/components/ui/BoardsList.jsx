import NewBoard from "../ui/NewBoard";
import Modal from "../ui/Modal";
export default function BoardsList({
	userBoardsData,
	showModal,
	setShowModal,
	currentBoardId,
	setCurrentBoardId,
}) {
	let names = "loading...";
	const TOKEN = localStorage.getItem("token");
	function chooseBoard(e) {
		const targetToken = e.currentTarget.dataset.id;

		fetch("http://localhost:8080/setdefaultboard", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${TOKEN}`,
			},
			body: JSON.stringify({
				defaultBoardId: targetToken,
			}),
		})
			.then((res) => res.text())
			.then(() => {
				setCurrentBoardId(targetToken);
			});
	}

	if (userBoardsData) {
		names = userBoardsData.map((boardData) =>
			boardData.boardId === Number(currentBoardId) ? (
				<button
					onClick={(e) => chooseBoard(e)}
					data-id={boardData.boardId}
					key={boardData.boardId}
					className="board-link board-link-active"
				>
					{boardData.boardName}
				</button>
			) : (
				<button
					onClick={(e) => chooseBoard(e)}
					data-id={boardData.boardId}
					key={boardData.boardId}
					className="board-link"
				>
					{boardData.boardName}
				</button>
			)
		);
	}

	return (
		<div className="boards-list">
			<h3>Boards</h3>
			{names}
			<button onClick={() => setShowModal("true")} className="newBoard new">
				Create new
			</button>
			{showModal && (
				<Modal>
					<NewBoard
						TOKEN={localStorage.getItem("token")}
						setCurrentBoardId={setCurrentBoardId}
						setShowModal={setShowModal}
						closeButton={true}
					/>
				</Modal>
			)}
		</div>
	);
}
