import { useContext } from "react";
import { BoardContext, UserContext } from "../../context/Contexts";

export default function NewTask({ setShowCreateTask }) {
	const { TOKEN, currentBoardId } = useContext(UserContext);
	const { boardDepartments, setRefreshBoardData } = useContext(BoardContext);
	// TODO: private Long executorId;

	let departments = <></>;
	if (boardDepartments) {
		departments = boardDepartments.map((dep) => (
			<option key={dep.id} value={dep.id}>
				{dep.name}
			</option>
		));
	}
	function createTask(formData) {
		fetch("http://localhost:8080/createTask", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${TOKEN}`,
			},
			body: JSON.stringify({
				title: formData.get("task-title"),
				description: formData.get("task-desc"),
				boardId: currentBoardId,
				...(formData.get("task-department")
					? { departmentId: formData.get("task-department") }
					: {}),
			}),
		})
			.then((res) => {
				return res;
			})
			.then(() => {
				setShowCreateTask(false);
				setRefreshBoardData((prev) => !prev);
			});
	}

	return (
		<>
			<h2 className="modal-title">Create New Task</h2>
			<form action={createTask} className="modal-form">
				<label htmlFor="task-title">Task Title:</label>
				<input
					className="input"
					placeholder="Take a brake"
					type="text"
					id="task-title"
					name="task-title"
					required
				/>
				<label htmlFor="task-desc">Description:</label>
				<textarea
					className="input"
					id="task-desc"
					name="task-desc"
					rows="4"
				></textarea>
				<label htmlFor="task-department">Select department:</label>
				<select id="task-department" name="task-department">
					<option value="">none</option>
					{departments}
				</select>
				<button type="submit" className="submit-btn">
					Add Task
				</button>
				<button
					className="close-btn"
					onClick={() => setShowCreateTask(false)}
					type="button"
				>
					Cancel
				</button>
			</form>
		</>
	);
}
