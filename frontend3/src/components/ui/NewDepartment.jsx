import { useRef } from "react";
export default function NewDepartment({
	currentBoardId,
	showCreateDepartment,
	setShowCreateDepartment,
	setCurrentDepartmentId,
}) {
	const departmentName = useRef();
	const TOKEN = localStorage.getItem("token");
	function createDepartment() {
		fetch("http://localhost:8080/createDepartment", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${TOKEN}`,
			},
			body: JSON.stringify({
				boardId: currentBoardId,
				name: departmentName.current.value,
			}),
		})
			.then((res) => res.json())
			.then((data) => {
				setShowCreateDepartment(false);
				setCurrentDepartmentId(data.id);
			});
	}
	return (
		<>
			<h2 className="title">Create new department</h2>
			<input
				ref={departmentName}
				type="text"
				placeholder="Department name"
				required
				minLength="1"
			/>
			<button onClick={createDepartment} type="button">
				Create department
			</button>
			{showCreateDepartment && (
				<button
					onClick={() => setShowCreateDepartment(false)}
					className="close-btn"
				>
					close
				</button>
			)}
		</>
	);
}
