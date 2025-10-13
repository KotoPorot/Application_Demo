import { useRef } from "react";
export default function NewBoard({
	TOKEN,
	setCurrentBoardId,
	setShowModal,
	closeButton,
}) {
	const boardName = useRef();

	function createBoard() {
		fetch("http://localhost:8080/createBoard", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${TOKEN}`,
			},
			body: JSON.stringify({
				boardName: boardName.current.value,
			}),
		})
			.then((res) => res.json())
			.then((data) => {
				setCurrentBoardId(data.boardId);
				setShowModal(false);
			});
	}
	return (
		<>
			<h2 className="title">Create new board</h2>
			<input
				ref={boardName}
				type="text"
				placeholder="Board name"
				required
				minLength="1"
			/>
			<button onClick={createBoard} type="button">
				Create board
			</button>
			{closeButton && (
				<button onClick={() => setShowModal(false)} className="close-btn">
					close
				</button>
			)}
		</>
	);
}
