import Modal from "../ui/Modal";
import { useState } from "react";
export default function BoardHeader({ boardName, boardMembers }) {
	const members = boardMembers.map((member) => <li>{member.username}</li>);
	const [showModalMembers, setShowModalMembers] = useState(false);
	return (
		<div className="board-header">
			<h2 className="board__department-name">{boardName}</h2>
			<button onClick={() => setShowModalMembers(true)} className="member-btn">
				Members
			</button>
			{showModalMembers && (
				<Modal>
					<div className="members-container">
						<h2>Board Members:</h2>
						<ul>{members}</ul>

						<button className="add-member new">Add member</button>
						<button
							className="close-btn"
							onClick={() => setShowModalMembers(false)}
						>
							Close
						</button>
					</div>
				</Modal>
			)}
		</div>
	);
}
