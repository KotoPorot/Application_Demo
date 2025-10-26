import { useState, useContext } from "react";
import { BoardContext, UserContext } from "../../context/Contexts";

export default function Members({ memberList, setShowModalMembers }) {
	const { setRefreshBoardData } = useContext(BoardContext);
	const { currentBoardId, TOKEN } = useContext(UserContext);
	const [showAddMember, setShowAddMember] = useState(false);

	function addMemberHandler(formData) {
		fetch("http://localhost:8080/addBoardMember", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${TOKEN}`,
			},
			body: JSON.stringify({
				userName: formData.get("new-member-name"),
				boardId: currentBoardId,
				boardRole: formData.get("role"),
			}),
		})
			.then((res) => res)
			.then(() => {
				setShowAddMember(false);
				setShowModalMembers(false);
				setRefreshBoardData((prev) => !prev);
			});
	}
	return (
		<div className="members-container">
			{showAddMember ? (
				<form action={addMemberHandler} className="form-content">
					<label htmlFor="new-member-name">Member name</label>
					<input
						name="new-member-name"
						type="text"
						id="new-member-name"
						className="input"
						required
					/>
					<label htmlFor="select-role">Select role:</label>
					<select name="role" id="select-role" defaultValue={"MEMBER"} required>
						<option value="MEMBER" required>
							Member
						</option>
						<option value="MANAGER">Manager</option>
					</select>
					<button type="submit" className="submit-btn">
						Add member
					</button>
				</form>
			) : (
				<>
					<h2>Board Members:</h2>
					<ul>{memberList}</ul>{" "}
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
