import Modal from "../ui/Modal";
import Members from "./Members";
import { useState } from "react";
export default function BoardHeader({
	boardName,
	boardMembers,
	currentBoardId,
}) {
	let members = "loading...";
	if (boardMembers) {
		members = boardMembers.map((member) => (
			<li key={member.userId}>{member.username}</li>
		));
	}

	const [showModalMembers, setShowModalMembers] = useState(false);
	return (
		<div className="board-header">
			<h2 className="board__department-name">{boardName}</h2>
			<button onClick={() => setShowModalMembers(true)} className="member-btn">
				Members
			</button>
			{showModalMembers && (
				<Modal>
					<Members
						members={members}
						setShowModalMembers={setShowModalMembers}
						currentBoardId={currentBoardId}
					/>
				</Modal>
			)}
		</div>
	);
}
