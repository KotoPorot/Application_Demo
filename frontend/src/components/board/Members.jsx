import { useState, useRef } from "react";
export default function Members({
	members,
	setShowModalMembers,
	currentBoardId,
}) {
	const newMemberName = useRef();
	const newMemberRole = useRef("MEMBER");
	const [showAddMember, setShowAddMember] = useState(false);
	function addMemberHandler() {
		const TOKEN = localStorage.getItem("token");
		fetch("http://localhost:8080/addBoardMember", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${TOKEN}`,
			},
			body: JSON.stringify({
				userName: newMemberName.current.value,
				boardId: currentBoardId,
				boardRole: newMemberRole.current.value,
			}),
		})
			.then((res) => res.json())
			.then((data) => {
				console.log("Success:", data);
				setShowAddMember(false);
				setShowModalMembers(false);
			});
	}
	return (
		<div className="members-container">
			{showAddMember ? (
				<>
					<label htmlFor="new-member-name">Member name</label>
					<input
						ref={newMemberName}
						type="text"
						id="new-member-name"
						className="input"
						required
					/>
					<label htmlFor="select-role">Select role:</label>
					<select ref={newMemberRole} name="role" id="select-role">
						<option value="MEMBER">Member</option>
						<option value="MANAGER">Manager</option>
					</select>
					<button onClick={addMemberHandler} className="add-member new">
						Add member
					</button>
				</>
			) : (
				<>
					<h2>Board Members:</h2>
					<ul>{members}</ul>{" "}
					<button
						onClick={() => setShowAddMember(true)}
						className="add-member new"
					>
						Add new member
					</button>
				</>
			)}

			<button
				className="close-btn"
				onClick={() => {
					setShowModalMembers(false);
					setShowAddMember(false);
				}}
				type="button"
			>
				Close
			</button>
		</div>
	);
}
