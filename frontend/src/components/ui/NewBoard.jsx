import { useContext } from "react";
import { UserContext } from "../../context/Contexts";

export default function NewBoard({ setShowModal, closeButton }) {
	const { TOKEN, setCurrentBoardId } = useContext(UserContext);
	function createBoard(formData) {
		fetch("http://localhost:8080/createBoard", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${TOKEN}`,
			},
			body: JSON.stringify({
				boardName: formData.get("boardName"),
			}),
		})
			.then((res) => res.json())
			.then((data) => {
				setCurrentBoardId(data.boardId);
				setShowModal(false);
			});
	}
	return (
		<form action={createBoard} className="form-content">
			<h2 className="title">Create new board</h2>
			<label htmlFor="boardName">Board name:</label>
			<input
				className="input"
				name="boardName"
				id="boardName"
				type="text"
				placeholder="NewTech GmbH"
				required
				minLength="1"
			/>
			<button type="submit" className="submit-btn">
				Create board
			</button>
			{closeButton && (
				<button onClick={() => setShowModal(false)} className="close-btn">
					close
				</button>
			)}
		</form>
	);
}
