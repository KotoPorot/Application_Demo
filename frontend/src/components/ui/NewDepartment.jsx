export default function NewDepartment({
	currentBoardId,
	showCreateDepartment,
	setShowCreateDepartment,
	setRefreshBoardData,
}) {
	const TOKEN = localStorage.getItem("token");
	function createDepartment(formData) {
		fetch("http://localhost:8080/createDepartment", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${TOKEN}`,
			},
			body: JSON.stringify({
				boardId: currentBoardId,
				name: formData.get("departmentName"),
			}),
		})
			.then((res) => res)
			.then(() => {
				setShowCreateDepartment(false);
				setRefreshBoardData((prev) => !prev);
			});
	}
	return (
		<form action={createDepartment} className="form-content">
			<h2 className="title">Create new department</h2>
			<label htmlFor="departmentName">Department name:</label>
			<input
				className="input"
				name="departmentName"
				id="departmentName"
				type="text"
				placeholder="Customer Support"
				required
				minLength="1"
			/>
			<button type="submit" className="submit-btn">
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
		</form>
	);
}
