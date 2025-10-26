import Modal from "../ui/Modal";
import Members from "./Members";
import { useState, useContext } from "react";
import { BoardContext } from "../../context/Contexts";
export default function BoardHeader() {
	const { contextBoardName, members } = useContext(BoardContext);
	let memberList = "loading...";
	if (members) {
		memberList = members.map((member) => (
			<li className="title" key={member.userId}>
				{member.username}
			</li>
		));
	}

	const [showModalMembers, setShowModalMembers] = useState(false);
	return (
		<div className="board-header">
			<h2 className="board__department-name">{contextBoardName}</h2>
			<button onClick={() => setShowModalMembers(true)} className="member-btn">
				Members
			</button>
			{showModalMembers && (
				<Modal>
					<Members
						memberList={memberList}
						setShowModalMembers={setShowModalMembers}
					/>
				</Modal>
			)}
		</div>
	);
}
