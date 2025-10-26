import NewBoard from "../ui/NewBoard";
import Modal from "../ui/Modal";
import { UserContext } from "../../context/Contexts";
import { useContext } from "react";

export default function BoardsList({ showModal, setShowModal }) {
	const { TOKEN, setCurrentBoardId, userBoards, currentBoardId } =
		useContext(UserContext);

	let names = "loading...";
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

	if (userBoards) {
		names = userBoards.map((boardData) =>
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
					<NewBoard setShowModal={setShowModal} closeButton={true} />
				</Modal>
			)}
		</div>
	);
}
