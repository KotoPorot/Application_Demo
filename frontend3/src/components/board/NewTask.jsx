import { useRef } from "react";

export default function NewTask({ currBoardData, setShowCreateTask }) {
	// TODO: private Long executorId;
	const TOKEN = localStorage.getItem("token");
	const currTitle = useRef("");
	const currDescription = useRef("");
	const currDepartment = useRef();

	let departments = <></>;
	if (currBoardData.boardDepartments) {
		departments = currBoardData.boardDepartments.map((dep) => (
			<option key={dep.id} value={dep.id}>
				{dep.name}
			</option>
		));
	}
	function createTask() {
		fetch("http://localhost:8080/createTask", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${TOKEN}`,
			},
			body: JSON.stringify({
				title: currTitle.current.value,
				description: currDescription.current.value,
				boardId: currBoardData.boardId,
				...(currDepartment.current?.value
					? { departmentId: currDepartment.current?.value }
					: {}),
			}),
		})
			.then((res) => {
				return res.json();
			})
			.then(() => {
				setShowCreateTask(false);
			});
	}

	return (
		<>
			<h2 className="modal-title">Create New Task</h2>
			<form className="modal-form">
				<label htmlFor="task-title">Task Title:</label>
				<input
					ref={currTitle}
					type="text"
					id="task-title"
					name="task-title"
					required
				/>
				<label htmlFor="task-desc">Description:</label>
				<textarea
					ref={currDescription}
					id="task-desc"
					name="task-desc"
					rows="4"
					required
				></textarea>
				<label htmlFor="task-department">Select department:</label>
				<select
					ref={currDepartment}
					id="task-department"
					name="task-department"
					required
				>
					<option value="">none</option>
					{departments}
				</select>
				<button type="button" onClick={createTask} className="submit-btn">
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
